package com.example.authentication.dto;

public record ResetPasswordRequest(String email, String otp, String newPassword) {}
