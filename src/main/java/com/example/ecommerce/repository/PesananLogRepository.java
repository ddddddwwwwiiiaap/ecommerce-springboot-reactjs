package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entity.PesananLog;

// Buat interface PesananLogRepository yang merupakan turunan dari JpaRepository dengan parameter PesananLog dan String
public interface PesananLogRepository extends JpaRepository<PesananLog, String>{
    
}
