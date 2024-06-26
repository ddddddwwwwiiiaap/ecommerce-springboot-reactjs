package com.example.ecommerce.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.entity.Kategori;
import com.example.ecommerce.repository.KategoriRepository;

// Service annotation digunakan untuk menandai kelas sebagai service
@Service
public class KategoriService {

    // Autowired adalah anotation yang digunakan untuk melakukan dependency injection,
    // maksudnya adalah memasukkan object dari kelas lain ke dalam kelas yang menggunakan anotation tersebut
    @Autowired
    private KategoriRepository kategoriRepository;

    public Kategori findById(String id) {
        return kategoriRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kategori dengan id " + id + " tidak ditemukan"));
    }

    public List<Kategori> findAll() {
        return kategoriRepository.findAll();
    }

    public Kategori create(Kategori kategori) {
        kategori.setId(UUID.randomUUID().toString());
        return kategoriRepository.save(kategori);
    }

    public Kategori edit(Kategori kategori) {
        return kategoriRepository.save(kategori);
    }

    public void deleteById(String id) {
        kategoriRepository.deleteById(id);
    }
}
