package com.auth.assignment.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class AuthRequest {
    @NonNull
    private String userLogin;

    @NonNull
    private CharSequence password;

    @Override
    public String toString() {
        return "LoginDto{" +
                "userLogin='" + userLogin + '\'' +
                '}';
    }
}
