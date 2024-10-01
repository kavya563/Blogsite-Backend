/*
package com.blogsite.blog_site_blog.controller;



import com.blogsite.blog_site_blog.entity.Blogs;
import com.blogsite.blog_site_blog.entity.Category;
import com.blogsite.blog_site_blog.security.JwtUtils;
import com.blogsite.blog_site_blog.service.BlogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class BlogControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private BlogController blogController;

    @Mock
    private BlogService blogService;

    @Mock
    private JwtUtils jwtUtils;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(blogController).build();
    }

    @Test
    void testAddBlog_Success() throws Exception {
        String token = "Bearer some.jwt.token";
        String userId = "123";
        Blogs blog = new Blogs();
        blog.setBlogname("My Blog");

        when(jwtUtils.getUserIdFromJwtToken(anyString())).thenReturn(userId);
        when(blogService.findBlogsByBlogname(blog.getBlogname())).thenReturn(new ArrayList<>());

        Blogs mockBlog = new Blogs("My Blog", Category.FOOD, "AuthorName", "Article Content", new Date(), "UserId123");
        when(blogService.addBlog(any(Blogs.class))).thenReturn(mockBlog);

        ResponseEntity<Object> response = blogController.addBlog(blog, token);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Blog added Success", response.getBody());
    }

    @Test
    void testAddBlog_BlogExists() throws Exception {
        String token = "Bearer some.jwt.token";
        Blogs blog = new Blogs();
        blog.setBlogname("Existing Blog");
        List<Blogs> existingBlogs = new ArrayList<>();
        existingBlogs.add(blog);

        when(jwtUtils.getUserIdFromJwtToken(anyString())).thenReturn("123");
        when(blogService.findBlogsByBlogname(blog.getBlogname())).thenReturn(existingBlogs);

        ResponseEntity<Object> response = blogController.addBlog(blog, token);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Blog Name already Exist", response.getBody());
    }

    @Test
    void testViewBlogs() throws Exception {
        String token = "Bearer some.jwt.token";
        String userId = "123";
        List<Blogs> blogs = new ArrayList<>();
       blogs.add(new Blogs("MyBlog",Category.FOOD,"user1","myarticle",new Date(),userId ));

        when(jwtUtils.getUserIdFromJwtToken(anyString())).thenReturn(userId);
        when(blogService.getBlogsByUserId(userId)).thenReturn(blogs);

        ResponseEntity<List<Blogs>> response = blogController.viewBlogs(token);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(blogs, response.getBody());
    }

    @Test
    void testGetAllBlogs() throws Exception {
        String userId = "123";
        List<Blogs> blogs = new ArrayList<>();
        blogs.add(new Blogs("MyBlog",Category.FOOD,"user1","myarticle",new Date(),userId ));

        when(blogService.findAll()).thenReturn(blogs);

        ResponseEntity<Object> response = blogController.getBlogs();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(blogs, response.getBody());
    }

    @Test
    void testDeleteBlog_Success() throws Exception {
        String token = "Bearer some.jwt.token";
        String blogName = "My Blog";
        String userId = "123";

        when(jwtUtils.getUserIdFromJwtToken(anyString())).thenReturn(userId);
        when(blogService.deleteBlogByBlognameAndUserId(blogName, userId)).thenReturn(true);

        ResponseEntity<Object> response = blogController.deleteBlog(blogName, token);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Blog Deleted successfully!", response.getBody());
    }

    @Test
    void testDeleteBlog_NotFound() throws Exception {
        String token = "Bearer some.jwt.token";
        String blogName = "Nonexistent Blog";
        String userId = "123";

        when(jwtUtils.getUserIdFromJwtToken(anyString())).thenReturn(userId);
        when(blogService.deleteBlogByBlognameAndUserId(blogName, userId)).thenReturn(false);

        ResponseEntity<Object> response = blogController.deleteBlog(blogName, token);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("\"Blog already deleted / not exists", response.getBody());
    }
}
*/
