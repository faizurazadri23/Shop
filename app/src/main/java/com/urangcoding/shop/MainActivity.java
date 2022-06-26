package com.urangcoding.shop;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.urangcoding.shop.adapter.SliderImageAdapter;
import com.urangcoding.shop.config.APIClient;
import com.urangcoding.shop.config.APIInterface;
import com.urangcoding.shop.model.SliderImageModel;
import com.urangcoding.shop.pojo.ImageSliderPojo;
import com.urangcoding.shop.util.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private SessionManager sessionManager;

    private SliderView imageSlider;
    private String api_key, nama, email, userName, message;

    private int success = 0;
    private APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageSlider = findViewById(R.id.imageSlider);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        apiInterface = APIClient.getClient().create(APIInterface.class);

        //Mengambil session

        HashMap<String, String> user = sessionManager.getUserDetail();
        api_key = user.get(sessionManager.API_KEY);

        imageSliders();

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