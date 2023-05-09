package com.james.memoryshots.dao;

import com.james.memoryshots.dto.Album;
import org.springframework.data.repository.CrudRepository;

public interface MainRepository extends CrudRepository<Album,Integer> {


}
