package com.tw.tradeaway.controller;

import com.tw.tradeaway.domain.Category;
import com.tw.tradeaway.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/categories")
    public List<Category> listCategories(){
        return categoryService.findAllCategories();
    }
}
