package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entity.PesananLog;

public interface PesananLogRepository extends JpaRepository<PesananLog, String>{
    
}
