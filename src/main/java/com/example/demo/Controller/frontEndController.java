package com.example.demo.Controller;

import com.example.demo.Service.postService;
import com.example.demo.model.posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.Service.postService;
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
}
