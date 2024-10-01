package com.blogsite.blog_site_blog.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.validation.constraints.Size;
import java.util.Date;


@Document(	collection = "blogs")
@Getter
@Setter
public class Blogs {//not a spring bean
	@Id
	private String id;

	@Indexed(unique = true)
	@Size(min = 3,max=1000, message = "Blog name must be 10 atleast characters ")
	private String blogname;
	private String category;


	@Size(min=3,max = 1000, message = "Author name contains atleaat  5 characters ")
	private String authorname;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Size(min=5,max = 1000, message = "Article name must be 5 characters")
	private String article;
	//@NotBlank(message = "TimeStamp cannot be blank#######")
	private String timestamp;
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBlogname() {
		return blogname;
	}
	public void setBlogname(String blogname) {
		this.blogname = blogname;
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

	public Blogs() {
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

	public Blogs(String id, @Size(min = 10, message = "Blog name must be 10 atleast characters ") String blogname, String category, @Size(min = 5, max = 1000, message = "Author name contains atleaat  5 characters ") String authorname, @Size(min = 5, max = 1000, message = "Article name must be 5 characters") String article, String timestamp, String userId) {
		this.id = id;
		this.blogname = blogname;
		this.category = category;
		this.authorname = authorname;
		this.article = article;
		this.timestamp = timestamp;
		this.userId = userId;
	}
}