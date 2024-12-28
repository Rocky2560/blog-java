package com.example.demo.Service;

import com.example.demo.Repository.userpostsRepository;
import com.example.demo.model.posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class postService {

    @Autowired
    userpostsRepository userpostsRepository;

    public List<posts> getAlllistPosts ()
    {
        List<posts> post = new ArrayList<>();
        userpostsRepository.findAll().forEach(posts1 -> post.add(posts1));
        return post;
    }

    public posts getPostById(int id)
    {
        return userpostsRepository.findById(id).get();
    }

    public void saveorUpdate(posts posts)
    {
        userpostsRepository.save(posts);
    }

    public void deletePosts(int id)
    {
        userpostsRepository.deleteById(id);
    }

    public void update (posts posts, int id)
    {
        userpostsRepository.save(posts);
    }
}
