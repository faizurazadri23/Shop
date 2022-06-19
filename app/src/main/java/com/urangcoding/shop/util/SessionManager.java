package com.urangcoding.shop.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.urangcoding.shop.MainActivity;
import com.urangcoding.shop.user.LoginActivity;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    public static final String PREF_NAME = "LOGIN";
    public static final String LOGIN = "IS_LOGIN";
    public static final String API_KEY = "API_KEY";
    public static final String USERNAME = "USERNAME";
    public static final String EMAIL = "EMAIL";
    public static final String NAMA = "NAMA";
    public static final String IMG = "IMG";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String api_key, String username,
                              String email, String nama, String img) {
        editor.putBoolean(LOGIN, true);
        editor.putString(API_KEY, api_key);
        editor.putString(USERNAME, username);
        editor.putString(EMAIL, email);
        editor.putString(NAMA, nama);
        editor.putString(IMG, img);
        editor.apply();
    }

    public boolean isLogin() {
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void checkLogin() {
        if (!this.isLogin()) {
            Intent i = new Intent(context, LoginActivity.class);
            context.startActivity(i);
            ((MainActivity) context).finish();
        }
    }

    public HashMap<String, String> getUserDetail() {
        HashMap<String, String> user = new HashMap<>();
        user.put(API_KEY, sharedPreferences.getString(API_KEY, null));
        user.put(USERNAME, sharedPreferences.getString(USERNAME, null));
        user.put(EMAIL, sharedPreferences.getString(EMAIL, null));
        user.put(NAMA, sharedPreferences.getString(NAMA, null));
        user.put(IMG, sharedPreferences.getString(IMG, null));

        return user;
    }

    public void logout() {
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, LoginActivity.class);
        context.startActivity(i);
        ((LoginActivity) context).finish();
    }
}
