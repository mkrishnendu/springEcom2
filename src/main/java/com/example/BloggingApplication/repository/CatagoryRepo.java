package com.example.BloggingApplication.repository;

import com.example.BloggingApplication.entities.Category;

import org.springframework.data.jpa.repository.JpaRepository;



public interface CatagoryRepo extends JpaRepository<Category,Integer> {

}
