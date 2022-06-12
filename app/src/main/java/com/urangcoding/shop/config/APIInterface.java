package com.urangcoding.shop.config;

import com.urangcoding.shop.pojo.KotaPOJO;
import com.urangcoding.shop.pojo.ProvinsiPOJO;

import retrofit2.Call;
import retrofit2.http.GET;
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
}
