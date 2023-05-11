package com.james.memoryshots.service;

import com.james.memoryshots.dto.Album;
import com.james.memoryshots.dto.AlbumQueryParams;
import com.james.memoryshots.dto.AlbumRequest;
import com.james.memoryshots.dto.Member;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface MainService {
    boolean insert(AlbumRequest albumRequest,String memberId) throws Exception;

    boolean update(AlbumRequest albumRequest,Integer albumId) throws Exception;

    boolean delete(Integer albumId) throws Exception;

    Album getAlbumById(Integer albumId) throws Exception;

    List<Album> findByMemberId(AlbumQueryParams albumQueryParams) throws Exception;

    Integer countAlbumList(AlbumQueryParams albumQueryParams)throws Exception;

}
