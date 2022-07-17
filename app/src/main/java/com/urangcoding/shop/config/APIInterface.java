package com.urangcoding.shop.config;

import com.urangcoding.shop.pojo.HomeProductPojo;
import com.urangcoding.shop.pojo.ImageSliderPojo;
import com.urangcoding.shop.pojo.KotaPOJO;
import com.urangcoding.shop.pojo.ProvinsiPOJO;
import com.urangcoding.shop.pojo.UserCreatePojo;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {

    /**
     * API Interface Provinsi
     **/

    @GET("api_project3/?folder=raja_ongkir&file=provinsi")
    Call<ProvinsiPOJO> getProvinsi();

    /**
     * API Interface Kota
     **/
    @GET("api_project3/?folder=raja_ongkir&file=kota")
    Call<KotaPOJO> getKota(@Query("provinsi") String provinsi);

    /**
     * API Interface User Register
     **/

    @FormUrlEncoded
    @POST("api_project3/index.php?folder=user&file=register")
    Call<UserCreatePojo> createUser(@FieldMap Map<String, String> fields);

    @FormUrlEncoded
    @POST("api_project3/index.php?folder=user&file=login")
    Call<UserCreatePojo> userLogin(@FieldMap Map<String, String> fields);

    /**
     * API Interface Home Slider
     **/

    @GET("api_project3/index.php?folder=home&file=sliders")
    Call<ImageSliderPojo> getImageSlider (@Query("api_key") String api_key);

    /*
    Product List Item Home
     */

    @GET("api_project3/index.php?folder=home&file=produkItem")
    Call<HomeProductPojo> getListProduct(@Query("api_key") String api_key);

}
