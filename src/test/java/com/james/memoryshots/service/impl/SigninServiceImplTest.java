package com.james.memoryshots.service.impl;

import com.james.memoryshots.dao.SigninRepository;
import com.james.memoryshots.dto.Member;
import com.james.memoryshots.dto.MemberRequest;
import com.james.memoryshots.service.SigninService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SigninServiceImplTest {
    @Autowired
    SigninService signinService;

    @Test
    void insert() throws Exception {
        Member member = new Member();
        member.setName("john");
        member.setEmail("a0987654321@gmail.com");
        member.setPwd("12345");

        boolean status = signinService.insert(member);
        assertTrue(status);
    }

    @Test
    void update() throws Exception {
        Member member = new Member();
        member.setName("john");
        member.setEmail("a0987654321@gmail.com");
        member.setPwd("12345");

        boolean status = signinService.update(member,7);
        assertTrue(status);
    }

    @Test
    void delete() throws Exception {
        boolean status = signinService.delete(7);
        assertTrue(status);
    }

    @Test
    void getMemberById() throws Exception {
        Member member = signinService.getMemberById(7);
        assertEquals("a987654321@gmail.com",member.getEmail());
    }

    @Test
    void doLogin() throws Exception {
        MemberRequest memberRequest = new MemberRequest();
        memberRequest.setEmail("a987654321@gmail.com");
        memberRequest.setPwd("jay0519");
        Member member = signinService.doLogin(memberRequest);

        assertNotNull(member);
        assertEquals("a987654321@gmail.com",member.getEmail());
    }

    @Test
    void getMemberByEmail() {
        Member member = signinService.getMemberByEmail("a987654321@gmail.com");
        assertNotNull(member);
        assertEquals("james40066",member.getName());
    }
}