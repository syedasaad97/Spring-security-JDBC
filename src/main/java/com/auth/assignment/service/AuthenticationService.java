package com.auth.assignment.service;

import com.auth.assignment.dto.AuthRequest;


public interface AuthenticationService {

    String authenticate(AuthRequest authRequest);

}
