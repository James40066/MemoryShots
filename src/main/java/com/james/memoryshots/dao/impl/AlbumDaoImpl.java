package com.james.memoryshots.dao.impl;

import com.james.memoryshots.dao.AlbumDao;
import com.james.memoryshots.dto.Album;
import com.james.memoryshots.dto.AlbumQueryParams;
import com.james.memoryshots.rowmapper.AlbumRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class AlbumDaoImpl implements AlbumDao {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public List<Album> getByMemerId(AlbumQueryParams albumQueryParams) {
        Map<String,Object> map = new HashMap<>();

        String sql = "SELECT album_id,member_id,album_name,album_desc,creat_date FROM album WHERE member_id = :memberId";

        //查詢條件
        if(albumQueryParams.getSearch() != null) {
            sql += " AND (album_name LIKE '%" + albumQueryParams.getSearch() + "%' OR album_desc Like '%" + albumQueryParams.getSearch() + "%')";
        }

        //sort條件
        sql += " ORDER BY album_id DESC limit :limit offset :offset";

        log.warn("getByMemerId_sql=>" + sql);

        map.put("memberId",albumQueryParams.getMemberId());
        map.put("limit",albumQueryParams.getLimit());
        map.put("offset",albumQueryParams.getOffset());

        List<Album> albumList = namedParameterJdbcTemplate.query(sql,map,new AlbumRowMapper());

        return albumList;
    }

    @Override
    public Integer countAlbumList(AlbumQueryParams albumQueryParams) {
        Map<String,Object> map = new HashMap<>();

        String sql = "SELECT count(*) FROM album WHERE member_id = :memberId";

        //查詢條件
        if(albumQueryParams.getSearch() != null) {
            sql += " AND (album_name LIKE '%" + albumQueryParams.getSearch() + "%' OR album_desc Like '%" + albumQueryParams.getSearch() + "%')";
        }

        log.warn("countAlbumList_sql=>" + sql);

        map.put("memberId",albumQueryParams.getMemberId());

        Integer count = namedParameterJdbcTemplate.queryForObject(sql,map,Integer.class);

        return count;
    }


}
