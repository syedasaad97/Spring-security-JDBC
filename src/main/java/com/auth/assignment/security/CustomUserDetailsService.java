package com.auth.assignment.security;

import com.auth.assignment.aop.AuthException;
import com.auth.assignment.model.User;
import com.auth.assignment.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @SneakyThrows
    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {

        return userRepository.findByEmailAndIsActiveTrue(login)
                .map(user -> createSecurityUser(user))
                .orElseThrow(() -> new AuthException("User " + login + " was not found."));

    }


    private org.springframework.security.core.userdetails.User createSecurityUser(User user) {
        List<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRoleName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                grantedAuthorities);
    }
}
