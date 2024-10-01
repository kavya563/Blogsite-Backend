package com.blogsite.blog_site_blog.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.blogsite.blog_site_blog.entity.Blogs;
import com.blogsite.blog_site_blog.service.BlogService;
import com.blogsite.blog_site_blog.service.KafKaConsumerService;
import org.axonframework.queryhandling.QueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BlogQueryHandler {
	@Autowired
	BlogService blogRepository;
	private static final Logger log = LoggerFactory.getLogger(BlogQueryHandler.class);

	List<Blogs> blogs = new ArrayList<>();
	
	@QueryHandler
	public List<Blogs> handle(GetAllBlogsQuery getAllBlogsQuery){
		
		//BeanUtils.copyProperties(,blogs);
			
			return blogRepository.findAll();
		
			//return blogRepository.findAllByUserid(getAllQuery.getUserid());
		
	}
	@QueryHandler
	public List<Blogs> handle1(GetMyBlogsQuery getAllQuery){
		
			return blogRepository.findAll();
		//BeanUtils.copyProperties(blogRepository.findAllByUserid(getAllQuery.getUserid()),blogs);
		//return blogs;
		
	}
	@QueryHandler
	public String handle2(DeleteBlogQuery deleteBlogQuery){
		log.info("DELETE");
		//	Optional<MongoBlogs> mongoBlog = blogRepository.findByBlognameAndUserid(deleteBlogQuery.getBlog().getBlogname(),deleteBlogQuery.getBlog().getUserid());
			//blogRepository.delete(mongoBlog.get());
		blogRepository.findByBlognameAndUserid(deleteBlogQuery.getBlog().getBlogname(),deleteBlogQuery.getBlog().getBlogname());
			return "success";
		
	}


}
