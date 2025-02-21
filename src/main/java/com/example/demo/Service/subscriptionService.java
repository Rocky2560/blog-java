package com.example.demo.Service;

import com.example.demo.Repository.subscriptionRespository;
import com.example.demo.Repository.userpostsRepository;
import com.example.demo.model.posts;
import com.example.demo.model.subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class subscriptionService {

    @Autowired
    private subscriptionRespository subscriptionRepository;

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private userpostsRepository postRepository;

    public void subscribe(String email) {
        // Save the email to the database
        subscription subscription = new subscription(email);
        subscriptionRepository.save(subscription);
        // Send confirmation email
        sendConfirmationEmail(email);

    }

    private void sendConfirmationEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sudipmanandhar2560@gmail.com");
        message.setTo(email);
        message.setSubject("Subscription Confirmation");
        message.setText("Thank you for subscribing to our blog!");

        mailSender.send(message);
    }

    // Method to notify all subscribers about a new post
    public void notifySubscribersAboutNewPost(posts post) {
        List<subscription> subscribers = subscriptionRepository.findAll();
        for (subscription subscriber : subscribers) {
            sendNewPostNotification(subscriber.getEmail(), post);
        }
    }

    // Send new post notification to a subscriber
    private void sendNewPostNotification(String email, posts post) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sudipmanandhar2560@example.com");
        message.setTo(email);
        message.setSubject("New Blog Post: " + post.getTitle());
        message.setText("A new post has been published!\n\nTitle: " + post.getTitle() + "\n\n" + post.getDescription());

        mailSender.send(message);
    }
}
