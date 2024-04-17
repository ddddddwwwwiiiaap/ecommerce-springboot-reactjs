package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entity.Kategori;

public interface KategoriRepository extends JpaRepository<Kategori, String>{
    
}
