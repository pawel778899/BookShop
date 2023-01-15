package com.example.bookshop100.service;




import com.example.bookshop100.model.Category;
import com.example.bookshop100.model.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAll();

    List<Category> getParents();
    List<CategoryDto> getAllParents();

    Category getById(Integer id);

    void add(CategoryDto categoryDto);


    void delete(Long id);
}
