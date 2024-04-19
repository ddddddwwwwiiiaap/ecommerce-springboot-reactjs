package com.example.ecommerce.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.entity.Kategori;
import com.example.ecommerce.service.KategoriService;

// http://localhost:8080/api/kategoris

// RestController anotation digunakan untuk menandai bahwa kelas tersebut adalah controller
@RestController
// RequestMapping anotation digunakan untuk menandai bahwa url yang digunakan adalah /api
@RequestMapping("/api")
public class KategoriController {
    
    // Autowired adalah anotation yang digunakan untuk melakukan dependency injection,
    // maksudnya adalah memasukkan object dari kelas lain ke dalam kelas yang menggunakan anotation tersebut
    @Autowired
    private KategoriService kategoriService;

    @GetMapping("/kategoris")
    public List<Kategori> findAll(){
        return kategoriService.findAll();
    }

    @GetMapping("/kategoris/{id}")
    // PathVariable anotation digunakan untuk mengambil data dari url
    public Kategori findById(@PathVariable("id") String id){
        return kategoriService.findById(id);
    }

    @PostMapping("/kategoris")
    // requestbody adalah anotation yang digunakan untuk mengambil data dari body request
    // kemudian data tersebut akan disimpan ke dalam variabel kategori
    public Kategori create(@RequestBody Kategori kategori){
        return kategoriService.create(kategori);
    }

    @PutMapping("/kategoris")
    public Kategori edit(@RequestBody Kategori kategori){
        return kategoriService.edit(kategori);
    }

    @DeleteMapping("/kategoris/{id}")
    public void deleteById(@PathVariable("id") String id){
        kategoriService.deleteById(id);
    }
}
