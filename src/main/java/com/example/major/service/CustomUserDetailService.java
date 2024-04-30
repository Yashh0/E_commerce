package com.example.major.service;

import java.util.Collection;
import java.util.Optional;

// import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.major.model.CustomUserDetail;
import com.example.major.model.User;
import com.example.major.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{

    @Autowired
        UserRepository userRepository;
        @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(email);
        user.orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return user.map(CustomUserDetail::new).get();
    }
    
}




// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import com.example.major.model.User;
// import com.example.major.repository.UserRepository;

// @Service
// public class CustomUserDetailService implements UserDetailsService {

//     @Autowired
//     private UserRepository userRepository;

//     @Override
//     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//         Optional<User> userOptional = userRepository.findUserByEmail(email);
//         User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
//         return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), (Collection<? extends GrantedAuthority>) user.getRoles());
//     }
// }
