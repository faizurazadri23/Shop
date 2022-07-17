package com.urangcoding.shop;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.urangcoding.shop.adapter.OppoAdapter;
import com.urangcoding.shop.adapter.SamsungAdapter;
import com.urangcoding.shop.adapter.SliderImageAdapter;
import com.urangcoding.shop.adapter.VivoAdapter;
import com.urangcoding.shop.adapter.XiaomiAdapter;
import com.urangcoding.shop.config.APIClient;
import com.urangcoding.shop.config.APIInterface;
import com.urangcoding.shop.model.ProdukModel;
import com.urangcoding.shop.model.SliderImageModel;
import com.urangcoding.shop.pojo.HomeProductPojo;
import com.urangcoding.shop.pojo.ImageSliderPojo;
import com.urangcoding.shop.util.SessionManager;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private SessionManager sessionManager;

    private SliderView imageSlider;
    private String api_key, nama, email, userName, message;

    private int success = 0;
    private APIInterface apiInterface;

    private RecyclerView rvVivo, rv_samsung, rv_Oppo, rv_xiaomi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageSlider = findViewById(R.id.imageSlider);
        rvVivo  = findViewById(R.id.rvVivo);
        rv_samsung = findViewById(R.id.rvSamsung);
        rv_Oppo = findViewById(R.id.rvOPPO);
        rv_xiaomi = findViewById(R.id.rvXiaomi);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        apiInterface = APIClient.getClient().create(APIInterface.class);

        //Mengambil session

        HashMap<String, String> user = sessionManager.getUserDetail();
        api_key = user.get(sessionManager.API_KEY);

        imageSliders();

        listItemProduct();

    }

    private void listItemProduct(){

        Call<HomeProductPojo> homeProductPojoCall = apiInterface.getListProduct(api_key);
        homeProductPojoCall.enqueue(new Callback<HomeProductPojo>() {
            @Override
            public void onResponse(Call<HomeProductPojo> call, Response<HomeProductPojo> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Error Code " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                success =response.body().getSuccess();

                if (success==1){
                    List<HomeProductPojo.Vivo> dataVivo = response.body().getList_vivo();

                    ArrayList<ProdukModel> listItemVivo = new ArrayList<>();
                    listItemVivo.clear();

                    for (HomeProductPojo.Vivo datasVivo : dataVivo){
                        ProdukModel md = new ProdukModel(datasVivo.getImage(),
                                datasVivo.getDeskripsi(),
                                formatRupiah(Double.parseDouble(datasVivo.getHarga())),
                                datasVivo.getSeller());

                        listItemVivo.add(md);
                    }


                    VivoAdapter vivoAdapter = new VivoAdapter(listItemVivo, getApplicationContext());
                    vivoAdapter.notifyDataSetChanged();

                    rvVivo.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                    rvVivo.setHasFixedSize(true);
                    rvVivo.setAdapter(vivoAdapter);

                    /*
                    Samsung
                     */
                    List<HomeProductPojo.Samsung> dataSamsung = response.body().getList_samsung();

                    ArrayList<ProdukModel> listItemSamsung = new ArrayList<>();
                    listItemSamsung.clear();



                    for (HomeProductPojo.Samsung datasSamsung : dataSamsung){
                        ProdukModel md = new ProdukModel(datasSamsung.getImage(),
                                datasSamsung.getDeskripsi(),
                                formatRupiah(Double.parseDouble(datasSamsung.getHarga())),
                                datasSamsung.getSeller());

                        listItemSamsung.add(md);
                    }


                    SamsungAdapter samsungAdapter = new SamsungAdapter(listItemSamsung, getApplicationContext());
                    samsungAdapter.notifyDataSetChanged();

                    rv_samsung.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                    rv_samsung.setHasFixedSize(true);
                    rv_samsung.setAdapter(samsungAdapter);

                     /*
                    Oppo
                     */
                    List<HomeProductPojo.Oppo> dataOppo = response.body().getList_oppo();

                    ArrayList<ProdukModel> listItemOppo = new ArrayList<>();
                    listItemOppo.clear();



                    for (HomeProductPojo.Oppo datasOppo : dataOppo){
                        ProdukModel md = new ProdukModel(datasOppo.getImage(),
                                datasOppo.getDeskripsi(),
                                formatRupiah(Double.parseDouble(datasOppo.getHarga())),
                                datasOppo.getSeller());

                        listItemOppo.add(md);
                    }


                    OppoAdapter oppoAdapter = new OppoAdapter(listItemOppo, getApplicationContext());
                    oppoAdapter.notifyDataSetChanged();

                    rv_Oppo.setLayoutManager(new GridLayoutManager(MainActivity.this, 2, GridLayoutManager.HORIZONTAL, false));
                    rv_Oppo.setHasFixedSize(true);
                    rv_Oppo.setAdapter(oppoAdapter);

                     /*
                    Xiaomi
                     */
                    List<HomeProductPojo.Xiaomi> dataXiaomi = response.body().getList_xiaomi();

                    ArrayList<ProdukModel> listItemXiaomi = new ArrayList<>();
                    listItemXiaomi.clear();



                    for (HomeProductPojo.Xiaomi datasXiaomi : dataXiaomi){
                        ProdukModel md = new ProdukModel(datasXiaomi.getImage(),
                                datasXiaomi.getDeskripsi(),
                                formatRupiah(Double.parseDouble(datasXiaomi.getHarga())),
                                datasXiaomi.getSeller());

                        listItemXiaomi.add(md);
                    }


                    XiaomiAdapter xiaomiAdapter = new XiaomiAdapter(listItemXiaomi, getApplicationContext());
                    xiaomiAdapter.notifyDataSetChanged();

                    rv_xiaomi.setLayoutManager(new GridLayoutManager(MainActivity.this, 2, GridLayoutManager.HORIZONTAL, false));
                    rv_xiaomi.setHasFixedSize(true);
                    rv_xiaomi.setAdapter(xiaomiAdapter);
                }else if (success==2){
                    sessionManager.logout();

                }else {
                    Toast.makeText(getApplicationContext(), "Error Code " + response.code(), Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<HomeProductPojo> call, Throwable t) {

            }
        });
    }

    private String formatRupiah(Double number){
        Locale localeID = new Locale("in","ID");
        NumberFormat format = NumberFormat.getCurrencyInstance(localeID);
        return format.format(number);
    }

    private void imageSliders() {
        Call<ImageSliderPojo> imageSliderPojoCall = apiInterface.getImageSlider(api_key);
        imageSliderPojoCall.enqueue(new Callback<ImageSliderPojo>() {
            @Override
            public void onResponse(Call<ImageSliderPojo> call, Response<ImageSliderPojo> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Error Code" + response.code() , Toast.LENGTH_LONG).show();
                    return;
                }

                ArrayList<SliderImageModel> listItem = new ArrayList<>();
                listItem.clear();
                success = response.body().getSuccess();

                if (success==1){
                    List<ImageSliderPojo.ImageSlider> imageSliderList = response.body().getData();

                    for (ImageSliderPojo.ImageSlider datas : imageSliderList){
                        SliderImageModel sliderImageModel = new SliderImageModel(datas.getImage(), datas.getDeskripsi());
                        listItem.add(sliderImageModel);
                    }

                    SliderImageAdapter sliderImageAdapter = new SliderImageAdapter(listItem, getApplicationContext());
                    sliderImageAdapter.notifyDataSetChanged();
                    imageSlider.setSliderAdapter(sliderImageAdapter);
                    imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM);
                    imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                    imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                    imageSlider.setIndicatorSelectedColor(Color.WHITE);
                    imageSlider.setIndicatorUnselectedColor(Color.GRAY);
                    imageSlider.setScrollTimeInSec(4);
                    imageSlider.startAutoCycle();
                }else if (success == 2){
                    sessionManager.logout();
                }
            }

            @Override
            public void onFailure(Call<ImageSliderPojo> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error " + t , Toast.LENGTH_LONG).show();
            }
        });
    }
}