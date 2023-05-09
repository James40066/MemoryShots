package com.james.memoryshots.controller;

import com.james.memoryshots.dto.AlbumRequest;
import com.james.memoryshots.dto.Member;
import com.james.memoryshots.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/MemoryShots_main")
public class MainController {
    @Autowired
    MainService mainService;

    @GetMapping("/")
    public String index(HttpSession session){
        Member member = (Member)session.getAttribute("member");
        if(member == null){
            return "signin";
        }else{
            //首頁
            return "album";
        }
    }

    @PostMapping("/creat_album")
    public ResponseEntity<?> creat_album(@RequestBody @Valid AlbumRequest albumRequest) throws Exception {
        //http://localhost:8082/creat_album
        //{"email":"a0953782057@gmail.com","pwd":"jay0519","name":"james40066"}

        boolean status = mainService.insert(albumRequest);

        if(status){
            return ResponseEntity.status(HttpStatus.CREATED).body("新增成功");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body("新增失敗");
        }
    }




}
