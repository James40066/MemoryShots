package com.james.memoryshots.service.impl;

import com.james.memoryshots.dao.AlbumDao;
import com.james.memoryshots.dao.MainRepository;
import com.james.memoryshots.dto.Album;
import com.james.memoryshots.dto.AlbumQueryParams;
import com.james.memoryshots.dto.AlbumRequest;
import com.james.memoryshots.service.MainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class MainServiceImpl implements MainService {
    @Autowired
    MainRepository mainRepository;

    @Autowired
    AlbumDao albumDao;

    @Override
    public boolean insert(AlbumRequest albumRequest,String memberId) throws Exception {
        boolean status = true;
        try{
            Album album = new Album();
            album.setMemberId(memberId);
            album.setAlbumName(albumRequest.getAlbumName());
            album.setAlbumDesc(albumRequest.getAlbumDesc());
            album.setCreatDate(new Date());

            Album a = mainRepository.save(album);
            log.warn("新增相簿id=>" + a.getAlbumId());
            log.warn("新增相簿名稱=>" + a.getAlbumName());

        }catch (Exception e){
            status = false;
        }
        return status;
    }

    @Override
    public boolean update(AlbumRequest albumRequest, Integer albumId) throws Exception {
        boolean status = true;

        Album a = mainRepository.findById(albumId).orElse(null);
        if(a != null){
            a.setAlbumName(albumRequest.getAlbumName());
            a.setAlbumDesc(albumRequest.getAlbumDesc());
            mainRepository.save(a);
        }else{
            status = false;
        }
        return status;
    }

    @Override
    public boolean delete(Integer albumId) throws Exception {
        boolean status = true;

        mainRepository.deleteById(albumId);
        Album a = mainRepository.findById(albumId).orElse(null);
        if(a != null){
            status = false;
        }
        return status;
    }

    @Override
    public Album getAlbumById(Integer albumId) throws Exception {

        Album album = mainRepository.findById(albumId).orElse(null);

        return album;
    }

    @Override
    public List<Album> findByMemberId(AlbumQueryParams albumQueryParams) throws Exception {

        List<Album> albumList = albumDao.getByMemerId(albumQueryParams);

        return albumList;
    }

    @Override
    public Integer countAlbumList(AlbumQueryParams albumQueryParams) throws Exception {

        Integer count = albumDao.countAlbumList(albumQueryParams);

        return count;
    }
}
