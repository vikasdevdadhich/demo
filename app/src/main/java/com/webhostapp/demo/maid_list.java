package com.webhostapp.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class maid_list extends AppCompatActivity {
    String religion = "abd", time = "abd", approach = "abd", gender = "abd";
    //this is the JSON Data URL
    //make sure you are using the correct ip else it will not work
    private static final String URL_PRODUCTS = "http://192.168.1.105/api.php";
    CardView cardView;
    //a list to store all the products
    List<Maid> productList;
    RecyclerView recyclerView;
    Button filter_btn;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maid_list);
        filter_btn = findViewById(R.id.filter_btn);
        filter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(maid_list.this, MaidActivity.class));
            }
        });
        //getting the recyclerview from xml
        recyclerView = findViewById(R.id.maid_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //initializing the productlist
        productList = new ArrayList<>();
        religion = getIntent().getExtras().getString("rel");
        time = getIntent().getExtras().getString("time");
        gender = getIntent().getExtras().getString("gender");
        approach = getIntent().getExtras().getString("approach");
        Toast.makeText(this, "Hey!" + religion + gender + time + approach, Toast.LENGTH_SHORT).show();
        //this method will fetch and parse json
        //to display it in recyclerview
         loadProducts();

    }


    private void loadProducts() {

         StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                //adding the product to product list
                                try {
                                    productList.add(new Maid(
                                            product.getInt("id"),
                                            product.getString("name"),
                                            product.getInt("age"),
                                            product.getString("area"),
                                            product.getString("gender"),
                                            product.getString("religion"),
                                            product.getString("image"),
                                            product.getDouble("rating")
                                    ));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            maid_adapter adapter = new maid_adapter(maid_list.this, productList);

                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                     },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
        }


}