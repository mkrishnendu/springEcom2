package com.example.BloggingApplication.repository;

import com.example.BloggingApplication.entities.Category;
import com.example.BloggingApplication.entities.Post;
import com.example.BloggingApplication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
  List<Post> findByUser(User user);
  List<Post>findByCategory(Category category);

}
