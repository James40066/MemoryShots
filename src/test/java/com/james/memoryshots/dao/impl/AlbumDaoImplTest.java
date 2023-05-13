package com.james.memoryshots.dao.impl;

import com.james.memoryshots.dao.AlbumDao;
import com.james.memoryshots.dto.Album;
import com.james.memoryshots.dto.AlbumQueryParams;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AlbumDaoImplTest {
    @Autowired
    AlbumDao albumDao;

    @Test
    void getByMemerId() {
        AlbumQueryParams albumQueryParams = new AlbumQueryParams();
        albumQueryParams.setMemberId("6");
        albumQueryParams.setLimit(10);
        albumQueryParams.setOffset(0);
        List<Album> albumList =  albumDao.getByMemerId(albumQueryParams);

        assertNotNull(albumList);
        assertEquals("大阪自由行14",albumList.get(0).getAlbumName());
    }

    @Test
    void countAlbumList() {
        AlbumQueryParams albumQueryParams = new AlbumQueryParams();
        albumQueryParams.setMemberId("6");
        albumQueryParams.setLimit(10);
        albumQueryParams.setOffset(0);

        Integer count =  albumDao.countAlbumList(albumQueryParams);
        assertTrue(count > 0);
        assertEquals(13,count);
    }
}