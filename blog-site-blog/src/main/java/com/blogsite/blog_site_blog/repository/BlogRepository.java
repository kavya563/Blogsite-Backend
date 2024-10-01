package com.blogsite.blog_site_blog.repository;

import com.blogsite.blog_site_blog.entity.Blogs;
import com.blogsite.blog_site_blog.entity.Category;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogRepository extends MongoRepository<Blogs,String> {

    List<Blogs> findByUserId(String userId);
	List<Blogs> findAllByCategory(String category);
	List<Blogs> findByCategoryAndTimestampBetween(Category category, java.sql.Date fromDate, java.sql.Date toDate);
	List<Blogs> findByBlogname(String blogname);
	Optional<Blogs> findByBlognameAndUserId(String blogName, String userId);

	@Query("DELETE FROM Blogs b WHERE b.userId = ?1 AND b.blogname = ?2")
	void deleteByUserIdAndBlogname(String userId, String blogname);

	@Query("{'timestamp' : { $gte: ?0, $lte: ?1 } }")
	List<Blogs> getObjectByCreationTimeStamp(String durationFromRang, String durationToRang);
}
