package com.example.major.service;

import com.example.major.model.Category;
import com.example.major.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.*;

// import javax.swing.text.html.Option;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;


    public List<Category> getAllCategory(){
        return  categoryRepository.findAll();
    }

    public void addCategory (Category category) {
        categoryRepository.save(category);
    }


    public void removeCategoryById(int Id) {
        categoryRepository.deleteById(Id);
    }
    
    public Optional<Category> getCategoryById(int Id) {
        return categoryRepository.findById(Id);
    }
}
