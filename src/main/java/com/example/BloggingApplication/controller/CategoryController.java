package com.example.BloggingApplication.controller;


import com.example.BloggingApplication.paylods.ApiResponse;
import com.example.BloggingApplication.paylods.CategoryDto;
import com.example.BloggingApplication.paylods.UserDto;
import com.example.BloggingApplication.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @PostMapping("/add-category")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto categoryDto1=categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);
    }
    @PutMapping("/update-category/{id}")
    public ResponseEntity<CategoryDto>updateCategory( @Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer id){
        CategoryDto categoryDto1=categoryService.updateCategory(categoryDto,id);
        return new ResponseEntity<>(categoryDto1,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(new ApiResponse("Category deleted successfully", true), HttpStatus.OK);
    }

    @GetMapping("/get-all-category")
    public ResponseEntity<List<CategoryDto>>getAllUser(){

        return new ResponseEntity<>(categoryService.getCategory(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto>getsingleuser(@PathVariable int id){
        return new ResponseEntity<>(categoryService.getbyIdCategory(id),HttpStatus.OK);
    }



}
