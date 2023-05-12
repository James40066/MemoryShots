package com.james.memoryshots.controller;

import com.james.memoryshots.dto.Album_photo;
import com.james.memoryshots.dto.Album_photoQueryParams;
import com.james.memoryshots.dto.Member;
import com.james.memoryshots.service.PhotoService;
import com.james.memoryshots.util.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/MemoryShots_photo")
public class PhotoController {
    @Autowired
    PhotoService photoService;


    @GetMapping("/{albumId}")
    public String index(HttpSession session, Model model, @PathVariable(required = true) int albumId){
        Member member = (Member)session.getAttribute("member");
        if(member == null){
            return "signin";//重新登入
        }else{
            //首頁
            model.addAttribute("member_id", member.getMemberId());
            model.addAttribute("member_Name", member.getName());
            model.addAttribute("albumId", albumId);
            return "photo";//相簿主頁
        }
    }

    @PostMapping("/upload/{albumId}")
    public ResponseEntity<?> uploadFile(@PathVariable(required = true) String albumId, @RequestParam("files") MultipartFile[] files) {
        try {
            for (MultipartFile file : files) {
                log.warn("file.getName()=>" + file.getOriginalFilename());
                photoService.insert(albumId,file);
            }
            return ResponseEntity.status(HttpStatus.OK).body("圖片上傳成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("圖片上傳失敗");
        }
    }

    @GetMapping("/getAlbum")
    public ResponseEntity<?> getAlbum(@RequestParam(required = true) int albumId,
                                      @RequestParam(defaultValue = "21") @Max(10) @Min(0) int limit,
                                      @RequestParam(defaultValue = "0") @Min(0) int offset
                                      ) throws Exception{
        //http://localhost:8082/MemoryShots_photo/getAlbum?albumId=13&offset=0
        Album_photoQueryParams albumPhotoQueryParams = new Album_photoQueryParams();
        albumPhotoQueryParams.setAlbumId(albumId);
        albumPhotoQueryParams.setLimit(limit);
        albumPhotoQueryParams.setOffset(offset);

        List<Album_photo> album_photoList = photoService.getAlbum_photoByAlbumId(albumPhotoQueryParams);

        Integer total = photoService.countAlbum_photoList(albumPhotoQueryParams);

        Page<Album_photo> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(album_photoList);

        if(album_photoList.size() > 0){
            return ResponseEntity.status(HttpStatus.OK).body(page);
        }else{
            return ResponseEntity.status(HttpStatus.OK).body("查無資料");
        }

    }


}
