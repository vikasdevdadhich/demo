package com.webhostapp.demo;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class abc extends AppCompatActivity {
     private static final String URL_PRODUCTS = "http://192.168.1.107/offer.php";
public RecyclerView recyclerView;
    CardView cardView, cardView1, cardView2, cardView3, cardView4, cardView5, cardView6,cardView7,cardView8,cardView9,cardView10,cardView11,cardView12;
    int data = 1;
    List<Offer> offerList;
ProgressBar progressBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.abc_layout);
//send(getIntent().getExtras().getString("fname"),getIntent().getExtras().getString("lname"),getIntent().getExtras().getString("email"));
progressBar=findViewById(R.id.prog);
        offerList= new ArrayList<>();
          recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
         cardView12=findViewById(R.id.cardview11);
        cardView = findViewById(R.id.id_makeup);
        cardView1 = findViewById(R.id.id_clean);
        cardView2=findViewById(R.id.id_haircut);
        cardView3 = findViewById(R.id.id_plumber);
        cardView4 = findViewById(R.id.id_brush);
        cardView5 = findViewById(R.id.id_yoga);
        cardView6 = findViewById(R.id.id_painting);
        cardView7 = findViewById(R.id.id_yogaguru);
        cardView8 = findViewById(R.id.id_decoration);
        cardView9 = findViewById(R.id.id_ac_repair);
        cardView10 = findViewById(R.id.id_spa);
        cardView11 = findViewById(R.id.id_spa2);
        String title="MAin content";
        NotificationCompat.Builder builder= new NotificationCompat.Builder(abc.this)
                .setSmallIcon(R.drawable.ic_search)
                .setContentText("Hello There!")
                .setShowWhen(true)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(title));
        loadProducts();
progressBar.setVisibility(View.GONE);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(abc.this, booking_layout.class);
                intent.putExtra("first", 1);
                startActivity(intent);

            }
        });
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(abc.this, maid_list.class).putExtra("first", 2));
                finish();
            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(abc.this, booking_layout.class).putExtra("first", 3));

                finish();
            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(abc.this, booking_layout.class).putExtra("first", 3));

                finish();
            }
        });
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(abc.this, booking_layout.class).putExtra("first", 4));

                finish();
            }
        });

        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(abc.this, booking_layout.class).putExtra("first", 5));

                finish();
            }
        });
        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(abc.this, booking_layout.class).putExtra("first", 4));

                finish();
            }
        });
        cardView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(abc.this, booking_layout.class).putExtra("first", 5));

                finish();
            }
        });
        cardView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(abc.this, booking_layout.class).putExtra("first", 6));

                finish();
            }
        });
        cardView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(abc.this, booking_layout.class).putExtra("first", 7));

                finish();
            }
        });
        cardView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(abc.this, booking_layout.class).putExtra("first", 8));

                finish();
            }
        });
        cardView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(abc.this, booking_layout.class).putExtra("first", 8));

                finish();
            }
        });
        cardView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(abc.this,Order_booking.class));
            }
        });


        BottomNavigationView bottomnav = findViewById(R.id.bottom_nav);
        bottomnav.setOnNavigationItemSelectedListener(navlistner);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new home_fragment()).commit();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navlistner = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selecteditem = null;
            switch (menuItem.getItemId()) {
                case R.id.frag_profile:
                    selecteditem = new profile_fragment();
                    break;
                case R.id.frag_bookings:
                    selecteditem = new booking_fragment();
                    break;
                case R.id.frag_help:
                    selecteditem = new help_fragment();
                    break;
                case R.id.frag_home:
                    selecteditem = new home_fragment();

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, selecteditem).commit();
            return true;
        }
    };

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
                                    offerList.add(new Offer(
                                            product.getString("image")
                                    ));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            Offer_Adapter adapter = new Offer_Adapter(abc.this, offerList);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(abc.this, "" + error, Toast.LENGTH_SHORT).show();

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
        progressBar.setVisibility(View.GONE);
    }

    public void send(final String first_name, final String last_name, final String email )
    {
       String HttpUrl="http://192.168.1.105/or.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {

                        // Hiding the progress dialog after all task complete.

                        // Showing response message coming from server.
                        Toast.makeText(abc.this, ServerResponse, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                          volleyError.printStackTrace();
                         }
                }) {
            @Override
            protected Map<String, String> getParams() {

                 Map<String, String> params = new HashMap<String, String>();

                 params.put("first_name", first_name);
                params.put("last_name", last_name);
                params.put("email", email);

                return params;
            }

        };

        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(abc.this);

        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);

    }

        }