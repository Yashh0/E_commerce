package com.example.major.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.major.dto.ProductDTO;
import com.example.major.model.Category;
import com.example.major.model.Product;
import com.example.major.repository.ProductRepository;

import java.util.*;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProduct() {
        return productRepository.findAll(); 
    }

    public void addProduct(Product product){
        productRepository.save(product);
    }
    
    public void removeProductById(long Id) {
        productRepository.deleteById(Id);
    }
    
    public Optional<Product> getProductById(long Id) {
        return productRepository.findById(Id);
    }
    public List<Product> getAllProductByCategoryId(int id) {
        return productRepository.findAllByCategory_Id(id); 
    }
}
