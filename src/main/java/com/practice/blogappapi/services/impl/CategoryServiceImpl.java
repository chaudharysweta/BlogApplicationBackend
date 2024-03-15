package com.practice.blogappapi.services.impl;

import com.practice.blogappapi.entities.Category;
import com.practice.blogappapi.exceptions.ResourceNotFoundException;
import com.practice.blogappapi.payloads.CategoryDto;
import com.practice.blogappapi.repositories.CategoryRepo;
import com.practice.blogappapi.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category cat=this.modelMapper.map(categoryDto,Category.class);
        Category addedCat=this.categoryRepo.save(cat);
        return this.modelMapper.map(addedCat,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatedcat = this.categoryRepo.save(cat);
        return this.modelMapper.map(updatedcat,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","category id",categoryId));
        this.categoryRepo.delete(cat);

    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category cat =this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","category id",categoryId));

        return this.modelMapper.map(cat,CategoryDto.class);
    }


    //
    @Override
    public List<CategoryDto> getCategories() {

        //get all the category from database through category.Repo interface and store the values in category.
        List<Category>categories = this.categoryRepo.findAll();
        List<CategoryDto> catDtos = categories.stream().map((cat)->this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
        return catDtos;
    }
}
//categories.stream()-> converts the list of category into stream
//A stream is a sequence of elements that can be processed in a functional style.
//map((cat) -> this.modelMapper.map(cat, CategoryDto.class)):
// Applies the map operation to transform each Category entity into a CategoryDto using the modelMapper.
//collect(Collectors.toList()): Collects the transformed CategoryDto objects into a new list.
//The Collectors.toList() collector is used to accumulate the elements of the stream into a new List.