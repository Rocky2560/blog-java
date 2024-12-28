package com.example.demo.Controller;

import com.example.demo.Service.postService;
import com.example.demo.model.posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/api")
public class blogController {

    @Autowired
    postService postService;

    @GetMapping("/admin")
    public String adminLogin()
    {
        return "index";
    }

    @GetMapping("/postDetails")
    public String  postDetails(Model model)
    {

        List<posts> posts = postService.getAlllistPosts();
        model.addAttribute("posts",posts);
        return "edit";
    }

    @GetMapping("/posts")
    public List<posts> getAllPosts()
    {
        return postService.getAlllistPosts();
    }

    @GetMapping("/posts/{postid}")
            public posts getPosts(@PathVariable("postid") int postid)
    {
        return postService.getPostById(postid);
    }

    @DeleteMapping("/posts/{postid}")
            public void deletePost(@PathVariable ("postid") int postid)
    {
        postService.deletePosts(postid);
    }
    @PostMapping("/addPost")
    public String addPost(@ModelAttribute posts posts) {
        // Handle the form data
        postService.saveorUpdate(posts);
        return "index";
    }

//    @PostMapping("/addPost")
//    public String savePosts (@RequestBody posts posts)
//    {
//        System.out.println(posts);
//        postService.saveorUpdate(posts);
//        return "index";
//    }

    @PutMapping("/posts")
    public posts update (@RequestBody posts posts)
    {
        postService.saveorUpdate(posts);
        return posts;
    }
}
