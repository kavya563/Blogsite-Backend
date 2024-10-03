package com.blogsite.blog_site_user.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
public class LoginRequest {
	@NotBlank(message = "Username is mandatory")
	@NotNull
	private String username;

	public LoginRequest() {
	}

	@NotNull
	@NotBlank(message = "Password is mandatory")
	@Size(min = 6, message = "Password must be at least 6 characters long")
	private String password;

	public String getUsername() {
		return username;
	}

	public LoginRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
