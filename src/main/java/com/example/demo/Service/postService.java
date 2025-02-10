    package com.example.demo.Service;

    import com.example.demo.Repository.MyCustomRepository;
    import com.example.demo.Repository.userpostsRepository;
    import com.example.demo.model.posts;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.PageRequest;
    import org.springframework.data.domain.Pageable;
    import org.springframework.stereotype.Service;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.multipart.MultipartFile;

    import java.io.File;
    import java.io.IOException;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.nio.file.Paths;
    import java.util.ArrayList;
    import java.util.List;


    @Service
    public class postService {

        private static final String UPLOAD_DIR = "uploads/";
        @Autowired
        userpostsRepository userpostsRepository;

        public List<posts> getAlllistPosts ()
        {
            List<posts> post = new ArrayList<>();
            userpostsRepository.findAll().forEach(posts1 -> post.add(posts1));
            return post;
        }

        public List<posts> getRecentPosts() {
            return userpostsRepository.findTop5ByOrderByCreatedateDesc();
        }

        public posts getPostById(long id)
        {
            return userpostsRepository.findById((int) id).get();
        }


        public Page<posts> getPostsByCategory(String category, int page, int size) {
            Pageable pageable = PageRequest.of(page, size);
            return (Page<posts>) userpostsRepository.findByCategory(category, pageable);
        }

        // Search posts by title with pagination
        public Page<posts> searchPosts(String keyword, int page, int size) {
            PageRequest pageRequest = PageRequest.of(page, size);
            return userpostsRepository.findByTitleContainingIgnoreCase(keyword, pageRequest);
        }



        public void saveorUpdate(posts posts, MultipartFile imagefile) throws IOException
        {
            if (imagefile != null && !imagefile.isEmpty())
            {
                File uploadDir = new File(UPLOAD_DIR);
                if (!uploadDir.exists())
                    uploadDir.mkdirs();

                String fileName = System.currentTimeMillis() + "_" + imagefile.getOriginalFilename();
                Path filePath = Paths.get(UPLOAD_DIR + fileName);
                Files.write(filePath, imagefile.getBytes());

                //set the image path to the objects
                posts.setImage("/uploads/" + fileName);


            }

            userpostsRepository.save(posts);
        }

        public void deletePosts(int id)
        {
            userpostsRepository.deleteById(id);
        }

    }
