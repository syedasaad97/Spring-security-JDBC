package com.auth.assignment.service;

import com.auth.assignment.AuthConstants;
import com.auth.assignment.IntegrationTest;
import com.auth.assignment.aop.AuthException;
import com.auth.assignment.dto.AuthRequest;
import com.auth.assignment.security.jwt.JWTTokenProvider;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class AuthJWTServiceTest extends IntegrationTest {

    @InjectMocks
    private AuthenticateJWTService authenticateJWTService;


    @MockBean
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Mock
    private JWTTokenProvider tokenProvider;


    private AuthRequest authRequest;

    private Authentication authentication;

    private UsernamePasswordAuthenticationToken authenticationToken;


    @Test
    public void testReturnJWT_Authenticate_Success() {
        try {
            authRequest = createAuthRequest(AuthConstants.login_email, AuthConstants.login_password);
            authenticationToken =
                    new UsernamePasswordAuthenticationToken(AuthConstants.login_email, AuthConstants.login_password);
            UserDetails userDetails
                    = userDetailsService.loadUserByUsername(
                    AuthConstants.login_email);
            authentication = new UsernamePasswordAuthenticationToken(
                    userDetails.getUsername(),
                    userDetails.getPassword(),
                    userDetails.getAuthorities());
        Mockito.when(authenticationManager.authenticate(authenticationToken)).thenReturn(authentication)
            ;
            String token = createTokenWithDifferentSignature();
            Mockito.when(tokenProvider.createJWTToken(authentication))
                    .thenReturn(token);

            String jwt = authenticateJWTService.authenticate(authRequest);
            assertEquals(jwt, token);
        } catch (AuthException e) {
            fail("Exception not expected");

        }catch (Exception e){
            fail("Exception not expected");
        }


    }

}
