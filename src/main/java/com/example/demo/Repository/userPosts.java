package com.example.demo.Repository;

import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table( name = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor

    public class userPosts {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String heading;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Date date;
}
