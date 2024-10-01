package com.blogsite.blog_site_blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BlogSiteBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogSiteBlogApplication.class, args);
	}

}
