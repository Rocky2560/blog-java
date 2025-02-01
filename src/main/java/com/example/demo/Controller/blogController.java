package com.example.demo.Controller;

import com.example.demo.Service.postService;
import com.example.demo.model.posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class blogController {

    private static final String UPLOAD_DIR = "uploads/";
    @Autowired
    postService postService;

    //Pointing tto the index page
    @GetMapping("/admin")
    public String adminLogin(Model model)
    {
        List<posts> posts = postService.getAlllistPosts();
        model.addAttribute("posts", posts);
        return "edit";
    }
    //Showing the post details
    @GetMapping("/postDetails")
    public String  postDetails(@RequestParam(value = "id", required = false) Long id, Model model)
    {
        List<posts> posts = postService.getAlllistPosts();
        model.addAttribute("posts", posts);
        return "edit";
    }


    //Getting the id for the edit process
    @GetMapping("/posts/{postid}")
            public String deletePost(@PathVariable ("postid") int postid)
    {
        postService.deletePosts(postid);
        return "redirect:/api/postDetails";
    }

    //Posting changes via id for the edit process
    @PostMapping("/addPost")
    public String addPost(@RequestParam("title") String title, @RequestParam("description") String description,@RequestParam("category") String category, @RequestParam("image")MultipartFile image, RedirectAttributes redirectAttributes) {
        // Handle the form data
        try
        {
            posts posts = new posts();
            posts.setTitle(title);
            posts.setDescription(description);
            posts.setCategory(category);
            postService.saveorUpdate(posts, image);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        postService.saveorUpdate(posts);
        return "edit    ";
    }

    @GetMapping("editPost/{id}")
    public  ResponseEntity<posts>  editPost(@PathVariable("id") int id, Model model)
    {
        posts post = postService.getPostById(id);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(post);
    }
    @PostMapping("editPost")
    public String updateExpense(@RequestParam("id") int id, @RequestParam("title") String title, @RequestParam("description")
                                    String description, @RequestParam("category") String category,
                                @RequestParam("image1") MultipartFile image,
                                RedirectAttributes redirectAttributes) throws IOException {

        try {
            posts posts = postService.getPostById(id);
//            posts.setTitle(title);
//            posts.setDescription(description);
//            posts.setCategory(category);
            postService.saveorUpdate(posts, image);
            return "redirect:/api/postDetails";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
