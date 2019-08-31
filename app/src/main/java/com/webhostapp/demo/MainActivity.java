package com.webhostapp.demo;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements  ResponseListener{
    public String email;
    Button sendOTP;
    EditText phoneEditText;
    String phone,first_name,last_name;
    Session session;
    private CallbackManager callbackManager;
    private LoginButton loginButton;
     private CircleImageView circleImageView;
    private TextView txtName, txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getSupportActionBar()!=null)
            getSupportActionBar().hide();
        session = new Session(this);
        if (session.getLogin()){
            Intent intent = new Intent(MainActivity.this,abc.class);
            startActivity(intent);
            finish();
        }
        phoneEditText = findViewById(R.id.edit_phone);
        sendOTP = findViewById(R.id.btn_otp);
        loginButton = findViewById(R.id.login_button);
        txtName = findViewById(R.id.profile_name);
        txtEmail = findViewById(R.id.profile_email);
        circleImageView = findViewById(R.id.profile_pic);
        sendOTP.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        phoneEditText.setVisibility(View.VISIBLE);
        phone=phoneEditText.getText().toString();
        if(!checkPhone(phone) || phone.length()!=10){
            Toast.makeText(getApplicationContext(),"Please enter a valid number", Toast.LENGTH_SHORT).show();
            return;
        }
        Network network = new Network(MainActivity.this);
        network.setMessage("Requesting OTP");
        network.setResponseListener(MainActivity.this);
        network.setPage("send-otp.php");
        HashMap<String,String> data = new HashMap<>();
        data.put("phone",phone);
        network.execute(data);
            }
});
        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions(Arrays.asList("email", "public_profile"));
        checkLoginStatus();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(MainActivity.this, "Check your network connection", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void responseReceived(String url, String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            Toast.makeText(getApplicationContext(),jsonObject.getString("msg"),Toast.LENGTH_SHORT).show();
            if(jsonObject.getBoolean("success")){
                Intent intent = new Intent(MainActivity.this, OTPActivity.class);
                intent.putExtra("phone",phone);
                startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Intent intent = new Intent(MainActivity.this, abc.class).putExtra("fname",first_name).putExtra("lname",last_name).putExtra("email",email);
            startActivity(intent);
            finish();
        }

    }

    AccessTokenTracker tokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if (currentAccessToken == null) {
                txtName.setText("");
                txtEmail.setText("");
                circleImageView.setImageResource(0);
                Toast.makeText(MainActivity.this, "User Logged out", Toast.LENGTH_LONG).show();
            } else
                loadUserProfile(currentAccessToken);
        }
    };

    public void loadUserProfile(AccessToken newAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {

            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                      first_name = object.getString("first_name");
                      last_name = object.getString("last_name");
                    email = object.getString("email");
                    String id = object.getString("id");
                    String image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";

                    txtEmail.setText(email);
                    txtName.setText(first_name + " " + last_name);
                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.dontAnimate();
                    Toast.makeText(MainActivity.this, "Hello!" + first_name, Toast.LENGTH_SHORT).show();
                    Glide.with(MainActivity.this).load(image_url).into(circleImageView);
                     startActivity(new Intent(MainActivity.this,abc.class).putExtra("fname",first_name).putExtra("lname",last_name).putExtra("email",email));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();

    }

    private void checkLoginStatus() {
        if (AccessToken.getCurrentAccessToken() != null) {
            loadUserProfile(AccessToken.getCurrentAccessToken());
        }
    }


    public boolean checkPhone(String phone){
        for(char ch : phone.toCharArray()){
            if(!Character.isDigit(ch)){
                return false;
            }
        }
        return true;
    }
    }



