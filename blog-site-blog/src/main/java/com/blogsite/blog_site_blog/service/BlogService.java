package com.blogsite.blog_site_blog.service;


import com.blogsite.blog_site_blog.entity.Blogs;
import com.blogsite.blog_site_blog.entity.Category;
import com.blogsite.blog_site_blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public Blogs addBlog(Blogs blog) {
        return blogRepository.save(blog);
    }

    public List<Blogs> findAll(){
        return blogRepository.findAll();
    }

    public List<Blogs> getBlogsByUserId(String userId) {
        List<Blogs> blogs = blogRepository.findByUserId(userId);
        System.out.println("Fetched Blogs: " + blogs);
        return blogs;
    }

    public List<Blogs> findBlogsByBlogname(String blogname) {
        return blogRepository.findByBlogname(blogname);
    }

    public  Optional<Blogs> findByBlognameAndUserid(String blogname, String userId){
        return blogRepository.findByBlognameAndUserId(blogname,userId);}


        public void deleteAll(){
        blogRepository.deleteAll();
        }
        public boolean deleteBlogByNameAndUserId(String blogName, String userId) {
        Optional<Blogs> blogOptional = blogRepository.findByBlognameAndUserId(blogName, userId);

        if (blogOptional.isPresent()) {
            blogRepository.delete(blogOptional.get());
            return true;
        }
        return false;
    }
    public List<Blogs> getBlogsByCategory(String category){
        return blogRepository.findAllByCategory(category);
    }


    public List<Blogs> getAllBlogsCategoriesByRange(String category, String durationFromRang, String durationToRang) throws Exception {
        List<Blogs> filteredBlogs=new ArrayList<>();
        List<Blogs> dateRangeBlogs=blogRepository.getObjectByCreationTimeStamp(durationFromRang, durationToRang);
        System.out.print("dateRangeBlogs"+dateRangeBlogs);
        for (Blogs blogsModal : dateRangeBlogs) {
            if(Objects.equals(blogsModal.getCategory(), category)) {
                filteredBlogs.add(blogsModal);
            }
        }
        filteredBlogs.sort(Comparator.comparing(Blogs::getTimestamp).reversed());
        return filteredBlogs;

    }
}
