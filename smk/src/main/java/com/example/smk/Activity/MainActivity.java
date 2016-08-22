package com.example.smk.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.smk.R;

public class MainActivity extends AppCompatActivity {
    final String LOG_TAG = "myLogs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vhod);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    //Оброботчик гостя
    public void onClickGuest(View v){
        Intent browserIntent = new Intent(MainActivity.this, Spisok.class);
        startActivity(browserIntent);
    }
    //Оброботчик регистрации
    public void onClickRegister(View v){
        Intent browserIntent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(browserIntent);
    }
    //Оброботчик логин
    public void onClickLogin(View v){
        Intent browserIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(browserIntent);
    }
    public void onClickExit(View v){
        this.finish();
    }
}


