package com.urangcoding.shop.pojo;

import android.media.Image;

import java.util.ArrayList;
import java.util.List;

public class ImageSliderPojo {

    private int success;
    private String message;
    private List<ImageSliderPojo.ImageSlider> data = new ArrayList<>();

    public int getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<ImageSliderPojo.ImageSlider> getData() {
        return data;
    }

    public class ImageSlider {

        //pastikan sama dengan respon pada api
        private String image, deskripsi;

        public String getImage() {
            return image;
        }

        public String getDeskripsi() {
            return deskripsi;
        }
    }
}
