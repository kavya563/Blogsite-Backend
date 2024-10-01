package com.blogsite.blog_site_blog.service;

import java.util.List;
import java.util.Optional;

import com.blogsite.blog_site_blog.entity.AppConstants;
import com.blogsite.blog_site_blog.entity.Blogs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;



import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KafKaConsumerService 
{

	@Autowired BlogService mongoRepo;

	private static final Logger log = LoggerFactory.getLogger(KafKaConsumerService.class);

	@KafkaListener(topics = AppConstants.TOPIC_ADD_BLOG, groupId = AppConstants.GROUP_ID)
	public void consume1(Blogs blogs) {
		log.info(String.format(AppConstants.TOPIC_ADD_BLOG+"%s", blogs));
		mongoRepo.addBlog(blogs);
		
	}
	
	@KafkaListener(topics = AppConstants.TOPIC_DELETE_BLOG, groupId = AppConstants.GROUP_ID)
	public void consume2(Blogs blogs) {
		log.info(String.format(AppConstants.TOPIC_DELETE_BLOG+"%s", blogs));
		List<Blogs> mongoBlog = mongoRepo.findBlogsByBlogname(blogs.getBlogname());
	}
	
	@KafkaListener(topics = AppConstants.TOPIC_FINDALL_BLOGS, groupId = AppConstants.GROUP_ID)
	public void consume3(List<Blogs> blogs) {
		log.info(String.format(AppConstants.TOPIC_FINDALL_BLOGS+"%s", blogs));
	}
	
	@KafkaListener(topics = AppConstants.TOPIC_FINDMY_BLOGS, groupId = AppConstants.GROUP_ID)
	public void consume4(List<Blogs> blogs) {
		log.info(String.format(AppConstants.TOPIC_FINDMY_BLOGS+"%s", blogs));
	}
	
	@KafkaListener(topics = AppConstants.TOPIC_SEARCH_BLOG_CAT, groupId = AppConstants.GROUP_ID)
	public void consume5(List<Blogs> blogs) {
		log.info(String.format(AppConstants.TOPIC_SEARCH_BLOG_CAT+"%s", blogs));
	}
	
	@KafkaListener(topics = AppConstants.TOPIC_SEARCH_BLOG_CAT_RANGE, groupId = AppConstants.GROUP_ID)
	public void consume6(List<Blogs> blogs) {
		log.info(String.format(AppConstants.TOPIC_SEARCH_BLOG_CAT_RANGE+"%s", blogs));
	}
}
