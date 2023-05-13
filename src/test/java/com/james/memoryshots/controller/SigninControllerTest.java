package com.james.memoryshots.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
class SigninControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void doLogin() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/MemoryShots/doSignIn")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"a123456789@gmail.com\",\"pwd\":\"jay0814\"}");

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200));

    }

    @Test
    void doSignUp() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/MemoryShots/doSignUp")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"a0953782057@gmail.com\",\"pwd\":\"jay0519\",\"name\":\"james40066\"}");

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(201));
    }

}