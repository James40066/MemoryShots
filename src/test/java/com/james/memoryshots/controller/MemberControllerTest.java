package com.james.memoryshots.controller;

import org.hamcrest.Matchers;
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
class MemberControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void get_member() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/MemoryShots_member/get_member/{memberId}",6);

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.equalTo("a123456789@gmail.com")));
    }

    @Test
    void update_album() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/MemoryShots_member/update_ember/{memberId}",6)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"james40066\",\"email\":\"a123456789@gmail.com\",\"pwd\":\"jay0814\"}");

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}