package com.example.demo.Controller;

import com.example.demo.Service.commentService;
import com.example.demo.Service.postService;
import com.example.demo.Service.subscriptionService;
import com.example.demo.model.comment;
import com.example.demo.model.posts;
import com.example.demo.model.subscription;
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
import com.example.demo.Service.commentService;
import java.util.ArrayList;
import java.util.List;

@Controller
//@RequestMapping("/blog")
public class frontEndController {

    @Autowired
    postService postService;

    @Autowired
    subscriptionService subscriptionService;

    @Autowired
    private commentService commentService;

    @GetMapping(value = "/")
    public String entryindex(Model model,  @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "5") int size)
    {
        Page<posts> posts = postService.getAlllistPosts(page,size);
        List<posts> recentPost = postService.getRecentPosts();
//        model.addAttribute("currentPage", 0);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", posts.getTotalPages());
        model.addAttribute("posts", posts);
        model.addAttribute("recentpost",recentPost);

        return "index";
    }
    @GetMapping(value = "/test")
    public String index(Model model,  @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "5") int size)
    {
        Page<posts> posts = postService.getAlllistPosts(page,size);
        List<posts> recentPost = postService.getRecentPosts();
//        model.addAttribute("currentPage", 0);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", posts.getTotalPages());
        model.addAttribute("posts", posts);
        model.addAttribute("recentpost",recentPost);

        return "index";
    }

    @GetMapping(value = "/subscribe")
    public String subscribe(@RequestParam(value = "email")String email, Model model)
    {
        subscriptionService.subscribe(email);
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
        List<posts> recentPost = postService.getRecentPosts();
        model.addAttribute("recentpost",recentPost);
        List<comment> comments = commentService.getCommetsByPostId((long)idd);
        model.addAttribute("comments", comments);
//        model.addAttribute("postId", postId);
        model.addAttribute("comment", new comment());
        return "garden-single";  // This returns the 'garden-single' view with the post data
    }

    @GetMapping(value = "/{category}")
    public String getPostsByCategory(@PathVariable String category, Model model, RedirectAttributes redirectAttributes,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "5") int size) {

//        size = 5;
////        System.out.println(size);
        Page<posts> postsPage = postService.getPostsByCategory(category, page, size);
        List<posts> recentPost = postService.getRecentPosts();
        model.addAttribute("recentpost",recentPost);
        model.addAttribute("category", category);
        model.addAttribute("posts", postsPage.getContent());  // Posts for current page
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", postsPage.getTotalPages());
        return "index"; // Return to Thymeleaf template
    }

    @GetMapping("/search")
    public String searchPosts(@RequestParam String keyword,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "5") int size,
                              Model model) {

        // Fetch search results
        Page<posts> searchResults = postService.searchPosts(keyword, page, size);

        List<posts> recentPost = postService.getRecentPosts();
        model.addAttribute("recentpost",recentPost);
        // Add results to the model
        model.addAttribute("posts", searchResults.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", searchResults.getTotalPages());
        model.addAttribute("keyword", keyword);


        return "forward:/redirect"; // Return the Thymeleaf view
    }

}
