package com.example.demo.Repository;

import com.example.demo.model.posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface userpostsRepository extends JpaRepository<posts, Integer>, MyCustomRepository {
    public interface PostRepository extends JpaRepository<posts, Long> {
        Page<posts> findByCategory(String category, Pageable pageable);
    }
}
