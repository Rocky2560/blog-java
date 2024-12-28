package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table (name = "posts")
//@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class posts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    public posts()
    {
        this.createdate = LocalDateTime.now();

    }
    @Column(nullable = false)
    private LocalDateTime createdate;
}