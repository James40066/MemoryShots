package com.james.memoryshots.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class AlbumRequest {
    @NotNull
    String albumName;

    String albumDesc;

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumDesc() {
        return albumDesc;
    }

    public void setAlbumDesc(String albumDesc) {
        this.albumDesc = albumDesc;
    }
}
