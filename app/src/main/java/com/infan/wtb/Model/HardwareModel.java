package com.infan.wtb.Model;

/**
 * Created by arrival on 3/24/18.
 */

public class HardwareModel {
    public int id;
    public String name;
    public String tag;
    public String vendor;

    public HardwareModel() {
    }

    public HardwareModel(int id, String name, String tag, String vendor) {
        this.id = id;
        this.name = name;
        this.tag = tag;
        this.vendor = vendor;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
