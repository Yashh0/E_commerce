package com.example.major.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.aop.framework.adapter.GlobalAdvisorAdapterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.major.global.GloablData;
import com.example.major.model.Role;
import com.example.major.model.User;
import com.example.major.repository.RoleRepository;
import com.example.major.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository ;

    @GetMapping("/login")
    public String login() {
        GloablData.cart.clear();
        return "login";
    }

    @GetMapping("/register")
    public String registerGet() {
        return "register";
    }
    
    @PostMapping("/register")
public String postRegister(@ModelAttribute("user") User user , HttpServletRequest request ) throws ServletException{
    
    String password = user.getPassword();
    user.setPassword(bCryptPasswordEncoder.encode(password)) ;
    List<Role> roles = new ArrayList<>();
    roles.add(roleRepository.findById(2).get());
    user.setRoles(roles);
    userRepository.save(user);
    request.login(user.getEmail(), password);
    return "redirect:/";
}
    
}
// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// import com.example.major.model.Role;

// import javax.servlet.ServletException;
// import javax.servlet.http.HttpServletRequest;
// import java.util.ArrayList;
// import java.util.List;

// @Controller
// public class LoginController {

//     private static final Logger logger = LogManager.getLogger(LoginController.class);

//     @Autowired
//     private BCryptPasswordEncoder bCryptPasswordEncoder;

//     @Autowired
//     UserRepository userRepository;

//     @Autowired
//     RoleRepository roleRepository;

//     @GetMapping("/login")
//     public String login() {
//         return "login";
//     }

//     @GetMapping("/register")
//     public String registerGet() {
//         return "register";
//     }

//     @PostMapping("/register")
//     public String postRegister(@ModelAttribute("user") User user, HttpServletRequest request , Model model) {
//         try {
//             // Encode the password
//             String password = user.getPassword();
//             user.setPassword(bCryptPasswordEncoder.encode(password));

//             // Assign roles (assuming the role ID for admin is 1)
//             List<Role> roles = new ArrayList<>();
//             Role adminRole = roleRepository.findById(2).orElseThrow(() -> new RuntimeException("Admin role not found"));
//             roles.add(adminRole);
//             user.setRoles(roles);

//             // Save user to the database
//             userRepository.save(user);

//             // Log the user in programmatically
//             request.login(user.getEmail(), password);

//             // Log successful registration
//             logger.info("User registered successfully: {}", user.getEmail());

//             return "redirect:/";
//         } catch (Exception e) {
//             // Log registration error
//             logger.error("Error occurred during registration: {}", e.getMessage());

//             // Add error message to model and return registration view
//             model.addAttribute("error", "An error occurred during registration. Please try again.");
//             return "register";
//         }
//     }

