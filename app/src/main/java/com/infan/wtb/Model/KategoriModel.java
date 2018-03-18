package com.infan.wtb.Model;

/**
 * Created by Infan Diskamulya H on 1/7/2018.
 */

public class KategoriModel {
    public String nama_kategori;
    public int id;
    public String imageurl;

    public KategoriModel() {
        this.nama_kategori = "";
        this.id = 0;
        this.imageurl = "";
    }

    public KategoriModel(String nama, int id, String imageurl) {
        this.nama_kategori = nama;
        this.id = id;
        this.imageurl = imageurl;
    }

    public String getNama() {
        return nama_kategori;
    }

    public void setNama(String nama) {
        this.nama_kategori = nama;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
