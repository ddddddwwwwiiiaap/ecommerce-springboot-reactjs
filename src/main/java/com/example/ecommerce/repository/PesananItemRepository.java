package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entity.PesananItem;

// Buat interface PesananItemRepository yang merupakan turunan dari JpaRepository dengan parameter PesananItem dan String
public interface PesananItemRepository extends JpaRepository<PesananItem, String>{
    
}
