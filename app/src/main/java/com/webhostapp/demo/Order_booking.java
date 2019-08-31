package com.webhostapp.demo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

class Order_booking extends AppCompatActivity implements ResponseListener,ReResponseListener{
    public LinearLayout linearLayout1;
    public RadioButton radio1, radio2;
    public TextView textView, date1;
    public EditText address, phoneno;
    public String radio,date,str_address,phone;
    public Button btnClosePopup, showPopupBtn, btnCreatePopup, btnConfirm;
    private PopupWindow popupWindow;
    View mOverlayView;
    public RequestQueue requestQueue;
    String addressHolder, contactHolder, dateHolder;
    ProgressDialog progressDialog;
    String HttpUrl = "https://stalemated-pushes.000webhostapp.com/or.php";
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_booking);


        radio1 = findViewById(R.id.radio1);
        radio2 = findViewById(R.id.radio2);
        address = findViewById(R.id.address);

        phoneno = findViewById(R.id.contact);
        date1 = findViewById(R.id.datePickerValue);
        textView = findViewById(R.id.sorry_text);
        phone=phoneno.getText().toString();
        str_address=address.getText().toString();
        date=date1.getText().toString();
        btnCreatePopup = findViewById(R.id.book_button);
        requestQueue = Volley.newRequestQueue(Order_booking.this);

        progressDialog = new ProgressDialog(Order_booking.this);
        radio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setVisibility(View.VISIBLE);

            }
        });
        radio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setVisibility(View.INVISIBLE);
            }
        });

        btnCreatePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                      initiatePopupWindow();

            }
        });
        this.showDatePickerDialog();

    }

    private PopupWindow pwindo;

    private void initiatePopupWindow() {
        try {
// We need to get the instance of the LayoutInflater
            LayoutInflater inflater = (LayoutInflater) Order_booking.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = (View) inflater.inflate(R.layout.text_view,
                    (ViewGroup) findViewById(R.id.popup_element));
            pwindo = new PopupWindow(layout, 1200, 600, true);
            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);

            btnClosePopup = layout.findViewById(R.id.btn_close);
            btnClosePopup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pwindo.dismiss();
                }

            });
            btnConfirm = layout.findViewById(R.id.btn_confirm);
            btnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Showing progress dialog at user registration time.
                    progressDialog.setMessage("Please Wait, We are Inserting Your Data on Server");
                    progressDialog.show();

                    // Calling method to get value from EditText.
                    GetValueFromEditText();

                    // Creating string request with post method.
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String ServerResponse) {

                                    // Hiding the progress dialog after all task complete.
                                    progressDialog.dismiss();

                                    // Showing response message coming from server.
                                    Toast.makeText(Order_booking.this, ServerResponse, Toast.LENGTH_LONG).show();
                                    pwindo.dismiss();
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {

                                    // Hiding the progress dialog after all task complete.
                                    progressDialog.dismiss();
                                    volleyError.printStackTrace();
                                    // Showing error message if something goes wrong.
                                    //   Toast.makeText(testing.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() {

                            // Creating Map String Params.
                            Map<String, String> params = new HashMap<String, String>();

                            // Adding All values to Params.
                            params.put("address", addressHolder);
                            params.put("contact", contactHolder);
                            params.put("date", dateHolder);
                            params.put("type",type);
                            return params;
                        }

                    };

                    // Creating RequestQueue.
                    RequestQueue requestQueue = Volley.newRequestQueue(Order_booking.this);

                    // Adding the StringRequest object into requestQueue.
                    requestQueue.add(stringRequest);
message();
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }


        // Create and show a DatePickerDialog when click button.
    }

    private void showDatePickerDialog() {
        // Get open DatePickerDialog button.
        Button datePickerDialogButton = findViewById(R.id.calander_button);
        showPopupBtn = findViewById(R.id.book_button);
        linearLayout1 = findViewById(R.id.linearlayout1);

        datePickerDialogButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                // Create a new OnDateSetListener instance. This listener will be invoked when user click ok button in DatePickerDialog.
                DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        StringBuffer strBuf = new StringBuffer();
                        strBuf.append(year);
                        strBuf.append("-");
                        strBuf.append(month + 1);
                        strBuf.append("-");
                        strBuf.append(dayOfMonth);

                        TextView datePickerValueTextView = findViewById(R.id.datePickerValue);
                        datePickerValueTextView.setText(strBuf.toString());
                    }
                };

                // Get current year, month and day.
                Calendar now = Calendar.getInstance();
                int year = now.get(java.util.Calendar.YEAR);
                int month = now.get(java.util.Calendar.MONTH);
                int day = now.get(java.util.Calendar.DAY_OF_MONTH);

                // Create the new DatePickerDialog instance.
                DatePickerDialog datePickerDialog = new DatePickerDialog(Order_booking.this, onDateSetListener, year, month, day);

                // Set dialog icon and title.
                datePickerDialog.setTitle("Please select date.");
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                // Popup the dialog.
                datePickerDialog.show();
            }
        });

    }

    // Creating method to get value from EditText.
    public void GetValueFromEditText() {

        addressHolder = address.getText().toString();
        contactHolder = phoneno.getText().toString();
        dateHolder = date1.getText().toString();
//        type = getIntent().getExtras().getString("Type");
String type="maid";
    }
    public void message()
    {
         Network network = new Network(Order_booking.this);
        network.setMessage("Booking Progress.");
        network.ReResponseListener(this);
        network.setPage("send-conf.php");
        HashMap<String,String> data = new HashMap<>();
        data.put("phone",contactHolder);
        network.execute(data);

        }

@Override
public void ReResponseReceived(String url, String data) {
        try {
        JSONObject jsonObject = new JSONObject(data);
        Toast.makeText(getApplicationContext(),jsonObject.getString("msg"),Toast.LENGTH_SHORT).show();
        if(jsonObject.getBoolean("success")){
            Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();
        pwindo.dismiss();
        }
        } catch (Exception e) {
        e.printStackTrace();
        }
        }


    @Override
    public void responseReceived(String url, String data) {

    }
}

