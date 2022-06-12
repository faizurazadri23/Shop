package com.urangcoding.shop.user;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.urangcoding.shop.R;
import com.urangcoding.shop.config.APIClient;
import com.urangcoding.shop.config.APIInterface;
import com.urangcoding.shop.pojo.KotaPOJO;
import com.urangcoding.shop.pojo.ProvinsiPOJO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    AutoCompleteTextView provinsi, kota;
    APIInterface apiInterface;

    int success = 0;
    int success_kota = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        provinsi = findViewById(R.id.provinsi);
        kota = findViewById(R.id.kota);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        list_provinsi();
        list_kota();
    }

    void list_provinsi() {
        Call<ProvinsiPOJO> provinsiPOJOCall = apiInterface.getProvinsi();
        provinsiPOJOCall.enqueue(new Callback<ProvinsiPOJO>() {
            @Override
            public void onResponse(Call<ProvinsiPOJO> call, Response<ProvinsiPOJO> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Error Code" + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                success = response.body().getSuccess();

                if (success == 1) {
                    List<ProvinsiPOJO.Provinsi> dataProvinsi = response.body().getData();

                    ArrayList<String> listItemProvinsi = new ArrayList<>();

                    for (ProvinsiPOJO.Provinsi data : dataProvinsi) {
                        listItemProvinsi.add(data.getProvinsi());
                    }





                    ArrayAdapter<String> adapterProvinsi = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, listItemProvinsi);
                    provinsi.setThreshold(1);
                    provinsi.setAdapter(adapterProvinsi);
                }
            }

            @Override
            public void onFailure(Call<ProvinsiPOJO> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error Code" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    void list_kota() {

        kota.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String default_provinsi = "";
                if (provinsi.getText().toString().equals("")) {
                    default_provinsi = "Sumatera Barat-32";
                } else {
                    default_provinsi = provinsi.getText().toString();
                }

                Call<KotaPOJO> kotaPOJOCall = apiInterface.getKota(default_provinsi);
                kotaPOJOCall.enqueue(new Callback<KotaPOJO>() {
                    @Override
                    public void onResponse(Call<KotaPOJO> call, Response<KotaPOJO> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Error Code" + response.code(), Toast.LENGTH_LONG).show();
                            return;
                        }

                        success_kota = response.body().getSuccess();

                        if (success_kota == 1) {
                            List<KotaPOJO.Kota> dataKota = response.body().getKotaList();

                            ArrayList<String> listItemKOta = new ArrayList<>();

                            for (KotaPOJO.Kota data : dataKota) {
                                listItemKOta.add(data.getKota());
                            }

                            Log.d("Data Ke  0 ", dataKota.get(0).getKota());

                            ArrayAdapter<String> adapterKota = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, listItemKOta);
                            kota.setThreshold(1);
                            kota.setAdapter(adapterKota);
                        }
                    }

                    @Override
                    public void onFailure(Call<KotaPOJO> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error Code" + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}