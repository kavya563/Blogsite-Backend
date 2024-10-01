package com.blogsite.blog_site_blog.query;

import java.sql.Date;


import lombok.Data;

@Data
public class FindAllByCategoryAndRangeQuery {
	private String category;
	private Date fromdate;
	private Date todate;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getFromdate() {
		return fromdate;
	}

	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}

	public Date getTodate() {
		return todate;
	}

	public void setTodate(Date todate) {
		this.todate = todate;
	}
}
