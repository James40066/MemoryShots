package com.james.memoryshots.rowmapper;

import com.james.memoryshots.dto.Album;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AlbumRowMapper implements RowMapper<Album> {
    @Override
    public Album mapRow(ResultSet resultSet, int i) throws SQLException {
        Album album = new Album();
        album.setAlbumId(resultSet.getInt("album_id"));
        album.setMemberId(resultSet.getString("member_id"));
        album.setAlbumName(resultSet.getString("album_name"));
        album.setAlbumDesc(resultSet.getString("album_desc"));
        album.setCreatDate(resultSet.getDate("creat_date"));
        return album;
    }
}
