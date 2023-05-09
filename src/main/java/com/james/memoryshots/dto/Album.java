package com.james.memoryshots.dto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Entity
@Table(name = "album")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id")
    int album_id;

    @NotNull
    @Column(name = "member_id")
    String member_id;

    @NotNull
    @Column(name = "album_name")
    String album_name;

    @Column(name = "album_desc")
    String album_desc;

    @NotNull
    @Column(name = "creat_date")
    Date creat_date;

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

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

    public Date getCreat_date() {
        return creat_date;
    }

    public void setCreat_date(Date creat_date) {
        this.creat_date = creat_date;
    }
}
