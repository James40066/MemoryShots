package com.james.memoryshots.service;

import com.james.memoryshots.dto.Album;
import com.james.memoryshots.dto.AlbumRequest;
import com.james.memoryshots.dto.Member;

public interface MainService {
    boolean insert(AlbumRequest albumRequest) throws Exception;

    boolean update(AlbumRequest albumRequest,Integer album_id) throws Exception;

    boolean delete(Integer album_id) throws Exception;

    Album getAlbumById(Integer album_id) throws Exception;

}
