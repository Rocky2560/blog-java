package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}


//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		if (!registry.hasMappingForPattern("/static/images/**")) {
//			registry.addResourceHandler("/static/images/**").addResourceLocations("classpath:/static/images/");
//		}
//	}

}
