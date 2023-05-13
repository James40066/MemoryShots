package com.james.memoryshots.controller;

import com.james.memoryshots.dto.AlbumRequest;
import com.james.memoryshots.dto.Member;
import com.james.memoryshots.service.SigninService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
@Slf4j
@Controller
@RequestMapping("/MemoryShots_member")
public class MemberController {

    @Autowired
    SigninService signinService;

    @GetMapping("/")
    @ApiIgnore
    public String index(HttpSession session, Model model){
        Member member = (Member)session.getAttribute("member");
        if(member == null){
            return "signin";//重新登入
        }else{
            //首頁
            model.addAttribute("member_id", member.getMemberId());
            model.addAttribute("member_Name", member.getName());
            return "member";//相簿主頁
        }
    }

    @GetMapping("/get_member/{memberId}")
    @ApiOperation("取得會員資料")
    public ResponseEntity<?> get_member(
            @ApiParam(required = true, value = "會員ID") @PathVariable int memberId
    ) throws Exception {
        //http://localhost:8082/MemoryShots_member/get_member/6

        Member member = signinService.getMemberById(memberId);
        member.setPwd(null);

        if(member != null){
            return ResponseEntity.status(HttpStatus.OK).body(member);
        }else{
            return ResponseEntity.status(HttpStatus.OK).body("查無資料");
        }
    }

    @PutMapping("/update_ember/{memberId}")
    @ApiOperation("更新會員資料")
    public ResponseEntity<?> update_album(
            @ApiParam(required = true, value = "會員資料") @RequestBody @Valid Member member,
            @ApiParam(required = true, value = "會員ID") @PathVariable int memberId
    ) throws Exception {
        //http://localhost:8082/update_ember/6
        //{"name":"james40066","email":"a123456789@gmail.com","pwd":"jay0814"}

        Member dbmember = signinService.getMemberById(memberId);

        if(member.getPwd() == ""){
            member.setPwd(dbmember.getPwd());
        }

        boolean status = signinService.update(member,memberId);

        if(status){
            return ResponseEntity.status(HttpStatus.OK).body("更新成功");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body("更新失敗");
        }
    }

}
