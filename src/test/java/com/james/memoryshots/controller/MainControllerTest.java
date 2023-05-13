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
class MainControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void creat_album() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/MemoryShots_main/creat_album/{memberId}",6)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"albumName\":\"大阪自由行16\",\"albumDesc\":\"2023/03/02\"}");

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(201));
    }

    @Test
    void del_album() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/MemoryShots_main/del_album/{albumId}",13);

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void update_album() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/MemoryShots_main/update_album/{albumId}",13)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"albumName\":\"大阪自由行8\",\"albumDesc\":\"2023/03/02\"}");

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void find_album() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/MemoryShots_main/find_album?memberId=6&offset=0");

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.total", Matchers.equalTo(13)));

    }
}