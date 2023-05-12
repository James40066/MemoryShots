package com.james.memoryshots.dao;

import com.james.memoryshots.dto.Album_photo;
import com.james.memoryshots.dto.Album_photoQueryParams;

import java.util.List;

public interface Album_photoDao {
    List<Album_photo> getByAibumId(Album_photoQueryParams albumPhotoQueryParams);

    Integer countAlbum_photoList(Album_photoQueryParams albumPhotoQueryParams);
}
