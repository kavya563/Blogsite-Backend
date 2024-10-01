package com.blogsite.blog_site_blog.query;

import com.blogsite.blog_site_blog.entity.Blogs;


import lombok.Data;

@Data
public class DeleteBlogQuery {
	private Blogs blog;

	public Blogs getBlog() {
		return blog;
	}

	public void setBlog(Blogs blog) {
		this.blog = blog;
	}
}
