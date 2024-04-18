package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entity.Pengguna;

public interface PenggunaRepository extends JpaRepository<Pengguna, String>{

    Boolean existsByEmail(String email);
}
