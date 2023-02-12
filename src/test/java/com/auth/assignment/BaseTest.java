package com.auth.assignment;

import com.auth.assignment.dto.AuthRequest;
import com.auth.assignment.model.Role;
import com.auth.assignment.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Assertions;

import java.security.Key;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public abstract class BaseTest extends Assertions {

    public static final String USER_ROLE = "ROLE_USER";
    public static final String ADMIN_ROLE = "ROLE_ADMIN";

    protected Role createUserRole() {
        Role role = new Role(USER_ROLE);
        return role;
    }

    protected Role createAdminRole() {
        Role role = new Role(ADMIN_ROLE);
        return role;
    }

    protected User createUser(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(createUserRole());
        user.setRoles(roleSet);
        return user;
    }

    protected AuthRequest createAuthRequest(String email, String password) {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUserLogin(email);
        authRequest.setPassword(password);
        return authRequest;
    }

    protected String createTokenWithDifferentSignature() {
        Key otherKey = Keys.hmacShaKeyFor(Decoders.BASE64
                .decode("Awz254a45s65fds737b9aafcb3412e07edIx67f33413274720ddbb7f6c5e64e9f14075f2d7ed041592f0b7657baf8"));

        return Jwts.builder()
                .setSubject("anonymous")
                .signWith(otherKey, SignatureAlgorithm.HS512)
                .setExpiration(new Date(new Date().getTime() + AuthConstants.ONE_MINUTE))
                .compact();
    }

}