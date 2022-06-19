package com.urangcoding.shop.pojo;

import java.util.ArrayList;
import java.util.List;

public class UserCreatePojo {

    private int success;
    private String message;
    private List<UserCreate> data = new ArrayList<>();

    public int getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<UserCreate> getData() {
        return data;
    }

    public class UserCreate{
        private String api_key, username, email, nama;

        public String getApi_key() {
            return api_key;
        }

        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }

        public String getNama() {
            return nama;
        }
    }
}
