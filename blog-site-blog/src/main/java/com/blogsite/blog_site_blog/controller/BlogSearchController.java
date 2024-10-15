package com.blogsite.blog_site_blog.controller;

import com.blogsite.blog_site_blog.entity.Blogs;
import com.blogsite.blog_site_blog.entity.Category;
import com.blogsite.blog_site_blog.query.FindAllByCategoryQuery;
import com.blogsite.blog_site_blog.security.JwtUtils;
import com.blogsite.blog_site_blog.service.BlogService;
import com.blogsite.blog_site_blog.service.KafKaProducerService;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = {"http://localhost:4200","http://blogsiteapp.s3-website-us-east-1.amazonaws.com"}, maxAge = 3600)
@RestController
@RequestMapping("api/v1.0/blogsite/blogs")
public class BlogSearchController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    KafKaProducerService kafkaProducerService;
    @Autowired
    QueryGateway queryGateway;

    @GetMapping("/info/{category}")
    public ResponseEntity<Object> searchBlogsByCategory(@PathVariable String category) {
        List<Blogs> blogs = blogService.getBlogsByCategory(category);
        FindAllByCategoryQuery findAllByCategoryQuery = new FindAllByCategoryQuery();
        findAllByCategoryQuery.setCategory(category);
        if (blogs.isEmpty()) {
            return new ResponseEntity<Object>("No Blogs Found", HttpStatus.OK);
        }
        return new ResponseEntity<Object>(blogs, HttpStatus.OK);
    }

    @GetMapping("info/{category}/{durationFromRang}/{durationToRang}")
    public ResponseEntity<?> getCategoriesRange(@PathVariable String category, @PathVariable String durationFromRang,
                                                @PathVariable String durationToRang) throws Exception{
        try {
            return new ResponseEntity<>(blogService.getAllBlogsCategoriesByRange(category, durationFromRang, durationToRang), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
