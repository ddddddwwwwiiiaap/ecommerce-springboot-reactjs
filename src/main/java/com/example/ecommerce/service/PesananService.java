package com.example.ecommerce.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.example.ecommerce.exception.BadRequestExeption;
import com.example.ecommerce.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecommerce.entity.Pengguna;
import com.example.ecommerce.entity.Pesanan;
import com.example.ecommerce.entity.PesananItem;
import com.example.ecommerce.entity.Produk;
import com.example.ecommerce.model.KeranjangRequest;
import com.example.ecommerce.model.PesananRequest;
import com.example.ecommerce.model.PesananResponse;
import com.example.ecommerce.model.StatusPesanan;
import com.example.ecommerce.repository.PesananItemRepository;
import com.example.ecommerce.repository.PesananRepository;
import com.example.ecommerce.repository.ProdukRepository;

@Service
public class PesananService {

    @Autowired
    private ProdukRepository produkRepository;
    @Autowired
    private PesananRepository pesananRepository;
    @Autowired
    private PesananItemRepository pesananItemRepository;
    @Autowired
    private KeranjangService keranjangService;
    @Autowired
    private PesananLogService pesananLogService;

    public PesananResponse create(String username, PesananRequest request) {
        Pesanan pesanan = new Pesanan();
        pesanan.setId(UUID.randomUUID().toString());
        pesanan.setTanggal(new Date());
        pesanan.setNomor(generateNomorPesanan());
        pesanan.setPengguna(new Pengguna(username));
        pesanan.setAlamatPengiriman(request.getAlamatPengiriman());
        pesanan.setStatusPesanan(StatusPesanan.DRAFT);
        pesanan.setWaktuPesan(new Date());

        List<PesananItem> items = new ArrayList<>();
        for (KeranjangRequest k : request.getItems()) {
            Produk produk = produkRepository.findById(k.getProdukId())
                    .orElseThrow(() -> new BadRequestExeption("Produk ID " + k.getProdukId() + " tidak ditemukan"));

            if (produk.getStok() < k.getKuantitas()) {
                throw new BadRequestExeption("Stok produk " + produk.getNama() + " tidak mencukupi");
            }

            PesananItem pi = new PesananItem();
            pi.setId(UUID.randomUUID().toString());
            pi.setProduk(produk);
            pi.setDeskripsi(produk.getNama());
            pi.setKuantitas(k.getKuantitas());
            pi.setHarga(produk.getHarga());
            pi.setJumlah(new BigDecimal(pi.getHarga().doubleValue() * pi.getKuantitas()));
            pi.setPesanan(pesanan);
            items.add(pi);

        }

        BigDecimal jumlah = BigDecimal.ZERO;
        for (PesananItem pi : items) {
            jumlah = jumlah.add(pi.getJumlah());
        }

        pesanan.setJumlah(jumlah);
        pesanan.setOngkir(request.getOngkir());
        pesanan.setTotal(pesanan.getJumlah().add(pesanan.getOngkir()));

        Pesanan saved = pesananRepository.save(pesanan);
        for (PesananItem pesananItem : items) {
            pesananItemRepository.save(pesananItem);
            Produk produk = pesananItem.getProduk();
            produk.setStok(produk.getStok() - pesananItem.getKuantitas());
            produkRepository.save(produk);
            keranjangService.delete(username, produk.getId());
        }

        // Catat log
        pesananLogService.createLog(username, pesanan, PesananLogService.DRAFT, "Pesanan sukses dibuat");
        PesananResponse pesananResponse = new PesananResponse(saved, items);
        return pesananResponse;
    }

    @Transactional
    public Pesanan cancelPesanan(String pesananId, String userId){
        Pesanan pesanan = pesananRepository.findById(pesananId)
                .orElseThrow(() -> new ResourceNotFoundException("Pesanan ID " + pesananId + " tidak ditemukan"));
        if(!userId.equals(pesanan.getPengguna().getId())){
            throw new BadRequestExeption("Pesanan ini hanya bisa dibatalkan oleh yang bersangkutan");
        }
        if(!StatusPesanan.DRAFT.equals(pesanan.getStatusPesanan())){
            throw new BadRequestExeption("Pesanan ini tidak bisa dibatalkan karena sudah diproses");
        }
        pesanan.setStatusPesanan(StatusPesanan.DIBATALKAN);
        Pesanan saved = pesananRepository.save(pesanan);
        pesananLogService.createLog(userId, pesanan, PesananLogService.DIBATALKAN, "Pesanan dibatalkan");
        return saved;
    }

