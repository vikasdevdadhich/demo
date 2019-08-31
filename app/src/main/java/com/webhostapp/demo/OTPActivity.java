package com.webhostapp.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONObject;

import java.util.HashMap;

public class OTPActivity extends AppCompatActivity implements  ResponseListener{

    EditText pin1,pin2,pin3,pin4;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar()!=null)
            getSupportActionBar().hide();
        setContentView(R.layout.otp_activity);
        phone = getIntent().getStringExtra("phone");
        pin1 = findViewById(R.id.pin1);
        pin2 = findViewById(R.id.pin2);
        pin3 = findViewById(R.id.pin3);
        pin4 = findViewById(R.id.pin4);
        pin1.addTextChangedListener(new GenericTextWatcher(pin1));
        pin2.addTextChangedListener(new GenericTextWatcher(pin2));
        pin3.addTextChangedListener(new GenericTextWatcher(pin3));
        pin4.addTextChangedListener(new GenericTextWatcher(pin4));
    }

    @Override
    public void responseReceived(String url, String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            Toast.makeText(getApplicationContext(),jsonObject.getString("msg"),Toast.LENGTH_SHORT).show();
            if(jsonObject.getBoolean("success")){
        //        FirebaseApp.initializeApp(this);
          //      FirebaseMessaging.getInstance().subscribeToTopic("alerts");
                Session session = new Session(this);
                session.setLogin(true);
                session.setPhone(phone);
                Intent intent = new Intent(OTPActivity.this, abc.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }else{
                pin1.setText("");
                pin2.setText("");
                pin3.setText("");
                pin4.setText("");
                pin1.requestFocus();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class GenericTextWatcher implements TextWatcher {
        private View view;
        private GenericTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String text = editable.toString();
            switch(view.getId()) {

                case R.id.pin1:
                    if(text.length()==1)
                        pin2.requestFocus();
                    break;
                case R.id.pin2:
                    if(text.length()==1)
                        pin3.requestFocus();
                    else
                        pin1.requestFocus();
                    break;
                case R.id.pin3:
                    if(text.length()==1)
                        pin4.requestFocus();
                    else
                        pin2.requestFocus();
                    break;
                case R.id.pin4:
                    if(text.length()==0)
                        pin3.requestFocus();
                    else{
                        String pin = pin1.getText().toString() + pin2.getText().toString() + pin3.getText().toString() + pin4.getText().toString();
                        if(pin.length()==4) {
                            Network network = new Network(OTPActivity.this);
                            network.setResponseListener(OTPActivity.this);
                            network.setMessage("Verifying OTP");
                            network.setPage("verify-otp.php");
                            HashMap<String,String> data = new HashMap<>();
                            data.put("phone",phone);
                            data.put("pin",pin);
                            network.execute(data);
                        }
                    }
                    break;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        }
    }
}

