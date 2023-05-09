package com.james.memoryshots.service;

import com.james.memoryshots.dto.Member;
import com.james.memoryshots.dto.MemberRequest;

public interface SigninServices {
     boolean insert(Member member) throws Exception;

     boolean update(Member member,Integer member_id) throws Exception;

     boolean delete(Integer member_id) throws Exception;

     Member getMemberById(Integer member_id) throws Exception;

     Member doLogin(MemberRequest memberRequest) throws Exception;

     Member getMemberByEmail(String Email);
}