    @Transactional
    public Pesanan terimaPesanan(String pesananId, String userId){
        Pesanan pesanan = pesananRepository.findById(pesananId)
                .orElseThrow(() -> new ResourceNotFoundException("Pesanan ID " + pesananId + " tidak ditemukan"));
        if(!userId.equals(pesanan.getPengguna().getId())){
            throw new BadRequestExeption("Pesanan ini hanya bisa dibatalkan oleh yang bersangkutan");
        }
        if(!StatusPesanan.PENGIRIMAN.equals(pesanan.getStatusPesanan())){
            throw new BadRequestExeption("Penerimaan gagal, status pesanan saat ini adalah " + pesanan.getStatusPesanan());
        }
        pesanan.setStatusPesanan(StatusPesanan.DIBATALKAN);
        Pesanan saved = pesananRepository.save(pesanan);
        pesananLogService.createLog(userId, pesanan, PesananLogService.DIBATALKAN, "Pesanan dibatalkan");
        return saved;
    }

    public List<Pesanan> findAllPesananUser(String userId, int page, int limit){
        return pesananRepository.findByPenggunaId(userId, PageRequest.of(page, limit, Sort.by("waktuPesan").descending()));
    }

    public List<Pesanan> search(String filterText, int page, int limit){
        return pesananRepository.search(filterText.toLowerCase(), PageRequest.of(page, limit, Sort.by("waktuPesan").descending()));
    }

    private String generateNomorPesanan() {
        return String.format("%016d", System.nanoTime());
    }

    @Transactional
    public Pesanan konfirmasiPembayaran(String pesananId, String userId){
        Pesanan pesanan = pesananRepository.findById(pesananId)
                .orElseThrow(() -> new ResourceNotFoundException("Pesanan ID " + pesananId + " tidak ditemukan"));
        if(!StatusPesanan.DRAFT.equals(pesanan.getStatusPesanan())){
            throw new BadRequestExeption("Konfirmasi pembayaran gagal, status pesanan saat ini adalah " + pesanan.getStatusPesanan());
        }
        pesanan.setStatusPesanan(StatusPesanan.PEMBAYARAN);
        Pesanan saved = pesananRepository.save(pesanan);
        pesananLogService.createLog(userId, pesanan, PesananLogService.PEMBAYARAN, "Pembayaran sukses dikonfirmasi");
        return saved;
    }

    @Transactional
    public Pesanan packing(String pesananId, String userId){
        Pesanan pesanan = pesananRepository.findById(pesananId)
                .orElseThrow(() -> new ResourceNotFoundException("Pesanan ID " + pesananId + " tidak ditemukan"));
        if(!StatusPesanan.PEMBAYARAN.equals(pesanan.getStatusPesanan())){
            throw new BadRequestExeption("Packing gagal, status pesanan saat ini adalah " + pesanan.getStatusPesanan());
        }
        pesanan.setStatusPesanan(StatusPesanan.PACKING);
        Pesanan saved = pesananRepository.save(pesanan);
        pesananLogService.createLog(userId, pesanan, PesananLogService.PACKING, "Pesanan sedang dipacking");
        return saved;
    }

    @Transactional
    public Pesanan kirim(String pesananId, String userId){
        Pesanan pesanan = pesananRepository.findById(pesananId)
                .orElseThrow(() -> new ResourceNotFoundException("Pesanan ID " + pesananId + " tidak ditemukan"));
        if(!StatusPesanan.PACKING.equals(pesanan.getStatusPesanan())){
            throw new BadRequestExeption("Pengiriman gagal, status pesanan saat ini adalah " + pesanan.getStatusPesanan());
        }
        pesanan.setStatusPesanan(StatusPesanan.PENGIRIMAN);
        Pesanan saved = pesananRepository.save(pesanan);
        pesananLogService.createLog(userId, pesanan, PesananLogService.PENGIRIMAN, "Pesanan sedang dikirim");
        return saved;
    }
}
