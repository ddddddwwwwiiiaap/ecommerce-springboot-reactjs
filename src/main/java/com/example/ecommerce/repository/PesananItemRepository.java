package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entity.PesananItem;

public interface PesananItemRepository extends JpaRepository<PesananItem, String>{
    
}
