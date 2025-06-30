package com.example.BloggingApplication.service.impl;

import com.example.BloggingApplication.entities.Category;
import com.example.BloggingApplication.exception.ResourceNotFoundException;
import com.example.BloggingApplication.paylods.CategoryDto;
import com.example.BloggingApplication.repository.CatagoryRepo;
import com.example.BloggingApplication.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CatagoryRepo catagoryRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category=catagoryRepo.save(this.DtotoCategory(categoryDto));
        return this.CategorytoDto(category);

    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
      return catagoryRepo.findById(categoryId).map(e->{
            e.setCatagoryId(categoryDto.getCatagoryId());
            e.setCategoryTitle(categoryDto.getCategoryTitle());
            e.setCategoryDescription(categoryDto.getCategoryDescription());
            return this.CategorytoDto(catagoryRepo.save(e));
        }).orElseThrow(()->new ResourceNotFoundException("category","id",categoryId));
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = catagoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        catagoryRepo.delete(category);
    }


    @Override
    public CategoryDto getbyIdCategory(Integer categoryId) {
    Category category = catagoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","id",categoryId));
    return this.CategorytoDto(category);
    }

    @Override
    public List<CategoryDto> getCategory() {


   return catagoryRepo.findAll().stream().map(this::CategorytoDto)
        .collect(Collectors.toList());
    }

    private Category DtotoCategory(CategoryDto categoryDto){
        Category category=this.modelMapper.map(categoryDto,Category.class);
        return category;
    }

    private CategoryDto CategorytoDto(Category category){
        CategoryDto categoryDto=this.modelMapper.map(category,CategoryDto.class);
        return categoryDto;

    }
}
