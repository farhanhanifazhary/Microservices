package com.hanif.order.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long produk_id;
    private Long id_pelanggan;
    private int jumlah;
    private double harga;
    private double total;
    private String tanggal;
    private String status;

    public Long getId() {
        return id;
    }

    public Long getId_produk() {
        return produk_id;
    }

    public void setId_produk(Long produk_id) {
        this.produk_id = produk_id;
    }

    public Long getId_pelanggan() {
        return id_pelanggan;
    }

    public void setId_pelanggan(Long id_pelanggan) {
        this.id_pelanggan = id_pelanggan;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", produk_id=" + produk_id +
                ", id_pelanggan=" + id_pelanggan +
                ", harga=" + harga +
                ", jumlah=" + jumlah +
                ", total=" + total +
                ", tanggal='" + tanggal + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
