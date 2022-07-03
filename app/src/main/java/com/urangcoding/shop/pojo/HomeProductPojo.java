package com.urangcoding.shop.pojo;

import java.util.ArrayList;
import java.util.List;

public class HomeProductPojo {

    private int success;
    private String messages;
    public List<HomeProductPojo.Vivo> list_vivo = new ArrayList<>();
    public final List<HomeProductPojo.Samsung> list_samsung = new ArrayList<>();

    public int getSuccess() {
        return success;
    }

    public String getMessages() {
        return messages;
    }

    public List<Vivo> getList_vivo() {
        return list_vivo;
    }

    public List<Samsung> getList_samsung() {
        return list_samsung;
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

    public class Samsung {
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
