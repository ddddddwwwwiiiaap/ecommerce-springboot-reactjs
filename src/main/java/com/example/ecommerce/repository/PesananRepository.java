package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entity.Pesanan;

public interface PesananRepository extends JpaRepository<Pesanan, String>{
    
}
