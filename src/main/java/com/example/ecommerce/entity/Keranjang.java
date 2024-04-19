package com.example.ecommerce.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

// Entity anotasi digunakan untuk menandai bahwa kelas ini adalah entitas yang akan disimpan di database
@Entity
// Data anotasi digunakan untuk membuat getter, setter, equals, hashcode, dan toString
@Data
public class Keranjang implements Serializable{
    
    //Id anotasi digunakan untuk menandai bahwa variabel ini adalah primary key
    @Id
    private String id;
    // JoinColumn anotasi digunakan untuk menandai bahwa variabel ini adalah foreign key
    @JoinColumn
    // ManyToOne anotasi digunakan untuk menandai bahwa hubungan antara tabel adalah many to one
    @ManyToOne
    private Produk produk;
    @JoinColumn
    @ManyToOne
    private Pengguna pengguna;
    private Double kuantitas;
    private BigDecimal harga;
    private BigDecimal jumlah;
    // Temporary anotasi digunakan untuk menandai bahwa tipe data yang digunakan adalah tanggal, dan formatnya adalah timestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date waktuPesan;
}
