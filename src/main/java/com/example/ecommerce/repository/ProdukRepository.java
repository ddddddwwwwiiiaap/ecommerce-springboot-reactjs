package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entity.Produk;

// Buat interface ProdukRepository yang merupakan turunan dari JpaRepository dengan parameter Produk dan String
public interface ProdukRepository extends JpaRepository<Produk, String>{
    
}
