package com.infan.wtb.Model;

import java.util.HashMap;

/**
 * Created by arrival on 3/24/18.
 */

public class ClassificationResultModel {
    public BarangModel barang;
    public HashMap<String, Integer> mapHardware;

    public ClassificationResultModel(BarangModel barang, HashMap<String, Integer> mapHardware) {
        this.barang = barang;
        this.mapHardware = mapHardware;
    }

    public BarangModel getBarang() {
        return barang;
    }

    public void setBarang(BarangModel barang) {
        this.barang = barang;
    }

    public HashMap<String, Integer> getMapHardware() {
        return mapHardware;
    }

    public void setMapHardware(HashMap<String, Integer> mapHardware) {
        this.mapHardware = mapHardware;
    }
}
