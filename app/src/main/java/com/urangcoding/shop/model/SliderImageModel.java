package com.urangcoding.shop.model;

public class SliderImageModel {

    private String iv_auto_image_slider, tv_auto_image_slider;

    public SliderImageModel(String iv_auto_image_slider, String tv_auto_image_slider) {
        this.iv_auto_image_slider = iv_auto_image_slider;
        this.tv_auto_image_slider = tv_auto_image_slider;
    }

    public String getIv_auto_image_slider() {
        return iv_auto_image_slider;
    }

    public void setIv_auto_image_slider(String iv_auto_image_slider) {
        this.iv_auto_image_slider = iv_auto_image_slider;
    }

    public String getTv_auto_image_slider() {
        return tv_auto_image_slider;
    }

    public void setTv_auto_image_slider(String tv_auto_image_slider) {
        this.tv_auto_image_slider = tv_auto_image_slider;
    }
}
