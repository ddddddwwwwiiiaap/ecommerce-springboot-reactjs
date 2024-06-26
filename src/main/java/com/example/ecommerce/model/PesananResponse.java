package com.example.ecommerce.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.ecommerce.entity.Pesanan;
import com.example.ecommerce.entity.PesananItem;

import lombok.Data;

@Data
public class PesananResponse implements Serializable{
    
    private String id;
    private String nomorPesanan;
    private Date tanggal;
    private String namaPelanggan;
    private String alamatPengiriman;
    private Date waktuPesan;
    private BigDecimal jumlah;
    private BigDecimal ongkir;
    private BigDecimal total;
    private List<PesananResponse.Item> items;

    public PesananResponse(Pesanan pesanan, List<PesananItem> pesananItems){
        this.id = pesanan.getId();
        this.nomorPesanan = pesanan.getNomor();
        this.tanggal = pesanan.getTanggal();
        this.namaPelanggan = pesanan.getPengguna().getNama();
        this.alamatPengiriman = pesanan.getAlamatPengiriman();
        this.waktuPesan = pesanan.getWaktuPesan();
        this.jumlah = pesanan.getJumlah();
        this.ongkir = pesanan.getOngkir();
        this.total = pesanan.getTotal();
        this.items = new ArrayList<>();
        for (PesananItem pesananItem : pesananItems) { //Mengambil data dari pesananItem
            Item item = new Item();
            item.setProdukId(pesananItem.getProduk().getId());
            item.setNamaProduk(pesananItem.getDeskripsi());
            item.setKuantitas(pesananItem.getKuantitas());
            item.setHarga(pesananItem.getHarga());
            item.setJumlah(pesananItem.getJumlah());
            this.items.add(item);
        }
    }

    @Data //Kenapa pake anotasi data? karena anotasi data menggantikan anotasi getter, setter, equals, hashCode, dan toString.
    private static class Item implements Serializable{
        private String produkId;
        private String namaProduk;
        private Double kuantitas;
        private BigDecimal harga;
        private BigDecimal jumlah;
    }
}
