package com.james.memoryshots.dao.impl;

import com.james.memoryshots.dao.Album_photoDao;
import com.james.memoryshots.dto.AlbumQueryParams;
import com.james.memoryshots.dto.Album_photo;
import com.james.memoryshots.dto.Album_photoQueryParams;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class Album_photoDaoImplTest {

    @Autowired
    Album_photoDao albumPhotoDao;

    @Test
    void getByAibumId() {
        Album_photoQueryParams albumPhotoQueryParams = new Album_photoQueryParams();
        albumPhotoQueryParams.setAlbumId(13);
        albumPhotoQueryParams.setLimit(21);
        albumPhotoQueryParams.setOffset(0);
        List<Album_photo> album_photoList = albumPhotoDao.getByAibumId(albumPhotoQueryParams);

        assertTrue(album_photoList.size() !=0);
        assertEquals("87957328_2864915236909304_3582581965628375040_n.jpg",album_photoList.get(0).getFileName());
    }

    @Test
    void countAlbum_photoList() {
        Album_photoQueryParams albumPhotoQueryParams = new Album_photoQueryParams();
        albumPhotoQueryParams.setAlbumId(13);
        albumPhotoQueryParams.setLimit(21);
        albumPhotoQueryParams.setOffset(0);
        Integer count = albumPhotoDao.countAlbum_photoList(albumPhotoQueryParams);

        assertTrue(count > 0);
        assertEquals(24,count);

    }
}