package com.example.BloggingApplication.paylods;

import com.example.BloggingApplication.entities.Category;
import com.example.BloggingApplication.entities.User;
import jakarta.persistence.ManyToOne;

import java.util.Date;


public class PostDto {

    private String Title;
    private String Content;
    private String ImageName;
    private Date addedDate;

    private CategoryDto category;

    private UserDto user;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getImageName() {
        return ImageName;
    }

    public void setImageName(String imageName) {
        ImageName = imageName;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public PostDto(String title, String content, String imageName, Date addedDate, CategoryDto category, UserDto user) {
        Title = title;
        Content = content;
        ImageName = imageName;
        this.addedDate = addedDate;
        this.category = category;
        this.user = user;
    }
    public PostDto(){

    }

}
