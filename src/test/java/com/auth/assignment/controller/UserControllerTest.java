package com.auth.assignment.controller;

import com.auth.assignment.AuthConstants;
import com.auth.assignment.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends IntegrationTest {


    @Test
    @WithMockUser(username = "asaad@localhost.com", roles = {"USER"})
    void test_Success_fetchUserDetail() throws Exception {
        mockMvc.perform(get(AuthConstants.FETCH_CURRENT_USER_URL))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "asaad@localhost.com", roles = {"USER"})
    void test_UnAuthorizeRequest_fetchAllUserDetailsByUser() throws Exception {
        mockMvc.perform(get(AuthConstants.FETCH_ALL_USER_URL))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "asaad@localhost.com", roles = {"USER", "ADMIN"})
    void test_UnAuthorizeRequest_fetchAllUserDetailsByAdmin() throws Exception {
        mockMvc.perform(get(AuthConstants.FETCH_ALL_USER_URL))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
