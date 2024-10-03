package com.blogsite.blog_site_user.service;

import java.util.List;

import com.blogsite.blog_site_user.entity.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;




@Slf4j
@Service
public class KafKaConsumerService 
{
	private static final Logger log = LoggerFactory.getLogger(KafKaConsumerService.class);

	@KafkaListener(topics = AppConstants.TOPIC_USER_REGISTER, groupId = AppConstants.GROUP_ID)
	public void consume1(String message) {

		log.info(String.format(AppConstants.TOPIC_USER_REGISTER+"User name is %s", message));
	}
	
	@KafkaListener(topics = AppConstants.TOPIC_USER_LOGIN, groupId = AppConstants.GROUP_ID)
	public void consume2(String message) {
		log.info(String.format(AppConstants.TOPIC_USER_LOGIN+"User name is %s", message));
	}
	
	@KafkaListener(topics = AppConstants.TOPIC_USER_REGISTER_FAIL, groupId = AppConstants.GROUP_ID)
	public void consume3(String message) {
		log.info(String.format(AppConstants.TOPIC_USER_REGISTER_FAIL+"%s", message));
	}
}
