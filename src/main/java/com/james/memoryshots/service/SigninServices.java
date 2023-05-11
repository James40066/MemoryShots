package com.james.memoryshots.service;

import com.james.memoryshots.dto.Member;
import com.james.memoryshots.dto.MemberRequest;

public interface SigninServices {
     boolean insert(Member member) throws Exception;

     boolean update(Member member,Integer memberId) throws Exception;

     boolean delete(Integer memberId) throws Exception;

     Member getMemberById(Integer memberId) throws Exception;

     Member doLogin(MemberRequest memberRequest) throws Exception;

     Member getMemberByEmail(String email);
}
