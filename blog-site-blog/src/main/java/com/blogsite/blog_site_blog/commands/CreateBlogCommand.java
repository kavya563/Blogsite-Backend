package com.blogsite.blog_site_blog.commands;

import java.sql.Timestamp;
import java.util.Date;

import com.blogsite.blog_site_blog.entity.Category;
import org.axonframework.modelling.command.TargetAggregateIdentifier;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateBlogCommand {
	
	@TargetAggregateIdentifier
	private String blogId;
	private String blogname;
	private String category;
	private String authorname;
	private String article;
	private String timestamp;
	private String userId;

	public String getBlogId() {
		return blogId;
	}

	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}

	public String getBlogname() {
		return blogname;
	}

	public void setBlogname(String blogname) {
		this.blogname = blogname;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAuthorname() {
		return authorname;
	}

	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public CreateBlogCommand(String blogId, String blogname, String category, String authorname,
							 String article, String timestamp, String userId) {
		this.blogId = blogId;
		this.blogname = blogname;
		this.userId = userId;
		this.category = category;
		this.authorname = authorname;
		this.article = article;
		this.timestamp = timestamp;
	}
}
