package com.urangcoding.shop.user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.urangcoding.shop.MainActivity;
import com.urangcoding.shop.R;
import com.urangcoding.shop.config.APIClient;
import com.urangcoding.shop.config.APIInterface;
import com.urangcoding.shop.pojo.UserCreatePojo;
import com.urangcoding.shop.util.SessionManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText emailLogin, passwordLogin;
    int success = 0;
    String message = "";
    APIInterface apiInterface;
    ProgressDialog loading;
    private SessionManager sessionManager;
    private TextView txt_signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailLogin = findViewById(R.id.email_Login);
        passwordLogin = findViewById(R.id.passwordLogin);
        btnLogin = findViewById(R.id.btnLogin);
        txt_signUp = findViewById(R.id.txt_signUp);

        sessionManager = new SessionManager(this);

        apiInterface = APIClient.getClient().create(APIInterface.class);
        loading = new ProgressDialog(LoginActivity.this);

        proses_login();

        txt_signUp.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }

    void proses_login() {
        btnLogin.setOnClickListener(view -> {
            String username = emailLogin.getText().toString().trim();
            String passwordUser = passwordLogin.getText().toString().trim();

            if (username.isEmpty()) {
                emailLogin.setError("Silahkan Di isikan");
            } else if (passwordUser.isEmpty()) {
                passwordLogin.setError("Silahkan Di isikan");
            } else {
                loading.setMessage("Please Wait...");
                loading.setCancelable(false);
                loading.show();

                Map<String, String> fields = new HashMap<>();
                fields.put("username", username);
                fields.put("password", passwordUser);

                Call<UserCreatePojo> userCreatePojoCall = apiInterface.userLogin(fields);
                userCreatePojoCall.enqueue(new Callback<UserCreatePojo>() {
                    @Override
                    public void onResponse(Call<UserCreatePojo> call, Response<UserCreatePojo> response) {
                        loading.dismiss();

                        if (!response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                            return;
                        }

                        success = response.body().getSuccess();
                        message = response.body().getMessage();

                        if (success == 1) {

                            List<UserCreatePojo.UserCreate> userCreateList = response.body().getData();

                            for (UserCreatePojo.UserCreate data : userCreateList) {
                                sessionManager.createSession(data.getApi_key(), data.getUsername(), data.getEmail(), data.getNama(), "");
                            }

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();


                        } else {
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
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