package com.james.memoryshots.dao;

import com.james.memoryshots.dto.Album;
import com.james.memoryshots.dto.AlbumQueryParams;
import io.swagger.models.auth.In;

import java.util.List;

public interface AlbumDao {
    List<Album> getByMemerId(AlbumQueryParams albumQueryParams);

    Integer countAlbumList(AlbumQueryParams albumQueryParams);
}
