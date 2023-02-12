package com.auth.assignment.service;

import com.auth.assignment.dto.AuthRequest;
import com.auth.assignment.security.jwt.JWTTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticateJWTService implements AuthenticationService {

    private final JWTTokenProvider jwtTokenProvider;

    private final AuthenticationManager authenticationManager;

    public AuthenticateJWTService(JWTTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }


    @Override
    public String authenticate(AuthRequest authRequest) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authRequest.getUserLogin(), authRequest.getPassword());
        Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.createJWTToken(authentication);
    }
}
