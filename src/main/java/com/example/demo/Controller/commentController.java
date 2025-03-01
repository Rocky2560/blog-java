package com.example.demo.Controller;

import com.example.demo.model.comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Service.commentService;
import com.example.demo.model.posts;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class commentController {

    @Autowired
    private commentService commentService;

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
    public String addComment(@PathVariable Long postId, @ModelAttribute comment comment,
                             @RequestParam(required = false) Long parentId) {
        comment.setPost(new posts(postId)); // Set post ID
        commentService.saveComment(comment, parentId);
        return "redirect:/posts/" + postId;
    }


}
