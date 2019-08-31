package com.webhostapp.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class images_layout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.images_layout);
    }
        public void changeActivty()
        {
            Intent intent = new Intent(images_layout.this,Offer_Activity.class);
            startActivity(intent);
            finish();

    }
    }
