package com.example.ecommerce.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.ecommerce.entity.Pengguna;
import com.example.ecommerce.exception.BadRequestExeption;
import com.example.ecommerce.repository.PenggunaRepository;

@Service
public class PenggunaService {

    @Autowired
    private PenggunaRepository penggunaRepository;

    public Pengguna findById(String id) {
        return penggunaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pengguna dengan id " + id + " tidak ditemukan"));
    }

    public List<Pengguna> findAll() {
        return penggunaRepository.findAll();
    }

    public Pengguna create(Pengguna pengguna) {
        if (!StringUtils.hasText(pengguna.getId())) {
            throw new BadRequestExeption("Username tidak boleh kosong");
        }

        if (penggunaRepository.existsById(pengguna.getId())) {
            throw new BadRequestExeption("Username " + pengguna.getId() + " sudah terdaftar");
        }

        if (!StringUtils.hasText(pengguna.getEmail())) {
            throw new BadRequestExeption("Email tidak boleh kosong");
        }

        if (penggunaRepository.existsByEmail(pengguna.getEmail())) {
            throw new BadRequestExeption("Email " + pengguna.getEmail() + " sudah terdaftar");
        }

        pengguna.setIsAktif(true);

        return penggunaRepository.save(pengguna);
    }

    public Pengguna edit(Pengguna pengguna) {

        if (!StringUtils.hasText(pengguna.getId())) {
            throw new BadRequestExeption("Username tidak boleh kosong");
        }

        if (!StringUtils.hasText(pengguna.getEmail())) {
            throw new BadRequestExeption("Email tidak boleh kosong");
        }

        return penggunaRepository.save(pengguna);
    }

    public void deleteById(String id) {
        penggunaRepository.deleteById(id);
    }
}
