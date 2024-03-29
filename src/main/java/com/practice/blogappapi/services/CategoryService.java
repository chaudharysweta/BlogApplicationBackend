package com.practice.blogappapi.services;

import com.practice.blogappapi.entities.Category;
import com.practice.blogappapi.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    //create
    CategoryDto createCategory(CategoryDto categoryDto);

    //update
    CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);

    //delete
    void deleteCategory(Integer categoryId);

    //get category
    CategoryDto getCategory(Integer categoryId);

    //get all category
    List<CategoryDto>getCategories();
}
