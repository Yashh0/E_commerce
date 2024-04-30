package com.example.major.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.major.global.GloablData;
import com.example.major.model.Product;
import com.example.major.service.ProductService;

import net.bytebuddy.agent.builder.AgentBuilder.CircularityLock.Global;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CartContoller {
    @Autowired
    ProductService productService;
    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id) {
        GloablData.cart.add(productService.getProductById(id).get());
        return "redirect:/shop";
    }

    @GetMapping("cart")
    public String getCart(Model model) {
        model.addAttribute("cartCount", GloablData.cart.size());
        model.addAttribute("total", GloablData.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("cart", GloablData.cart);
        return "cart";
    }
    @GetMapping("/cart/removeItem/{index}")
    public String cartItemRemove(@PathVariable int index) {
        GloablData.cart.remove(index);
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("total", GloablData.cart.stream().mapToDouble(Product::getPrice).sum());
        return "checkout";
    }
    
    
}
