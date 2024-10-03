package com.blogsite.blog_site_user.commands;

import com.blogsite.blog_site_user.entity.User;
import com.blogsite.blog_site_user.repository.UserRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserEventsHandler {
	
	@Autowired
	UserRepository userRepository;
	@EventHandler
	public void on(UserCreatedEvent blogCreatedEvent) {
		User user = new User();
		BeanUtils.copyProperties(blogCreatedEvent, user);
		userRepository.save(user);
		
	}
}
