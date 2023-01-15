package com.example.bookshop100.repository;



import com.example.bookshop100.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> getCategoryByParent(Long id);

}

