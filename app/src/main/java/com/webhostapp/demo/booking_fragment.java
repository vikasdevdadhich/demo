package com.webhostapp.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class
booking_fragment extends Fragment {
    String religion="abd", time="abd", approach="abd", gender="abd";
    //this is the JSON Data URL
    //make sure you are using the correct ip else it will not work
    private static final String URL_PRODUCTS = "http://192.168.42.100/ordered_item.php";

    //a list to store all the products
    List<Order_List> productList;
    List<Order_List> list;
    ProgressBar progressBar;
    //the recyclerview
    RecyclerView recyclerView;
    Button filter_btn;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_bookings,container,false);
        recyclerView = v.findViewById(R.id.book_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

       /* //initializing the productlist
        productList = new ArrayList<>();
        religion = getActivity().getIntent().getExtras().getString("rel");
        time = getActivity().getIntent().getExtras().getString("time");
        gender =getActivity().getIntent().getExtras().getString("gender");
        approach = getActivity().getIntent().getExtras().getString("approach");
         //this method will fetch and parse json
        //to display it in recyclerview
*/
        loadProducts();

    /*  }
      else
      {
               List<Maid> temp = new ArrayList();
              for(Maid d: productList){
                  //or use .equal(text) with you want equal match
                  //use .toLowerCase() for better matches
                  if(d.getReligion().contains(religion)&&d.getGender().contains(gender)){
                      temp.add(d);
                  }
              }
          maid_adapter adapter = new maid_adapter(maid_list.this, temp);

              recyclerView.setAdapter(adapter);
          //update recyclerview

          }
      }

*/

        return v;
    }
  private void loadProducts() {

    /*
     * Creating a String Request
     * The request type is GET defined by first parameter
     * The URL is defined in the second parameter
     * Then we have a Response Listener and a Error Listener
     * In response listener we will get the JSON response as a String
     * */
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
                            productList.add(new Order_List(
                                    product.getInt("id"),
                                    product.getString("address"),
                                     product.getString("contact"),
                                    product.getString("date"),
                                    product.getString("type")

                            ));
                        }

                        //creating adapter object and setting it to recyclerview
                        Order_List_Adapter adapter = new Order_List_Adapter(getContext(), productList);
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
    Volley.newRequestQueue(getContext()).add(stringRequest);
}
}



