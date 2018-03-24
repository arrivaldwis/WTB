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
    public int vga_id;
    public int processor_id;
    public int memory_id;
    public int display_id;

    public BarangModel() {
        this.deskripsi = "";
        this.foto = "";
        this.harga = "";
        this.kategori_id = 0;
        this.nama = "";
        this.rating = 0;
        this.vga_id = 0;
        this.processor_id = 0;
        this.display_id = 0;
        this.rating = 0;
        this.url = "";
        this.tag = "";
    }

    public BarangModel(String deskripsi, String foto, String harga, int kategori_id, String nama, int rating, String url, String tag,
                       int vga_id, int memory_id, int display_id, int processor_id) {
        this.deskripsi = deskripsi;
        this.foto = foto;
        this.harga = harga;
        this.kategori_id = kategori_id;
        this.nama = nama;
        this.rating = rating;
        this.url = url;
        this.tag = tag;
        this.vga_id = vga_id;
        this.memory_id = memory_id;
        this.display_id = display_id;
        this.processor_id = processor_id;
    }

    public int getVga_id() {
        return vga_id;
    }

    public void setVga_id(int vga_id) {
        this.vga_id = vga_id;
    }

    public int getProcessor_id() {
        return processor_id;
    }

    public void setProcessor_id(int processor_id) {
        this.processor_id = processor_id;
    }

    public int getMemory_id() {
        return memory_id;
    }

    public void setMemory_id(int memory_id) {
        this.memory_id = memory_id;
    }

    public int getDisplay_id() {
        return display_id;
    }

    public void setDisplay_id(int display_id) {
        this.display_id = display_id;
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

