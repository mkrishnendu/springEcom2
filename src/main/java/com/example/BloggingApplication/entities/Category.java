package com.example.BloggingApplication.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="categories")
@Getter
@Setter

//@NoArgsConstructor
public class Category {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int catagoryId;

@Column(name="title")
    private String categoryTitle;
@Column(name="desccription")
    private String categoryDescription;
@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
private List<Post> posts=new ArrayList<>();

    public int getCatagoryId() {
        return catagoryId;
    }

    public void setCatagoryId(int catagoryId) {
        this.catagoryId = catagoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public Category(int catagoryId, String categoryTitle, String categoryDescription) {
        this.catagoryId = catagoryId;
        this.categoryTitle = categoryTitle;
        this.categoryDescription = categoryDescription;
    }
    public Category() {
        // No-args constructor required by ModelMapper
    }

}
