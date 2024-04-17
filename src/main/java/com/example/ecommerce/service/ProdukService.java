package com.example.ecommerce.service;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.example.ecommerce.entity.Produk;
import com.example.ecommerce.exception.BadRequestExeption;
import com.example.ecommerce.repository.KategoriRepository;
import com.example.ecommerce.repository.ProdukRepository;

@Service
public class ProdukService {

    @Autowired
    private KategoriRepository kategoriRepository;

    @Autowired
    private ProdukRepository produkRepository;

    public List<Produk> findAll() {
        return produkRepository.findAll();
    }

    public Produk findById(String id) {
        return produkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produk dengan id " + id + " tidak ditemukan"));
    }

    public Produk create(Produk produk) {
        if (!StringUtils.hasText(produk.getNama())){
            throw new BadRequestExeption("Nama produk tidak boleh kosong");
        }

        if (produk.getKategori() == null){
            throw new BadRequestExeption("Kategori produk tidak boleh kosong");
        }

        if (!StringUtils.hasText(produk.getKategori().getId())){
            throw new BadRequestExeption("Id kategori produk tidak boleh kosong");
        }

        kategoriRepository.findById(produk.getKategori().getId())
                .orElseThrow(() -> new BadRequestExeption("Kategori produk dengan id " + produk.getKategori().getId() + " tidak ditemukan"));
        produk.setId(UUID.randomUUID().toString());
        return produkRepository.save(produk);
    }

    public Produk edit(Produk produk) {
        if(!StringUtils.hasText(produk.getId())) {
            throw new BadRequestExeption("Id produk tidak boleh kosong");
        }

        if (!StringUtils.hasText(produk.getNama())){
            throw new BadRequestExeption("Nama produk tidak boleh kosong");
        }

        if (produk.getKategori() == null){
            throw new BadRequestExeption("Kategori produk tidak boleh kosong");
        }

        if (!StringUtils.hasText(produk.getKategori().getId())){
            throw new BadRequestExeption("Id kategori produk tidak boleh kosong");
        }

        kategoriRepository.findById(produk.getKategori().getId())
                .orElseThrow(() -> new BadRequestExeption("Kategori produk dengan id " + produk.getKategori().getId() + " tidak ditemukan"));
        return produkRepository.save(produk);
    }

    public Produk ubahGambar(String id, String gambar) {
        Produk produk = new Produk();
        produk.setGambar(gambar);
        return produkRepository.save(produk);
    }

    public void deleteById(String id) {
        produkRepository.deleteById(id);
    }
}
