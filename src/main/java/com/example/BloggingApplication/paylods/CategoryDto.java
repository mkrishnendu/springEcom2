package com.example.BloggingApplication.paylods;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



//@NoArgsConstructor
public class CategoryDto {
    private int catagoryId;
    @NotEmpty
    @Size(min=3,message = "minimum 4 word")
    private String categoryTitle;
    @NotEmpty
@Size(min=5)
    private String categoryDescription;

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

    public CategoryDto(int catagoryId, String categoryTitle, String categoryDescription) {
        this.catagoryId = catagoryId;
        this.categoryTitle = categoryTitle;
        this.categoryDescription = categoryDescription;
    }
    public CategoryDto() {
        // No-args constructor required by ModelMapper
    }
}
