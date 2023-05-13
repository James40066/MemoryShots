package com.james.memoryshots.controller;

import com.james.memoryshots.dto.Member;
import com.james.memoryshots.dto.MemberRequest;
import com.james.memoryshots.service.SigninService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/MemoryShots")
public class SigninController {
    //http://localhost:8082/swagger-ui/index.html
    //http://localhost:8082/MemoryShots/

    @Autowired
    SigninService signinService;

    @GetMapping("/")
    @ApiIgnore
    public String index(){
        //首頁
        return "index";
    }

    @GetMapping("/signIn")
    @ApiIgnore
    public String signIn(){
        //登入頁
        return "signin";
    }

    @GetMapping("/signUp")
    @ApiIgnore
    public String signUp(){
        //註冊頁
        return "signup";
    }

    @PostMapping("/doSignIn")
    @ApiOperation("進行會員登入")
    public ResponseEntity<?> doLogin(
            @ApiParam(required = true, value = "會員資料") @RequestBody @Valid MemberRequest memberRequest,
            HttpSession session
    ) throws Exception {
        //http://localhost:8082/MemoryShots/doSignIn
        //{"email":"a123456789@gmail.com","pwd":"jay0814"}

        log.warn("EMAIL=>" + memberRequest.getEmail());
        log.warn("PWD=>" + memberRequest.getPwd());

        Member member = signinService.doLogin(memberRequest);
        if(member != null){
            session.setAttribute("member",member);
            return ResponseEntity.status(HttpStatus.OK).body("登入成功");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body("登入失敗，帳號或密碼錯誤");
        }
    }

    @PostMapping("/doSignUp")
    @ApiOperation("進行會員註冊")
    public ResponseEntity<?> doSignUp(
            @ApiParam(required = true, value = "會員資料") @RequestBody @Valid Member member
    ) throws Exception {
        //http://localhost:8082/doSignUp
        //{"email":"a0953782057@gmail.com","pwd":"jay0519","name":"james40066"}
        boolean status = signinService.insert(member);
        if(status){
            return ResponseEntity.status(HttpStatus.CREATED).body("註冊成功");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body("註冊失敗，該email已被註冊");
        }
    }

    @GetMapping("/doLogOut")
    @ApiIgnore
    public String doLogOut(HttpSession session) throws Exception {

        if(session.getAttribute("member") != null){
            session.removeAttribute("member");
        }

        return "redirect:/MemoryShots/signIn";
    }



}
