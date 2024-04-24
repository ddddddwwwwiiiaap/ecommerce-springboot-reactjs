package com.example.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entity.Keranjang;

// Buat interface KeranjangRepository yang merupakan turunan dari JpaRepository dengan parameter Keranjang dan String
public interface KeranjangRepository extends JpaRepository<Keranjang, String>{

    Optional<Keranjang> findByPenggunaIdAndProdukId(String username, String produkId);

    List<Keranjang> findByPenggunaId(String username);
    
}
