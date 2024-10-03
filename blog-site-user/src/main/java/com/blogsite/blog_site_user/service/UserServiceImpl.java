package com.blogsite.blog_site_user.service;

import com.blogsite.blog_site_user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
}
