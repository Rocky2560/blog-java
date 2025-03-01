package com.example.demo.Service;

import com.example.demo.Repository.commentRepository;
import com.example.demo.model.comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class commentService {

    @Autowired
    private commentRepository commentRepository;

    public List<comment> getCommetsByPostId(Long postId)
    {
        return commentRepository.findByPostIdAndParentIsNullOrderByCreatedAtDesc(postId);
    }

    public comment saveComment(comment comment, Long parentId)
    {
        if (parentId != null)
        {
            Optional<comment> parentComment = commentRepository.findById(parentId);
            parentComment.ifPresent(comment::setParent);
        }
        return commentRepository.save(comment);
    }
}
