package com.urangcoding.shop.pojo;

import java.util.ArrayList;
import java.util.List;

public class KotaPOJO {
    private int success;
    private String message;
    private List<Kota> data = new ArrayList<>();

    public int getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<Kota> getKotaList() {
        return data;
    }

    public class Kota {
        private String id, kota;

        public String getId() {
            return id;
        }

        public String getKota() {
            return kota;
        }
    }
}
