package com.example.demo.Controller;

import com.example.demo.Service.postService;
import com.example.demo.model.posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
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
    public String adminLogin(Model model)
    {
        List<posts> posts = postService.getAlllistPosts();
        model.addAttribute("posts", posts);
        return "index";
    }

    @GetMapping("/postDetails")
    public String  postDetails(@RequestParam(value = "id", required = false) Long id, Model model)
    {
//        if (id == null) {
            List<posts> posts = postService.getAlllistPosts();
            model.addAttribute("posts", posts);
//        }
//        else {
//            posts editPost = postService.getPostById(id);
//            if (editPost == null) {
//                return "redirect:/error";
//            }
//            model.addAttribute("editPost", editPost);
//        }
        return "edit";
    }

    @GetMapping("/posts")
    public List<posts> getAllPosts()
    {
        return postService.getAlllistPosts();
    }

//    @GetMapping("/posts/{postid}")
//            public posts getPosts(@PathVariable("postid") int postid)
//    {
//        return postService.getPostById(postid);
//    }

    @GetMapping("/posts/{postid}")
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


    @PutMapping("/posts")
    public posts update (@RequestBody posts posts)
    {
        postService.saveorUpdate(posts);
        return posts;
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
    public String updateExpense(@ModelAttribute posts posts)
    {
        postService.saveorUpdate(posts);
        return "redirect:/api/postDetails";
    }
}
