package com.pale.londry.response;

public class Result {

    String id;
    String nama_customer;
    String jenis_pakaian;
    String berat;
    String total_harga;
    String nama_kasir;
    String tgl_simpan;
    String tgl_update;

    public String getId() {
        return id;
    }

    public String getNama_customer() {
        return nama_customer;
    }

    public String getJenis_pakaian() {
        return jenis_pakaian;
    }

    public String getBerat() {
        return berat;
    }

    public String getTotal_harga() {
        return total_harga;
    }

    public String getNama_kasir() {
        return nama_kasir;
    }

    public String getTgl_simpan() {
        return tgl_simpan;
    }

    public String getTgl_update() {
        return tgl_update;
    }
}

