package com.example.major.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.major.global.GloablData;
import com.example.major.service.CategoryService;
import com.example.major.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {
    @Autowired
    CategoryService categoryService ;
    @Autowired
    ProductService productService ;
     
    @GetMapping({"/", "/home"})
    public String  home(Model model) {
        model.addAttribute("cartCount", GloablData.cart.size());

        return "index";
    }
     
    @GetMapping("/shop")
    public String  shop(Model model) {
        model.addAttribute("categories", categoryService.getAllCategory());
      
        model.addAttribute("cartCount", GloablData.cart.size());
        model.addAttribute("products", productService.getAllProduct());
        return "shop";
    }
    @GetMapping("/shop/category/{id}")
    public String  shopByCategry(Model model , @PathVariable int id) {
       
        model.addAttribute("cartCount", GloablData.cart.size());
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", productService.getAllProductByCategoryId(id));
        return "shop";
    }
    @GetMapping("/shop/viewproduct/{id}")
    public String viewProductById (Model model , @PathVariable int id) {
       
        model.addAttribute("cartCount", GloablData.cart.size());
        model.addAttribute("product", productService.getProductById(id).get());
        return "viewProduct";
    }


    
    
    
    
}
