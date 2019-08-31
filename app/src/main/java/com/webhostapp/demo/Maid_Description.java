package com.webhostapp.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class Maid_Description extends AppCompatActivity {
    int age;
    String image, area, name, gender, religion;
RatingBar ratebar;
    double rating;
    ImageView maidimage;
    Button book;
    TextView nameView, ageView, areaView, genderView, religionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maid__description);
        maidimage = findViewById(R.id.image_id);
        nameView = findViewById(R.id.name_id);
        book = findViewById(R.id.hire_btn);
        ageView = findViewById(R.id.age_id);
        religionView = findViewById(R.id.religion_id);
        genderView = findViewById(R.id.gender_id);
        ratebar=findViewById(R.id.rating1);
        areaView = findViewById(R.id.area_id);
        name = getIntent().getExtras().getString("name");
        age = getIntent().getExtras().getInt("age");
        Toast.makeText(this, "" + name, Toast.LENGTH_SHORT).show();
        area = getIntent().getExtras().getString("area");
        image = getIntent().getExtras().getString("image");
        gender = getIntent().getExtras().getString("gender");
        religion = getIntent().getExtras().getString("religion");
        rating=getIntent().getExtras().getDouble("rate");
        Glide.with(this).load(image).into(maidimage);
        nameView.setAllCaps(true);
        nameView.setText("" + name);
        ageView.setText("Age : " + age);
        areaView.setText("Area : " + area);
        genderView.setText("Gender : " + gender);
        religionView.setText("Religion : " + religion);
        ratebar.setRating((float) rating);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Maid_Description.this, Order_booking.class));
                finish();
            }
        });

    }
}
