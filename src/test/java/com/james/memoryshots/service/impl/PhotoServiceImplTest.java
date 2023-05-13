package com.james.memoryshots.service.impl;

import com.james.memoryshots.dto.Album_photo;
import com.james.memoryshots.dto.Album_photoQueryParams;
import com.james.memoryshots.service.PhotoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PhotoServiceImplTest {
    @Autowired
    PhotoService photoService;

    @Autowired
    ResourceLoader resourceLoader;

    @Test
    void insert() throws Exception {
        String staticFolderPath = resourceLoader.getResource("classpath:static").getFile().getAbsolutePath();

        File file = new File(staticFolderPath + "/img/0001.jpg");
        MultipartFile mufile = new MockMultipartFile("0001.jpg", "0001.jpg","image/jpeg" ,new FileInputStream(file));

        boolean status =  photoService.insert("13",mufile);
        assertTrue(status);
    }
    @Test
    void getAlbum_photoByAlbumId() throws Exception {
        Album_photoQueryParams albumPhotoQueryParams = new Album_photoQueryParams();
        albumPhotoQueryParams.setAlbumId(13);
        albumPhotoQueryParams.setLimit(21);
        albumPhotoQueryParams.setOffset(0);
        List<Album_photo> album_photoList = photoService.getAlbum_photoByAlbumId(albumPhotoQueryParams);

        assertNotNull(album_photoList);
        assertEquals("87957328_2864915236909304_3582581965628375040_n.jpg",album_photoList.get(0).getFileName());
    }

    @Test
    void countAlbum_photoList() throws Exception {
        Album_photoQueryParams albumPhotoQueryParams = new Album_photoQueryParams();
        albumPhotoQueryParams.setAlbumId(13);
        albumPhotoQueryParams.setLimit(21);
        albumPhotoQueryParams.setOffset(0);
        Integer count = photoService.countAlbum_photoList(albumPhotoQueryParams);

        assertNotNull(count);
        assertEquals(24,count);
    }
}