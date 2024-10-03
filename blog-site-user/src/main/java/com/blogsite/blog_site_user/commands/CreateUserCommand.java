package com.blogsite.blog_site_user.commands;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.axonframework.modelling.command.TargetAggregateIdentifier;



import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserCommand {
	/*
	@TargetAggregateIdentifier
	private String id; */
	private String username;
	private String email;
	private String password;

	public CreateUserCommand(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}
//private Set<Role> roles;
}
