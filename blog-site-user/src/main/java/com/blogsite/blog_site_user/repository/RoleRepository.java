package com.blogsite.blog_site_user.repository;

import com.blogsite.blog_site_user.entity.ERole;
import com.blogsite.blog_site_user.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends MongoRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
