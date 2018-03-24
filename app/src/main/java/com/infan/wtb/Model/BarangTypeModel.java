package com.infan.wtb.Model;

/**
 * Created by arrival on 3/24/18.
 */

public class BarangTypeModel {
    public BarangModel barang;
    public String type;
    public int sum;

    public BarangTypeModel() {
    }

    public BarangTypeModel(BarangModel barang, String type, int sum) {
        this.type = type;
        this.sum = sum;
        this.barang = barang;
    }

    public BarangModel getBarang() {
        return barang;
    }

    public void setBarang(BarangModel barang) {
        this.barang = barang;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
