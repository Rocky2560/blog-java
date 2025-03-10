package com.example.demo.Repository;

import com.example.demo.model.posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface userpostsRepository extends JpaRepository<posts, Integer>, MyCustomRepository {
    List<posts> findTop5ByOrderByCreatedateDesc();
    // Fetch posts containing a specific keyword in the title (case-insensitive)
    Page<posts> findByTitleContainingIgnoreCase(String title, Pageable pageable);


    Page<posts> findByCategory(String category, Pageable pageable);


    Page<posts> findAll(Pageable pageable);

}
