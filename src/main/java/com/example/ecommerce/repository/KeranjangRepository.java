package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entity.Keranjang;

public interface KeranjangRepository extends JpaRepository<Keranjang, String>{
    
}
