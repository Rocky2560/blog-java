package com.example.demo.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api")
public class FileUploadController {

    private static final String UPLOAD_DIR = "uploads/";
    @PostMapping("/upload-image")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // Ensure the upload directory exists
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Generate a unique file name (e.g., using timestamp or UUID)
            String originalFileName = file.getOriginalFilename();
//            String uniqueFileName = System.currentTimeMillis() + "_" + originalFileName;
            Path filePath = uploadPath.resolve(originalFileName);

            // Save the file to the uploads directory
            Files.copy(file.getInputStream(), filePath);

            // Build the image URL
            String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/uploads/" + originalFileName)
                    .toUriString();

            // Return the image URL directly as a string (expected format for TinyMCE)
            return ResponseEntity.ok("http://localhost:8082/uploads/monitor.jpg");

        } catch (IOException e) {
            // Log the error
            e.printStackTrace();

            // Return a JSON error message in case of failure
            ErrorResponse errorResponse = new ErrorResponse("Image upload failed: " + e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    // Success response model
    public static class UploadResponse {
        private String location;

        public UploadResponse(String location) {
            this.location = location;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }

    // Error response model
    public static class ErrorResponse {
        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

}