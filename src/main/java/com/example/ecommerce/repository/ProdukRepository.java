package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entity.Produk;

public interface ProdukRepository extends JpaRepository<Produk, String>{
    
}
