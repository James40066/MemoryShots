package com.james.memoryshots.dao;

import com.james.memoryshots.dto.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SigninRepository extends CrudRepository<Member,Integer> {

    Optional<Member> findByEmail(String email);

}
