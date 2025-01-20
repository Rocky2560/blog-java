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

    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";
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

//    @GetMapping("/uploads")
//    public ResponseEntity<?> loaduploadsFile(@RequestParam("file") MultipartFile file) throws IOException
//    {
//
//        if (file != null && !file.isEmpty()) {
//            File uploadDir = new File(UPLOAD_DIR);
//            if (!uploadDir.exists())
//                uploadDir.mkdirs();
//
//            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
//            Path filePath = Paths.get(UPLOAD_DIR + fileName);
//            Files.write(filePath, file.getBytes());
//            Map<String, String> result = new HashMap<>();
//            result.put("location", "/uploads/" + file.getOriginalFilename());
//            //set the image path to the objects
////            posts.setImage("/uploads/" + fileName);
//
//            String fPath = UPLOAD_DIR + file.getOriginalFilename();
////        file.transferTo(new File(filePath));
//            System.out.println("fissssle");
////        Map<String, String> result = new HashMap<>();
////        result.put("location","uploads/"+ file.getOriginalFilename());
//            return ResponseEntity.ok(fPath);
//        }
//        return ResponseEntity.status(400).body(Map.of("error", "No file uploaded"));
//    }

    //Uplaod the file which is added in the text field as well
//    @PostMapping("/uploads")
//    public ResponseEntity<Map<String, String>> uploadsFile(@RequestParam("upload") MultipartFile file) {
//        try {
//            // Define the directory to save the file
//            File directory = new File("src/main/resources/static/uploads/");
//            if (!directory.exists()) {
//                directory.mkdirs();
//            }
//
//            // Save the file
//            String filePath = directory.getAbsolutePath() + "/" + file.getOriginalFilename();
//            file.transferTo(new File(filePath));
//
//            // Return the public URL of the uploaded file
//            Map<String, String> response = new HashMap<>();
//            response.put("url", "/uploads/" + file.getOriginalFilename());
//            return ResponseEntity.ok(response);
//
//        } catch (IOException e) {
//            e.printStackTrace(); // Log the error for debugging
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(Collections.singletonMap("error", "File upload failed!"));
//        }
//    }
//
//    // Class for the response JSON
//    public static class ImageResponse {
//        private String url;
//
//        public ImageResponse(String url) {
//            this.url = url;
//        }
//
//        public String getUrl() {
//            return url;
//        }
//
//        public void setUrl(String url) {
//            this.url = url;
//        }
//    }

    //Showing the post details
    @GetMapping("/postDetails")
    public String  postDetails(@RequestParam(value = "id", required = false) Long id, Model model)
    {
            List<posts> posts = postService.getAlllistPosts();
            model.addAttribute("posts", posts);

        return "edit";
    }

//    @GetMapping("/posts")
//    public List<posts> getAllPosts()
//    {
//        return postService.getAlllistPosts();
//    }


    //Getting the id for the edit process
    @GetMapping("/posts/{postid}")
            public void deletePost(@PathVariable ("postid") int postid)
    {
        postService.deletePosts(postid);
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
//            System.out.println("hello");
//            posts.setImage(image);

            postService.saveorUpdate(posts, image);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        postService.saveorUpdate(posts);
        return "index";
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
            posts.setTitle(title);
            posts.setDescription(description);
            posts.setCategory(category);
            postService.saveorUpdate(posts, image);
            return "redirect:/api/postDetails";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
