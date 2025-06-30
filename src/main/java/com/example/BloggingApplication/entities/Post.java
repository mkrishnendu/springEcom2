package com.example.BloggingApplication.entities;


import com.example.BloggingApplication.paylods.CategoryDto;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;
    private String Title;
    private String Content;
    private String ImageName;
    private Date addedDate;
@ManyToOne
@JoinColumn(name="category_id")
    private Category category;
@ManyToOne

    private User user;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post(int postId, String title, String content, String imageName, Date addedDate, Category category, User user) {
        this.postId = postId;
        Title = title;
        Content = content;
        ImageName = imageName;
        this.addedDate = addedDate;
        this.category = category;
        this.user = user;
    }
    public Post(){

    }

}
