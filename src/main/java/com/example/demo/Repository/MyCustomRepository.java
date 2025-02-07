package com.example.demo.Repository;

import com.example.demo.model.posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MyCustomRepository {

    @Query("SELECT p FROM posts p WHERE p.category = :category")
    Page<posts> findByCategory(String category, Pageable pageable);
}

