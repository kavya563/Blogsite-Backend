package com.blogsite.blog_site_blog.controller;

import com.blogsite.blog_site_blog.commands.CreateBlogCommand;
import com.blogsite.blog_site_blog.entity.AppConstants;
import com.blogsite.blog_site_blog.entity.Blogs;

import com.blogsite.blog_site_blog.query.DeleteBlogQuery;
import com.blogsite.blog_site_blog.query.GetAllBlogsQuery;
import com.blogsite.blog_site_blog.security.JwtUtils;
import com.blogsite.blog_site_blog.service.BlogService;
import com.blogsite.blog_site_blog.service.KafKaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Optional;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("api/v1.0/blogsite/user")
public class BlogController {

	@Autowired
	private BlogService blogService;

	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	KafKaProducerService kafkaProducerService;
	@Autowired
	CommandGateway commandGateway;

	@Autowired
	QueryGateway queryGateway;


	@PostMapping("/blogs/add")
	public ResponseEntity<Object> addBlog(@Valid @RequestBody Blogs blog, @RequestHeader("Authorization") String token) {
		String userId = jwtUtils.getUserIdFromJwtToken(token.replace("Bearer ", ""));

		Optional<Blogs> blog1 = blogService.findByBlognameAndUserid(blog.getBlogname(), blog.getUserId());
		if (!blog1.isPresent()) {
			Blogs blogsModalNew = new Blogs();
			LocalDateTime myDateObj = LocalDateTime.now();
			DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String formattedDate = myDateObj.format(myFormatObj);
			blogsModalNew.setBlogname(blog.getBlogname());
			blogsModalNew.setAuthorname(blog.getAuthorname());
			blogsModalNew.setArticle(blog.getArticle());
			blogsModalNew.setCategory(blog.getCategory());
			blogsModalNew.setTimestamp(formattedDate);
			blogsModalNew.setUserId(userId);

			CreateBlogCommand createBlogCommand = new CreateBlogCommand(
					UUID.randomUUID().toString(),
					blogsModalNew.getBlogname(),
					blogsModalNew.getCategory(),
					blogsModalNew.getAuthorname(),
					blogsModalNew.getArticle(),
					blogsModalNew.getTimestamp(),
					blogsModalNew.getUserId()
			);
			commandGateway.sendAndWait(createBlogCommand);

			return ResponseEntity.ok(new ResponseMessage("Blog added successfully"));
		}
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("message", "Blog name already exists");
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@GetMapping("/getMyBlogs")
	public ResponseEntity<List<Blogs>> viewBlogs(@RequestHeader("Authorization") String token) {
		String userId = jwtUtils.getUserIdFromJwtToken(token.replace("Bearer ", ""));

		System.out.println("User ID: " + userId); // Log the user ID
		List<Blogs> blogs = blogService.getBlogsByUserId(userId);
		System.out.println("Fetched: " + blogs);
		return ResponseEntity.ok(blogs);
	}

	@GetMapping("/getall")
	public ResponseEntity<Object> getBlogs() {
		List<Blogs> blogs = blogService.findAll();
		GetAllBlogsQuery getAllBlogsQuery = new GetAllBlogsQuery();
		return new ResponseEntity<Object>(blogs, HttpStatus.OK);
	}


	@DeleteMapping("/deleteAll")
	public ResponseEntity<String> deleteAll() {
		blogService.deleteAll();
		return ResponseEntity.ok("All entries deleted successfully");
	}


	@DeleteMapping("/blogs/delete/{blogname}")
	public ResponseEntity<Object> deleteBlog(@PathVariable String blogname, @RequestHeader("Authorization") String token) {
		String userId = jwtUtils.getUserIdFromJwtToken(token.replace("Bearer ", ""));
		if (jwtUtils.validateJwtToken(token.replace("Bearer ", ""))) {
			// Check if the blog exists for the user
			boolean isDeleted = blogService.deleteBlogByNameAndUserId(blogname, userId);

			if (isDeleted) {
				return ResponseEntity.ok(Collections.singletonMap("message", "User registered successfully"));
			} else {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("message", "Blog already deleted / not exists"));
			}
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("message", "UnAuthorised"));

	}


}
