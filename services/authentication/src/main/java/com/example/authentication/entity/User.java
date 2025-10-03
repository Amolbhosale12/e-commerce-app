package com.example.authentication.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password; // BCrypt encoded

    private boolean enabled = false;

    // OTP fields for verification / reset
    private String otp;
    private Instant otpExpiry;

    // constructors, getters, setters
    public User() {}
    // getters/setters omitted for brevity (or use Lombok @Data)
    // ...
}
