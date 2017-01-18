package com.tw.tradeaway.service;

import com.tw.tradeaway.domain.Category;
import com.tw.tradeaway.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public List<Category> findAllCategories(){
        Iterable<Category> categoryIterable = categoryRepository.findAll();
        List<Category> categories = new ArrayList<>();
        categoryIterable.forEach(categories::add);
        return categories;
    }
}
