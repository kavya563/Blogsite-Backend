package com.blogsite.blog_site_blog.commands;

import com.blogsite.blog_site_blog.entity.Blogs;
import com.blogsite.blog_site_blog.repository.BlogRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class BlogEventsHandler {
	
	@Autowired
	BlogRepository blogRepository;
	@EventHandler
	public void on(BlogCreatedEvent blogCreatedEvent) {
		Blogs blogs = new Blogs();
		BeanUtils.copyProperties(blogCreatedEvent, blogs);
		blogRepository.save(blogs);
		
	}
}
