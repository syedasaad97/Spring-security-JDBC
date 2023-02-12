package com.auth.assignment.controller;

import com.auth.assignment.dto.UserDto;
import com.auth.assignment.service.UserService;
import com.auth.assignment.util.CommonUtils;
import com.auth.assignment.util.Constants;
import com.auth.assignment.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.message.AuthException;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    @PreAuthorize(Constants.IS_USER_OR_ADMIN)
    public ResponseEntity<UserDto> getCurrentUser() throws AuthException {
        String email = SecurityUtils.dgetCurrentUserLogin().orElseThrow(() -> new AuthException("User details not found"));
        UserDto userDto = userService.getUserDetail(email);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("/all")
    @PreAuthorize(Constants.IS_ADMIN)
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtos = userService.fetchAllUsers();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }
}
