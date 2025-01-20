package com.example.demo.Repository;

import com.example.demo.model.posts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userpostsRepository extends CrudRepository <posts, Integer>{
}
