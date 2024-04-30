package com.example.major.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.major.model.Role;

public interface RoleRepository extends JpaRepository<Role , Integer>  {
    
}
