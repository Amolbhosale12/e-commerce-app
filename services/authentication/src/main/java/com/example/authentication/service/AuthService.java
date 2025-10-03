package com.example.authentication.service;


import com.example.authentication.dto.*;
import com.example.authentication.entity.User;
import com.example.authentication.repository.UserRepository;
import com.example.authentication.security.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final OtpService otpService;

    public void register(RegisterRequest req) {
        if (userRepository.findByEmail(req.email()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }
        User user = new User();
        user.setEmail(req.email());
        user.setPassword(passwordEncoder.encode(req.password()));
        user.setEnabled(false);
        userRepository.save(user);
        // generate OTP and (in prod) send it
        String otp = otpService.generateAndSaveOtp(user.getEmail());
        // For testing you may log or return OTP. Here we do nothing.
        log.info("OTP generated: " + otp);

    }

    public String login(AuthRequest req) {
        Optional<User> opt = userRepository.findByEmail(req.email());
        if (opt.isEmpty()) throw new IllegalArgumentException("Invalid credentials");
        User user = opt.get();
        if (!passwordEncoder.matches(req.password(), user.getPassword()))
            throw new IllegalArgumentException("Invalid credentials");
        if (!user.isEnabled()) throw new IllegalStateException("User not verified");
        return jwtUtil.generateToken(user.getEmail());
    }

    public String requestForgotPassword(OtpRequest req) {
        Optional<User> opt = userRepository.findByEmail(req.email());
        if (opt.isEmpty()) throw new IllegalArgumentException("User not found");
        return otpService.generateAndSaveOtp(req.email()); // return OTP for testing
    }

    public boolean verifyOtp(VerifyOtpRequest req) {
        boolean ok = otpService.verifyOtp(req.email(), req.otp());
        if (ok) {
            // example: mark user enabled (if registration flow)
            userRepository.findByEmail(req.email()).ifPresent(u -> {
                u.setEnabled(true);
                userRepository.save(u);
            });
        }
        return ok;
    }

    public void resetPassword(ResetPasswordRequest req) {
        boolean ok = otpService.verifyOtp(req.email(), req.otp());
        if (!ok) throw new IllegalArgumentException("Invalid or expired OTP");
        userRepository.findByEmail(req.email()).ifPresent(u -> {
            u.setPassword(passwordEncoder.encode(req.newPassword()));
            userRepository.save(u);
        });
    }
}
