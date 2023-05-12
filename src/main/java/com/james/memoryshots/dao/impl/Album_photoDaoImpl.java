package com.james.memoryshots.dao.impl;

import com.james.memoryshots.dao.AlbumDao;
import com.james.memoryshots.dao.Album_photoDao;
import com.james.memoryshots.dto.Album;
import com.james.memoryshots.dto.AlbumQueryParams;
import com.james.memoryshots.dto.Album_photo;
import com.james.memoryshots.dto.Album_photoQueryParams;
import com.james.memoryshots.rowmapper.AlbumRowMapper;
import com.james.memoryshots.rowmapper.Album_photoRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@Component
public class Album_photoDaoImpl implements Album_photoDao {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Album_photo> getByAibumId(Album_photoQueryParams albumPhotoQueryParams) {
        Map<String,Object> map = new HashMap<>();

        String sql = "SELECT pid,album_id,file_name,creat_date FROM album_photo WHERE album_id = :album_id";

        //sort條件
        sql += " ORDER BY pid DESC limit :limit offset :offset";

        log.warn("getByAibumId_sql=>" + sql);

        map.put("album_id",albumPhotoQueryParams.getAlbumId());
        map.put("limit",albumPhotoQueryParams.getLimit());
        map.put("offset",albumPhotoQueryParams.getOffset());

        List<Album_photo> album_photoList = namedParameterJdbcTemplate.query(sql,map,new Album_photoRowMapper());

        return album_photoList;
    }

    @Override
    public Integer countAlbum_photoList(Album_photoQueryParams albumPhotoQueryParams) {

        String sql = "SELECT count(*) FROM album_photo WHERE album_id = :album_id ORDER BY album_id DESC";
        log.warn("countAlbum_photoList_sql=>" + sql);

        Map<String,Object> map = new HashMap<>();
        map.put("album_id",albumPhotoQueryParams.getAlbumId());

        Integer Count = namedParameterJdbcTemplate.queryForObject(sql,map,Integer.class);

        return Count;
    }

}
