package com.blogsite.blog_site_user.commands;

import java.util.Set;
import java.util.UUID;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;



@Aggregate
public class UserAggregate {

	@AggregateIdentifier
	private String id;
	private String username;
	private String email;
	private String password;
	//private Set<Role> roles;
	
	@CommandHandler
	public UserAggregate(CreateUserCommand createBlogCommand) {
		this.id = UUID.randomUUID().toString();
		UserCreatedEvent userCreatedEvent = new UserCreatedEvent();
		userCreatedEvent.setId(this.id);
		BeanUtils.copyProperties(createBlogCommand, userCreatedEvent);
		AggregateLifecycle.apply(userCreatedEvent);
	}
	

	
	@EventSourcingHandler
	public void on(UserCreatedEvent blogCreatedEvent) {
		this.id= blogCreatedEvent.getId();
		this.username=blogCreatedEvent.getUsername();
		this.email=blogCreatedEvent.getEmail();
		this.password=blogCreatedEvent.getPassword();
		//this.roles=blogCreatedEvent.getRoles();
	}
}
