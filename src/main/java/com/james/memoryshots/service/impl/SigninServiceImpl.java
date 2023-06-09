package com.james.memoryshots.service.impl;

import com.james.memoryshots.dto.Member;
import com.james.memoryshots.dao.SigninRepository;
import com.james.memoryshots.dto.MemberRequest;
import com.james.memoryshots.service.SigninService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;



@Component
@Slf4j
public class SigninServiceImpl implements SigninService {
    @Autowired
    SigninRepository signinRepository;

    private BCryptPasswordEncoder  passwordEncoder;

    public SigninServiceImpl(){
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public boolean insert(Member member) throws Exception{
        boolean status = true;
        Member m = signinRepository.findByEmail(member.getEmail()).orElse(null);

        if(m == null){
            //進行密碼加密
            member.setPwd(passwordEncoder.encode(member.getPwd()));

            Member db_member = signinRepository.save(member);
            Integer memberId = db_member.getMemberId();
            log.warn("new_member_id=>" + memberId);
        }else{
            status = false;
        }
        return status;
    }

    @Override
    public boolean update(Member member, Integer memberId) throws Exception {
        boolean status = true;

        Member m = signinRepository.findById(memberId).orElse(null);
        if(m != null){
            m.setEmail(member.getEmail());
            m.setPwd(passwordEncoder.encode(member.getPwd()));
            m.setName(member.getName());
            signinRepository.save(m);
        }else{
            status = false;
        }

        return status;
    }

    @Override
    public boolean delete(Integer memberId) throws Exception {
        boolean status = true;
        signinRepository.deleteById(memberId);
        Member m = signinRepository.findById(memberId).orElse(null);

        if(m != null){
            status = false;
        }

        return status;
    }

    @Override
    public Member getMemberById(Integer memberId) throws Exception{

        Member member = signinRepository.findById(memberId).orElse(null);

        return member;
    }

    @Override
    public Member doLogin(MemberRequest memberRequest) throws Exception{
        Member m = signinRepository.findByEmail(memberRequest.getEmail()).orElse(null);

        if(m == null){
            log.warn("該email {} 尚未註冊",memberRequest.getEmail());
            //throw  new ResponseStatusException(HttpStatus.BAD_REQUEST);
            return null;
        }

        log.warn("登入帳號=>" + memberRequest.getEmail());
        log.warn("登入密碼比對=>"+passwordEncoder.matches(memberRequest.getPwd(),m.getPwd()));

        if(!passwordEncoder.matches(memberRequest.getPwd(),m.getPwd())){
            log.warn("該email {} 的密碼不正確",memberRequest.getEmail());
            //throw  new ResponseStatusException(HttpStatus.BAD_REQUEST);
            return null;
        }
        return m;
    }

    @Override
    public Member getMemberByEmail(String Email)  {

        Member member = signinRepository.findByEmail(Email).orElse(null);

        return member;
    }
}
