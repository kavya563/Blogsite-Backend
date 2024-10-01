package com.blogsite.blog_site_blog.service;

import java.util.List;

import com.blogsite.blog_site_blog.entity.Blogs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafKaProducerService 
{
	private static final Logger log = LoggerFactory.getLogger(KafKaConsumerService.class);


	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	public void addDelBlog(Blogs blog, String topic)
	{
		this.kafkaTemplate.send(topic, blog);
	}
	
	public void findBlogs(List<Blogs> blogs,String topic)
	{
		this.kafkaTemplate.send(topic, blogs);
	}

	public void searchBlogs(List<Blogs> blogs, String topic) {
		this.kafkaTemplate.send(topic, blogs);		
	}
	
}
