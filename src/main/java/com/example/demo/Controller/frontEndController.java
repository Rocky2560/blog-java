package com.example.demo.Controller;

import com.example.demo.Service.postService;
import com.example.demo.model.posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.Service.postService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class frontEndController {

    @Autowired
    postService postService;

    @GetMapping(value = "/test")
    public String index(Model model)
    {
        List<posts> posts = postService.getAlllistPosts();
        model.addAttribute("posts", posts);
        return "index";
    }

    @GetMapping(value = "/details")
    public String postdetails(@RequestParam(value = "postid") String id, Model model) {
        int idd = Integer.parseInt(id);
        posts posts =  postService.getPostById(idd);
        model.addAttribute("posts", posts);
        return "garden-single";  // This returns the 'garden-single' view with the post data
    }

    @GetMapping("/categoryDetails/{category}")
    public ResponseEntity<List<posts>> getPostsByCategory(@PathVariable String category) {
        System.out.println(category);
        return ResponseEntity.ok(postService.getPostsByCategory(category));
    }

//    @GetMapping(value = "/categoryDetails")
//    public String categorydetails(@RequestParam(value = "category") String category, Model model) {
//        posts posts = (com.example.demo.model.posts) postService.getPostsByCategory(category);
//        model.addAttribute("posts", posts);
//        return "garden-single";  // This returns the 'garden-single' view with the post data
//    }

}
