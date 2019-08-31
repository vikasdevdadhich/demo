package com.webhostapp.demo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class Offer_Activity extends AppCompatActivity {
    Button showPopupBtn, closePopupBtn;
    Button cart_button, add;
    ImageView image1, image2, image3;
    PopupWindow popupWindow;
    LinearLayout linearLayout1;
    int i=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_);
        cart_button = findViewById(R.id.cart_button);
        String image = getIntent().getExtras().toString();
        add = findViewById(R.id.btn_add);
        image1 = findViewById(R.id.change_image);
        image2 = findViewById(R.id.change_image2);
        image3 = findViewById(R.id.change_image3);
        Glide.with(this).load(image).into(image1);

        Glide.with(this).load(image).into(image2);
        Glide.with(this).load(image).into(image3);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i/2==0){
                add.setText("Add");
                    Toast.makeText(Offer_Activity.this, "hello", Toast.LENGTH_SHORT).show();
                    i=i+1;


                }
            else
                {
                    add.setText("Remove");
                    Toast.makeText(Offer_Activity.this, "Hii", Toast.LENGTH_SHORT).show();
                    i=i-1;
                }
            }
        });
        cart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Offer_Activity.this, Order_booking.class);
                startActivity(intent);
                finish();
            }
        });

    }}