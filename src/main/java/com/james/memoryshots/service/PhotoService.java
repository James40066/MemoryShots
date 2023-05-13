package com.james.memoryshots.service;

import com.james.memoryshots.dto.AlbumQueryParams;
import com.james.memoryshots.dto.Album_photo;
import com.james.memoryshots.dto.Album_photoQueryParams;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotoService {

    boolean insert(String albumId, MultipartFile file) throws Exception;

    List<Album_photo> getAlbum_photoByAlbumId(Album_photoQueryParams albumPhotoQueryParams) throws Exception;

    Integer countAlbum_photoList(Album_photoQueryParams albumPhotoQueryParams)throws Exception;

}
