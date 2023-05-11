package com.james.memoryshots.dao;

import com.james.memoryshots.dto.Album;
import com.james.memoryshots.dto.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MainRepository extends CrudRepository<Album,Integer> {

    //List<Album> findByMemberId(String memberId);

}
