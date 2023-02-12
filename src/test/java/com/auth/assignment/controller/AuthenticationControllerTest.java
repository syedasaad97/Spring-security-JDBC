package com.auth.assignment.controller;

import com.auth.assignment.AuthConstants;
import com.auth.assignment.IntegrationTest;
import com.auth.assignment.dto.AuthRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



class AuthenticationControllerTest extends IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void test_Success_authenticateRequest() throws Exception {
        AuthRequest authRequest = createAuthRequest(AuthConstants.login_email,AuthConstants.login_password);
        String content = toJsonString(objectMapper, authRequest);
        mockMvc.perform(post(AuthConstants.authenticateURL).content(content).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    void test_UnAuthorizeRequest_authenticateRequest() throws Exception {
        AuthRequest authRequest = createAuthRequest("test@gmail.com","123");
        String content = toJsonString(objectMapper, authRequest);
        mockMvc.perform(post(AuthConstants.authenticateURL).content(content).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }


}