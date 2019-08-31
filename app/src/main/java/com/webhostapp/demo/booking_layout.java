package com.webhostapp.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class booking_layout extends AppCompatActivity {
Button book_btn;
String type;
String position= null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_layout);
         ImageView imageView= findViewById(R.id.image1);
        book_btn=findViewById(R.id.book_now_button);


        TextView head=findViewById(R.id.heading);
        TextView desc=findViewById(R.id.description);
        int message = getIntent().getExtras().getInt("first");
             switch (message)
        { case 1:
             imageView.setImageResource(R.mipmap.makeover);
             head.setText("Makeup");
            type="Makeup and Beauty";
             desc.setText(R.string.information);
             break;
            case 2:
                imageView.setImageResource(R.mipmap.cleaning);
                head.setText("Maid Or Servant");
                type="Maid Or servant";

                desc.setText(R.string.information);
                break;
            case 3:
                imageView.setImageResource(R.mipmap.haircut);
                head.setText("Hair Cut");
                type="Hair Cut";

                desc.setText(R.string.information);

                break;
            case 4:
                imageView.setImageResource(R.mipmap.electrician);
                type="Plumber or electrician etc.";

                head.setText("Plumber and etc.");
                desc.setText(R.string.information);

                break;
            case 5:
                imageView.setImageResource(R.mipmap.paint);
                head.setText("Painters");
                type="Painters";
                desc.setText(R.string.information);

                break;
            case 6:
                imageView.setImageResource(R.mipmap.yogaguru);
                head.setText("Yoga");
                type="Yoga Or Instructor";
                desc.setText(R.string.information);

                break;
        }


        book_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(booking_layout.this,Order_booking.class).putExtra("Type",type));
            }
        });
    }

}

