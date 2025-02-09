package com.example.demo.Controller;

import com.example.demo.Service.postService;
import com.example.demo.model.posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.Service.postService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
//@RequestMapping("/blog")
public class frontEndController {

    @Autowired
    postService postService;

    @GetMapping(value = "/test")
    public String index(Model model,  @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "5") int size)
    {
        List<posts> posts = postService.getAlllistPosts();
        List<posts> recentPost = postService.getRecentPosts();
        model.addAttribute("posts", posts);
//        model.addAttribute("recentpost",recentPost);

        return "index";
    }

    @GetMapping(value = "/redirect")
    public String redirect()
    {
        return "index";
    }

    @GetMapping(value = "/details" )
    public String postdetails(@RequestParam(value = "postid") String id, Model model,  @RequestParam(defaultValue = "0")
                                  int page, @RequestParam(defaultValue = "5") int size) {
        int idd = Integer.parseInt(id);
        posts posts =  postService.getPostById(idd);
        model.addAttribute("posts", posts);
        return "garden-single";  // This returns the 'garden-single' view with the post data
    }

    @GetMapping(value = "/{category}")
    public String getPostsByCategory(@PathVariable String category, Model model, RedirectAttributes redirectAttributes,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "5") int size) {

        Page<posts> postsPage = postService.getPostsByCategory(category, page, size);
        model.addAttribute("category", category);
        model.addAttribute("posts", postsPage.getContent());  // Posts for current page
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", postsPage.getTotalPages());
        return "forward:/redirect"; // Return to Thymeleaf template
    }

}
