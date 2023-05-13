package com.james.memoryshots.service.impl;

import com.james.memoryshots.dto.Album;
import com.james.memoryshots.dto.AlbumQueryParams;
import com.james.memoryshots.dto.AlbumRequest;
import com.james.memoryshots.service.MainService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MainServiceImplTest {
    @Autowired
    MainService mainService;

    @Test
    void insert() throws Exception {
        AlbumRequest albumRequest = new AlbumRequest();
        albumRequest.setAlbumName("東京自由行");
        albumRequest.setAlbumDesc("2022/02/25");

        boolean status = mainService.insert(albumRequest,"6");
        assertTrue(status);
    }

    @Test
    void update() throws Exception {
        AlbumRequest albumRequest = new AlbumRequest();
        albumRequest.setAlbumName("東京自由行");
        albumRequest.setAlbumDesc("2022/02/25");

        boolean status = mainService.update(albumRequest,2);
        assertTrue(status);
    }

    @Test
    void delete() throws Exception {
        boolean status = mainService.delete(2);
        assertTrue(status);
    }

    @Test
    void getAlbumById() throws Exception {
        Album album = mainService.getAlbumById(3);
        assertNotNull(album);
        assertEquals("大阪自由行3",album.getAlbumName());
    }

    @Test
    void findByMemberId() throws Exception {
        AlbumQueryParams albumQueryParams = new AlbumQueryParams();
        albumQueryParams.setMemberId("6");
        albumQueryParams.setLimit(10);
        albumQueryParams.setOffset(0);
        List<Album> albumList = mainService.findByMemberId(albumQueryParams);
        assertNotNull(albumList);
        assertEquals("大阪自由行14",albumList.get(0).getAlbumName());
    }

    @Test
    void countAlbumList() throws Exception {
        AlbumQueryParams albumQueryParams = new AlbumQueryParams();
        albumQueryParams.setMemberId("6");
        albumQueryParams.setLimit(10);
        albumQueryParams.setOffset(0);
        Integer count = mainService.countAlbumList(albumQueryParams);
        assertNotNull(count);
        assertEquals(13,count);
    }
}