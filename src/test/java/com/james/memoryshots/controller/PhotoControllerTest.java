package com.james.memoryshots.controller;

import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.File;
import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class PhotoControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ResourceLoader resourceLoader;

    @Test
    void uploadFile() throws Exception {

        String staticFolderPath = resourceLoader.getResource("classpath:static").getFile().getAbsolutePath();
        File file = new File(staticFolderPath + "/img/0001.jpg");

        //log.warn("file.exists()=> "+ file.exists());

        FileInputStream fi = new FileInputStream(file);
        MockMultipartFile mockMultipartFile = new MockMultipartFile("files",file.getName(),"image/jpeg",fi);

        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/MemoryShots_photo/upload/{albumId}",13)
                .file(mockMultipartFile))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getAlbum() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/MemoryShots_photo/getAlbum?albumId=13&offset=0");

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.total", Matchers.equalTo(24)));
    }

    @Test
    void get_album_info() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/MemoryShots_photo/get_album_info/{albumId}",13);

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.albumDesc", Matchers.equalTo("2023/03/18")));
    }


}