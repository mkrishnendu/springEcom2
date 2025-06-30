package com.example.BloggingApplication.service;

import com.example.BloggingApplication.paylods.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CategoryService {
    //create

    public CategoryDto createCategory(CategoryDto categoryDto);
    //update

    public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
    //delete
    public void deleteCategory(Integer categoryId);

    //get

    public CategoryDto getbyIdCategory(Integer categoryId);

    //getall
    public List<CategoryDto> getCategory();




}
