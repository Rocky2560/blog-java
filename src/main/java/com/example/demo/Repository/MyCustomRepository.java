package com.example.demo.Repository;

import com.example.demo.model.posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyCustomRepository {

    List<posts> findByCategory(String category);

}

