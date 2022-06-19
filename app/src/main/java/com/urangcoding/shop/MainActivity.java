package com.urangcoding.shop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.urangcoding.shop.util.SessionManager;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private SessionManager sessionManager;
    private TextView tvSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSession = findViewById(R.id.tvSession);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        //Mengambil session

        HashMap<String, String> user = sessionManager.getUserDetail();
        String api_key = user.get(sessionManager.API_KEY);
        String userName = user.get(sessionManager.USERNAME);
        String email = user.get(sessionManager.EMAIL);
        String nama = user.get(sessionManager.NAMA);

        tvSession.setText("Session " + api_key + " " + userName + " " + email + " " + nama);
    }
}