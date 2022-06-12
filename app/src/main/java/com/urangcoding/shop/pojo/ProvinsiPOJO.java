package com.urangcoding.shop.pojo;

import java.util.ArrayList;
import java.util.List;

public class ProvinsiPOJO {

    private int success;
    private String message;

    private List<Provinsi> data = new ArrayList<>();

    public int getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<Provinsi> getData() {
        return data;
    }

    public class Provinsi {

        private String id, provinsi;

        public String getId() {
            return id;
        }

        public String getProvinsi() {
            return provinsi;
        }
    }
}
