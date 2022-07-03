package com.urangcoding.shop.pojo;

import java.util.ArrayList;
import java.util.List;

public class HomeProductPojo {

    private int success;
    private String messages;
    public List<HomeProductPojo.Vivo> list_vivo = new ArrayList<>();

    public int getSuccess() {
        return success;
    }

    public String getMessages() {
        return messages;
    }

    public List<Vivo> getList_vivo() {
        return list_vivo;
    }

    public class Vivo {
        private String image, deskripsi, harga, seller;

        public String getImage() {
            return image;
        }

        public String getDeskripsi() {
            return deskripsi;
        }

        public String getHarga() {
            return harga;
        }

        public String getSeller() {
            return seller;
        }
    }
}
