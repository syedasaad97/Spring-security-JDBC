package com.auth.assignment.controller;

import com.auth.assignment.dto.AuthRequest;
import com.auth.assignment.dto.JWTTokenDto;
import com.auth.assignment.security.jwt.JWTFilter;
import com.auth.assignment.service.AuthenticationService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/authenticate")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping()
    public ResponseEntity<JWTTokenDto> authenticateRequest(@Valid @RequestBody AuthRequest loginDto)  {

        String jwtToken = authenticationService.authenticate(loginDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwtToken);
        return new ResponseEntity<>(new JWTTokenDto(jwtToken), httpHeaders, HttpStatus.OK);
    }
}
