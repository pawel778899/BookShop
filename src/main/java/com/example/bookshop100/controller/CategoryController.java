package com.example.bookshop100.controller;


import com.example.bookshop100.model.CategoryDto;
import com.example.bookshop100.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {


    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

//    @GetMapping("/categories")
//    public String categoryList(ModelMap modelMap) {
//        modelMap.addAttribute("categories", categoryService.getAll());
//        modelMap.addAttribute("newCategory",new CategoryDto());
//        return "category";
//    }
@GetMapping("/categories")
public String categoryList(ModelMap modelMap) {
    modelMap.addAttribute("categories", categoryService.getAll());
    modelMap.addAttribute("newCategory",new CategoryDto());
    return "category-list";
}
    @PostMapping("/categories/add")
    public String addCategory(@ModelAttribute("newCategory") CategoryDto categoryDto) {
        categoryService.add(categoryDto);
        return "redirect:/categories";
    }

    @GetMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable("delete") Long id) {
    categoryService.delete(id);
        return "redirect:/categories";
    }

}


