package com.example.ecommerce.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Pengguna implements Serializable{
    
    @Id
    private String id;
    //JsonIgnore digunakan untuk mengabaikan field tertentu pada saat proses serialisasi dan deserialisasi JSON.
    @JsonIgnore
    private String password;
    private String nama;
    @JsonIgnore
    private String alamat;
    @JsonIgnore
    private String email;
    @JsonIgnore
    private String hp;
    @JsonIgnore
    private String roles;
    @JsonIgnore
    private Boolean isAktif;

    public Pengguna(String username) {
        this.id = username;
    }
}
