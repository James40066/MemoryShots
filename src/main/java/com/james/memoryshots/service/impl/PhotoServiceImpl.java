package com.james.memoryshots.service.impl;

import com.james.memoryshots.dao.Album_photoDao;
import com.james.memoryshots.dao.PhotoRepository;
import com.james.memoryshots.dto.Album_photo;
import com.james.memoryshots.dto.Album_photoQueryParams;
import com.james.memoryshots.service.PhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    Album_photoDao albumPhotoDao;

    @Autowired
    ResourceLoader resourceLoader;

    @Override
    public void insert(String albumId, MultipartFile file) throws Exception {

        if (!file.getContentType().equals("image/jpeg")) {
            throw new IllegalArgumentException("只允許上傳JPG格式");
        }

        //靜態資源路徑
        String staticFolderPath = resourceLoader.getResource("classpath:static").getFile().getAbsolutePath();

        //文件夾路徑
        String folderPath = staticFolderPath  + "/album/" + albumId;
        log.warn("folderPath=>" + folderPath);
        File targetFolder = new File(folderPath);
        if (!targetFolder.exists()) {
            targetFolder.mkdirs();
        }

        //檔案名稱
        String fileName = file.getOriginalFilename();
        log.warn("folderPath + fileName=>" + folderPath +"/"+ fileName);

        //複製檔案
        File targetFile = new File(folderPath + "/" + fileName);
        file.transferTo(targetFile);

        if (targetFile.exists()) {
            log.warn("檔案已成功保存在指定路徑: " + targetFile.getAbsolutePath());
        } else {
            log.warn("檔案保存失敗");
        }

        //寫入資料庫
        Album_photo albumPhoto = new Album_photo();
        albumPhoto.setAlbumId(albumId);
        albumPhoto.setFileName(fileName);
        albumPhoto.setCreatDate(new Date());

        Album_photo a = photoRepository.save(albumPhoto);
        log.warn("新增相簿id=>" + a.getAlbumId());
        log.warn("新增照片名稱=>" + a.getFileName());

    }

    @Override
    public List<Album_photo> getAlbum_photoByAlbumId(Album_photoQueryParams albumPhotoQueryParams) throws Exception {

        List<Album_photo> album_photoList = albumPhotoDao.getByAibumId(albumPhotoQueryParams);

        return album_photoList;
    }

    @Override
    public Integer countAlbum_photoList(Album_photoQueryParams albumPhotoQueryParams) throws Exception {

        Integer count = albumPhotoDao.countAlbum_photoList(albumPhotoQueryParams);

        return count;
    }


}
