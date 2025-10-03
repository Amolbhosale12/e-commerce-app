package com.example.authentication.service;



import com.example.authentication.entity.User;
import com.example.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.Random;

@Service
public class OtpService {

    private final UserRepository userRepository;
    private final int length;
    private final long expiryMs;
    private final Random random = new Random();

    public OtpService(UserRepository userRepository,
                      @Value("${app.otp.length}") int length,
                      @Value("${app.otp.expiry-ms}") long expiryMs) {
        this.userRepository = userRepository;
        this.length = length;
        this.expiryMs = expiryMs;
    }

    public String generateAndSaveOtp(String email) {
        Optional<User> opt = userRepository.findByEmail(email);
        if (opt.isEmpty()) throw new IllegalArgumentException("User not found");
        User user = opt.get();
        String otp = generateNumericOtp(length);
        user.setOtp(otp);
        user.setOtpExpiry(Instant.now().plusMillis(expiryMs));
        userRepository.save(user);
        // In prod: send OTP via email/SMS. Here we simply return it for testing.
        return otp;
    }

    public boolean verifyOtp(String email, String otp) {
        Optional<User> opt = userRepository.findByEmail(email);
        if (opt.isEmpty()) return false;
        User user = opt.get();
        if (user.getOtp() == null) return false;
        if (user.getOtpExpiry() == null || Instant.now().isAfter(user.getOtpExpiry())) return false;
        if (!user.getOtp().equals(otp)) return false;
        // clear OTP after successful verify
        user.setOtp(null);
        user.setOtpExpiry(null);
        userRepository.save(user);
        return true;
    }

    private String generateNumericOtp(int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
