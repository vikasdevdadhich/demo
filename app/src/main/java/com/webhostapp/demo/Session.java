package com.webhostapp.demo;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    private Context context;
    private SharedPreferences sharedPreferences;
    Session(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("data",Context.MODE_PRIVATE);
    }
    public boolean getLogin(){
        return sharedPreferences.getBoolean("login",false);
    }

    public void setLogin(boolean login){
        sharedPreferences.edit().putBoolean("login",login).apply();
    }

    public String getPhone(){
        return sharedPreferences.getString("phone","");
    }

    public void setPhone(String phone){
        sharedPreferences.edit().putString("phone", phone).apply();
    }
}

