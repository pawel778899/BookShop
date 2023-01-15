package com.example.bookshop100.service.impl;



import com.example.bookshop100.model.Category;
import com.example.bookshop100.model.CategoryDto;
import com.example.bookshop100.repository.CategoryRepository;
import com.example.bookshop100.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> getAllParents() {
//        return categoryRepository.findAll();
         List<Category> categories = categoryRepository.findAll();
        return categories.stream().filter(category -> category.getParent()==null).map(category -> new CategoryDto(category.getId(),category.getName(), -1L)).toList();

    }

    @Override
    public List<CategoryDto> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> new CategoryDto(category.getId(), category.getName(), getParentId(category))).toList();
    }

    private Long getParentId(Category category) {
        if (category.getParent()==null) {
            return -1L;
        }
        else{
           return category.getParent().getId();
        }
    }

    @Override
    public List<Category> getParents() {
        return categoryRepository.getCategoryByParent(null);
    }

    @Override
    public Category getById(Integer id) {
        return categoryRepository.getById(Long.valueOf(id));
    }

    @Override
    public void add(CategoryDto categoryDto) {
        Optional<Category> parentCategory = categoryRepository.findById(categoryDto.getParentId());
        parentCategory.ifPresent(pC->{
            Category category = new Category();
            category.setName(categoryDto.getName());
            category.setParent(pC);
            categoryRepository.save(category);
        });


    }
}

