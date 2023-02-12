package com.auth.assignment.security.jwt;

import com.auth.assignment.AuthConstants;
import com.auth.assignment.IntegrationTest;
import com.auth.assignment.util.AppProperties;
import com.auth.assignment.util.Constants;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.util.ReflectionTestUtils;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;


public class TokenProviderTest extends IntegrationTest {


    private Key key;

    private AppProperties appProperties;
    @Mock
    private JWTTokenProvider tokenProvider;

    @BeforeEach
    public void setup() {
        appProperties = Mockito.mock(AppProperties.class);
        tokenProvider = new JWTTokenProvider(appProperties);
        key = Keys.hmacShaKeyFor(Decoders.BASE64
                .decode("Byz254a45s65fds737b9aafcb3412e07edIX67f33413274720ddbb7f6c5e64e9f14075f2d7ed041592f0b7657baf8"));

        ReflectionTestUtils.setField(tokenProvider, "key", key);
        ReflectionTestUtils.setField(tokenProvider, "tokenValidityInMilliseconds", AuthConstants.ONE_MINUTE);
    }

    @Test
    public void testReturnFalse_JWThasInvalidSignature() {
        boolean isTokenValid = tokenProvider.validateJWTToken(createTokenWithDifferentSignature());

        assertEquals(isTokenValid, false);

    }

    @Test
    public void testReturnFalse_JWTisMalformed() {
        Authentication authentication = createAuthentication();
        String token = tokenProvider.createJWTToken(authentication);
        boolean isTokenValid = tokenProvider.validateJWTToken(token.replace(token.charAt(0), 'a'));

        assertEquals(isTokenValid, false);
    }

    @Test
    public void testReturnFalse_JWTisExpired() {
        ReflectionTestUtils.setField(tokenProvider, "tokenValidityInMilliseconds", -AuthConstants.ONE_MINUTE);

        Authentication authentication = createAuthentication();
        String token = tokenProvider.createJWTToken(authentication);

        boolean isTokenValid = tokenProvider.validateJWTToken(token);

        assertEquals(isTokenValid, false);
    }

    @Test
    public void testReturnFalse_JWTisInvalid() {
        boolean isTokenValid = tokenProvider.validateJWTToken("");

        assertEquals(isTokenValid, false);
    }

    @Test
    public void testReturnTrue_JWTisValid() {
        ReflectionTestUtils.setField(tokenProvider, "key", key);
        Authentication authentication = createAuthentication();
        String token = tokenProvider.createJWTToken(authentication);


        boolean isTokenValid = tokenProvider.validateJWTToken(token);

        assertEquals(isTokenValid, true);
    }

    private Authentication createAuthentication() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(Constants.USER_ROLE));
        return new UsernamePasswordAuthenticationToken("anonymous", "anonymous", authorities);
    }


}
