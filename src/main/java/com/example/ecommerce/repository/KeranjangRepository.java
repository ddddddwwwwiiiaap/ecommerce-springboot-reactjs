package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entity.Keranjang;

// Buat interface KeranjangRepository yang merupakan turunan dari JpaRepository dengan parameter Keranjang dan String
public interface KeranjangRepository extends JpaRepository<Keranjang, String>{
    
}
