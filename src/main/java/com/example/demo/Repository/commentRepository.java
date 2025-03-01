package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.comment;

import java.util.List;

public interface commentRepository extends JpaRepository<comment, Long> {
    List<comment> findByPostIdAndParentIsNullOrderByCreatedAtDesc(Long postId); // Fetch top-level comments
}
