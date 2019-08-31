package com.webhostapp.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MaidActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {
    String[] country = {"1 Hour", "2 Hour", "5 Hour", "8 Hour", "10 Hour", "12 Hour", "24 Hour"};
    String[] slot = {"Morning", "Evening", "Noon", "Other"};
    RadioGroup radioGroup, radioGroup1, radioGroup2, radioGroup3;
    RadioButton radioButton, radioButton1, radioButton2, radiButton3;
    Button button;
    public String religion, gender, price_approach, time_shift, no_hour;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maid_activity);
        setTitle("Searching Criteria");
        final maid_list md= new maid_list();
        radioGroup = findViewById(R.id.religion_grp);
        radioGroup1 = findViewById(R.id.shift_grp);
        radioGroup2 = findViewById(R.id.gender_grp);
        radioGroup3 = findViewById(R.id.salary_grp);

        button = findViewById(R.id.search_btn);
        Spinner hour_spin = findViewById(R.id.time_spin);
        hour_spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hour_spin.setAdapter(aa);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = radioGroup.getCheckedRadioButtonId();
                int id1 = radioGroup1.getCheckedRadioButtonId();
                int id2 = radioGroup2.getCheckedRadioButtonId();
                int id3 = radioGroup3.getCheckedRadioButtonId();
                radioButton = findViewById(id);
                radioButton2 = findViewById(id2);
                radioButton1 = findViewById(id1);
                radiButton3 = findViewById(id3);
                religion = radioButton.getText().toString();
                time_shift = radioButton1.getText().toString();
                gender = radioButton2.getText().toString();
                price_approach = radiButton3.getText().toString();

                startActivity(new Intent(MaidActivity.this, maid_list.class).putExtra("rel", religion).putExtra("time", time_shift).putExtra("gender", gender).putExtra("approach", price_approach));

            }

        });

    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(), country[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}
