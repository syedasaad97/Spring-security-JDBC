package com.auth.assignment.security;


import com.auth.assignment.AuthConstants;
import com.auth.assignment.IntegrationTest;
import com.auth.assignment.aop.AuthException;
import com.auth.assignment.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

public class CustomUserDetailsServiceIntTest extends IntegrationTest {

    @Autowired
    private UserDetailsService userDetailsService;


    @Test
    @Transactional
    public void testReturnUserFound_loadUserByUsername() {
        User user = createUser(AuthConstants.login_email, AuthConstants.login_password);
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());

        assertEquals(userDetails.getUsername(), user.getEmail());
    }


    @Transactional
    @Test
    public void testReturnUserNotFound_loadUserByUsername() {
        User user = createUser("test@gmail.com", AuthConstants.login_password);
        assertThrows(AuthException.class, () -> userDetailsService.loadUserByUsername(user.getEmail()));
    }


}
