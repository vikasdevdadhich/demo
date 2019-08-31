package com.webhostapp.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Book_Servant extends AppCompatActivity {
TextView name_text,age_text,religion_text,area_text,gender_text,desc_text;
ImageView imageView;
Button book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book__servant);
        name_text=findViewById(R.id.name_text);
        age_text=findViewById(R.id.Age_text);
        religion_text=findViewById(R.id.Religion_text);
        area_text=findViewById(R.id.Area_text);
        gender_text=findViewById(R.id.Gender_text);
        book=findViewById(R.id.book_servant);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Book_Servant.this,booking_layout.class));
                finish();
            }
        });



    }
}
