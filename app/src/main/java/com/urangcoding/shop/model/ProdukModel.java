package com.urangcoding.shop.model;

public class ProdukModel {

    private String imgItem, tvNamaItem, tvHarga, tvSeller;

    public ProdukModel(String imgItem, String tvNamaItem, String tvHarga, String tvSeller) {
        this.imgItem = imgItem;
        this.tvNamaItem = tvNamaItem;
        this.tvHarga = tvHarga;
        this.tvSeller = tvSeller;
    }

    public String getImgItem() {
        return imgItem;
    }

    public void setImgItem(String imgItem) {
        this.imgItem = imgItem;
    }

    public String getTvNamaItem() {
        return tvNamaItem;
    }

    public void setTvNamaItem(String tvNamaItem) {
        this.tvNamaItem = tvNamaItem;
    }

    public String getTvHarga() {
        return tvHarga;
    }

    public void setTvHarga(String tvHarga) {
        this.tvHarga = tvHarga;
    }

    public String getTvSeller() {
        return tvSeller;
    }

    public void setTvSeller(String tvSeller) {
        this.tvSeller = tvSeller;
    }
}
