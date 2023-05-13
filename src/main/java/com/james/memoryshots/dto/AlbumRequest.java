package com.james.memoryshots.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class AlbumRequest {
    @NotNull
    @ApiModelProperty(value = "相簿名稱", required = true)
    String albumName;

    @ApiModelProperty(value = "相簿備註")
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
