package com.james.memoryshots.service.impl;

import com.james.memoryshots.dao.MainRepository;
import com.james.memoryshots.dto.Album;
import com.james.memoryshots.dto.AlbumRequest;
import com.james.memoryshots.service.MainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class MainServiceImpl implements MainService {
    @Autowired
    MainRepository mainRepository;

    @Override
    public boolean insert(AlbumRequest albumRequest) throws Exception {
        boolean status = true;

        Album album = new Album();
        album.setAlbum_name(albumRequest.getAlbum_name());
        album.setAlbum_desc(albumRequest.getAlbum_desc());
        album.setCreat_date(new Date());

        Album a = mainRepository.save(album);
        Integer album_id = a.getAlbum_id();

        if(album_id == null){
            status = false;
        }
        return false;
    }

    @Override
    public boolean update(AlbumRequest albumRequest, Integer album_id) throws Exception {
        boolean status = true;

        Album a = mainRepository.findById(album_id).orElse(null);
        if(a != null){
            a.setAlbum_name(albumRequest.getAlbum_name());
            a.setAlbum_desc(albumRequest.getAlbum_desc());
            mainRepository.save(a);
        }else{
            status = false;
        }

        return false;
    }

    @Override
    public boolean delete(Integer album_id) throws Exception {
        boolean status = true;

        mainRepository.deleteById(album_id);
        Album a = mainRepository.findById(album_id).orElse(null);

        if(a != null){
            status = false;
        }

        return false;
    }

    @Override
    public Album getAlbumById(Integer album_id) throws Exception {

        Album album = mainRepository.findById(album_id).orElse(null);

        return album;
    }
}
