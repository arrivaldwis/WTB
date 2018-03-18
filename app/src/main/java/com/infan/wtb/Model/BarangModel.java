package com.infan.wtb.Model;

/**
 * Created by Infan Diskamulya H on 12/31/2017.
 */

public class BarangModel {
    public String deskripsi;
    public String foto;
    public String harga;
    public int kategori_id;
    public String nama;
    public int rating;
    public String url;
    public String tag;

    public BarangModel() {
        this.deskripsi = "";
        this.foto = "";
        this.harga = "";
        this.kategori_id = 0;
        this.nama = "";
        this.rating = 0;
        this.url = "";
        this.tag = "";
    }

    public BarangModel(String deskripsi, String foto, String harga, int kategori_id, String nama, int rating, String url, String tag) {
        this.deskripsi = deskripsi;
        this.foto = foto;
        this.harga = harga;
        this.kategori_id = kategori_id;
        this.nama = nama;
        this.rating = rating;
        this.url = url;
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDeskripsi() {

        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {

        this.deskripsi = deskripsi;
    }

    public String getFoto() {

        return foto;
    }

    public void setFoto(String foto) {

        this.foto = foto;
    }

    public String getHarga() {

        return harga;
    }

    public void setHarga(String harga) {

        this.harga = harga;
    }

    public int getKategori_id() {

        return kategori_id;
    }

    public void setKategori_id(int kategori_id) {

        this.kategori_id = kategori_id;
    }

    public String getNama() {

        return nama;
    }

    public void setNama(String nama) {

        this.nama = nama;
    }

    public int getRating() {

        return rating;
    }

    public void setRating(int rating) {

        this.rating = rating;
    }
}

