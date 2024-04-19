package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entity.Kategori;

// Buat interface KategoriRepository yang merupakan turunan dari JpaRepository dengan parameter Kategori dan String
public interface KategoriRepository extends JpaRepository<Kategori, String>{
    
}
