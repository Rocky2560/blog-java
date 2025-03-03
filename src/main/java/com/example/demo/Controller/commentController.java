package com.example.demo.Controller;

import com.example.demo.Service.postService;
import com.example.demo.model.comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Service.commentService;
import com.example.demo.model.posts;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/posts")
public class commentController {

    @Autowired
    private commentService commentService;

    @Autowired
    private postService postService;

    @GetMapping("/{postId}")
    public String showPostWithComments(@PathVariable Long postId, Model model)
    {
        List<comment> comments = commentService.getCommetsByPostId(postId);
        model.addAttribute("comments", comments);
        model.addAttribute("postId", postId);
        model.addAttribute("comment", new comment());
        return "post";

    }

    // Handle new comment submission
    @PostMapping("/{postId}/addComment")
    public String addComment(@PathVariable int postId, @ModelAttribute comment comment,
                             @RequestParam(required = false) Long parentId, @RequestParam(value = "name") String name,
                             @RequestParam(value = "text") String message) {
        posts posts = postService.findById(postId).orElseThrow(()-> new RuntimeException("posts not found"));
//        comment.setPost(new posts(postId)); // Set post ID
//        System.out.println(posts.getId());
//        comment comment = new comment();
        comment.setName(name);
        comment.setComment(message);
       comment.setPost(posts);
        System.out.println(comment.getId());
        commentService.saveComment(comment, parentId);
        return "redirect:/details?postid=" + postId;

    }


}
