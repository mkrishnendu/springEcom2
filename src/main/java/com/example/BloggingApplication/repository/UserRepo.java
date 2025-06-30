package com.example.BloggingApplication.repository;

import com.example.BloggingApplication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {

}
