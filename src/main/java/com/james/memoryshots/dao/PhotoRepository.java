package com.james.memoryshots.dao;

import com.james.memoryshots.dto.Album_photo;
import org.springframework.data.repository.CrudRepository;

public interface PhotoRepository extends CrudRepository<Album_photo,Integer> {

}
