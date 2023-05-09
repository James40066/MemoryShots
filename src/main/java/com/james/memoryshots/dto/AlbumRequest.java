package com.james.memoryshots.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class AlbumRequest {
    @NotNull
    @Email
    String album_name;

    String album_desc;

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public String getAlbum_desc() {
        return album_desc;
    }

    public void setAlbum_desc(String album_desc) {
        this.album_desc = album_desc;
    }
}
