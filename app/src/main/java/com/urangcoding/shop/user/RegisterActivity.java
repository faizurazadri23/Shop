package com.urangcoding.shop.user;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.urangcoding.shop.R;
import com.urangcoding.shop.config.APIClient;
import com.urangcoding.shop.config.APIInterface;
import com.urangcoding.shop.pojo.KotaPOJO;
import com.urangcoding.shop.pojo.ProvinsiPOJO;
import com.urangcoding.shop.pojo.UserCreatePojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    AutoCompleteTextView provinsi, kota;
    APIInterface apiInterface;

    int success = 0;
    int success_kota = 0;
    private ProgressDialog loading;
    private Button btnRegister;
    private RadioGroup rgJekel;
    private RadioButton selectedRadioButton;
    private String message = "";
    private EditText edtUsername,edtEmail, edtPassword, edtNama,edtRepassword, edtNoHp, edtAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        provinsi = findViewById(R.id.provinsi);
        kota = findViewById(R.id.kota);
        btnRegister = findViewById(R.id.btnRegister);
        rgJekel = findViewById(R.id.rgJekel);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtNama = findViewById(R.id.edtNama);
        edtRepassword = findViewById(R.id.edtRePassword);
        edtNoHp = findViewById(R.id.edtPhone);
        edtEmail = findViewById(R.id.edtEmail);
        edtAlamat = findViewById(R.id.edtAlamat);

        apiInterface = APIClient.getClient().create(APIInterface.class);
        loading = new ProgressDialog(RegisterActivity.this);

        list_provinsi();
        list_kota();
        simpan();
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

    void simpan() {

        btnRegister.setOnClickListener(view -> {

            String password = edtPassword.getText().toString().trim();
            String repassword = edtRepassword.getText().toString().trim();

            if (!password.equals(repassword)){
                Toast.makeText(getApplicationContext(), "Password dan Re Password Tidak Sama" , Toast.LENGTH_LONG).show();
            }else {
                loading.setMessage("Please Wait...");
                loading.setCancelable(false);
                loading.show();

                selectedRadioButton = findViewById(rgJekel.getCheckedRadioButtonId());
                Map<String, String> field = new HashMap<>();
                field.put("username", edtUsername.getText().toString());
                field.put("email", edtEmail.getText().toString());
                field.put("nama", edtNama.getText().toString());
                field.put("password", edtPassword.getText().toString());
                field.put("gender", selectedRadioButton.getText().toString());
                field.put("province_id", provinsi.getText().toString());
                field.put("city_id", kota.getText().toString());
                field.put("address", edtAlamat.getText().toString());
                field.put("phone", edtNoHp.getText().toString());

                Call<UserCreatePojo> userCreatePojoCall = apiInterface.createUser(field);
                userCreatePojoCall.enqueue(new Callback<UserCreatePojo>() {
                    @Override
                    public void onResponse(Call<UserCreatePojo> call, Response<UserCreatePojo> response) {
                        loading.dismiss();

                        if (!response.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Error " + response.code() , Toast.LENGTH_LONG).show();
                            return;
                        }

                        success = response.body().getSuccess();
                        message = response.body().getMessage();

                        if (success==1){
                            String hasil = "";
                            List<UserCreatePojo.UserCreate> userCreateList = response.body().getData();

                            for (UserCreatePojo.UserCreate data : userCreateList){
                                hasil += data.getApi_key() + "\n";
                                hasil += data.getUsername()+ "\n";
                                hasil += data.getEmail()+ "\n";
                                hasil += data.getNama()+ "\n";
                            }

                            Toast.makeText(getApplicationContext(), hasil , Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(getApplicationContext(), message , Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserCreatePojo> call, Throwable t) {

                    }
                });
            }


        });
    }
}