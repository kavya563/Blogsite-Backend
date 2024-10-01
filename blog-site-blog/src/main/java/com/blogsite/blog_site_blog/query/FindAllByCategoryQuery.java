package com.blogsite.blog_site_blog.query;


import com.blogsite.blog_site_blog.entity.Category;
import lombok.Data;

import java.util.Calendar;

@Data
public class FindAllByCategoryQuery {
	private String category;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public FindAllByCategoryQuery() {
	}

	public FindAllByCategoryQuery(String category) {
		this.category = category;
	}
}
