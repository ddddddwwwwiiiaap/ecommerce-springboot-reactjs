package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entity.Pengguna;

// Buat interface PenggunaRepository yang merupakan turunan dari JpaRepository dengan parameter Pengguna dan String
public interface PenggunaRepository extends JpaRepository<Pengguna, String>{

    Boolean existsByEmail(String email);
}
