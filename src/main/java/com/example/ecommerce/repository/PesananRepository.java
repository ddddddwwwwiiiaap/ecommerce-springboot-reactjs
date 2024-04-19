package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entity.Pesanan;

// Buat interface PesananRepository yang merupakan turunan dari JpaRepository dengan parameter Pesanan dan String
public interface PesananRepository extends JpaRepository<Pesanan, String>{
    
}
