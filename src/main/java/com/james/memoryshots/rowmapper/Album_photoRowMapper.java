package com.james.memoryshots.rowmapper;

import com.james.memoryshots.dto.Album_photo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Album_photoRowMapper implements RowMapper<Album_photo> {
    @Override
    public Album_photo mapRow(ResultSet resultSet, int i) throws SQLException {
        Album_photo albumPhoto = new Album_photo();
        albumPhoto.setPid(resultSet.getInt("pid"));
        albumPhoto.setAlbumId(resultSet.getString("album_id"));
        albumPhoto.setFileName(resultSet.getString("file_name"));
        albumPhoto.setCreatDate(resultSet.getDate("creat_date"));
        return albumPhoto;
    }
}
