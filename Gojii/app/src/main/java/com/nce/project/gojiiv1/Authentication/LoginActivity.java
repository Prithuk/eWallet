package com.nce.project.gojiiv1.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.nce.project.gojiiv1.R;
import com.nce.project.gojiiv1.helper.RetrofitAPI;
import com.nce.project.gojiiv1.helper.TokenCustomerResponse;
import com.nce.project.gojiiv1.helper.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String EMAIL = "email";
    private static CallbackManager callbackManager;
    private static AccessTokenTracker accessTokenTracker;
    private static ProfileTracker profileTracker;
    private static AccessToken accessToken;
    private static LoginButton loginButton;
    private static EditText emailEditText, passwordEditText;
    private static Button loginBtn;
    private static String loginResponse ="";
    // private ProgressDialog progressDialog= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        loginBtn = findViewById(R.id.login);


        //facebook  login part
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.fb_loginBtn);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException error) {

            }


        });

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                // Set the access Token using
                // currentAccessToken when it's loaded or set.
            }
        };
        // If the access Token is available already assign it.
        accessToken = AccessToken.getCurrentAccessToken();

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(
                    Profile oldProfile,
                    Profile currentProfile) {
                // App code
            }
        };


        //normal login

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loign2wallet();
            }
        });


    }

    private void loign2wallet() {
        // showProgressDialog();
        final String emailValue = emailEditText.getText().toString();
        final String passwordValue = passwordEditText.getText().toString();

        Call<TokenCustomerResponse> call = RetrofitAPI
                .getInstance()
                .getApi()
                .login(emailValue, passwordValue);

        call.enqueue(new Callback<TokenCustomerResponse>() {
            @Override
            public void onResponse(Call<TokenCustomerResponse> call, Response<TokenCustomerResponse> response) {
                //Toast.makeText(LoginActivity.this, "Logged in success", Toast.LENGTH_SHORT).show();

                if (response.code()==200) {
                    if (response.body() != null) {
                       // Log.i("onSuccess", response.body().toString());
                        try {
                            JSONObject reader = new JSONObject(loginResponse);
                            JSONObject token= reader.getJSONObject("Token");
                            JSONObject customer = reader.getJSONObject("Customer");
                          String  tokenType= token.getString("tokenType");
                          String id= customer.getString("id");
                          Log.d("tokenType",tokenType);
                            Toast.makeText(LoginActivity.this, "id:"+id, Toast.LENGTH_SHORT).show();
                            Toast.makeText(LoginActivity.this, "tokenType:"+tokenType, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                       /* String jsonresponse= response.errorBody().toString();
                        Toast.makeText(LoginActivity.this, jsonresponse, Toast.LENGTH_SHORT).show();*/
                    }

                }

            }

            @Override
            public void onFailure(Call<TokenCustomerResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "message:" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void fetchJson(String jsonresponse) {
        try {
            JSONObject object= new JSONObject(jsonresponse);
            String tokenType = object.getString("tokenType");
            Log.d("tokenType", tokenType);
            Toast.makeText(this, "tokenType: "+ tokenType, Toast.LENGTH_SHORT).show();


        }catch (JSONException e){
            e.printStackTrace();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }
    /* Show progress dialog. */
/*    private void showProgressDialog()
    {
        // Set progress dialog display message.
        progressDialog.setMessage("Please Wait");

        // The progress dialog can not be cancelled.
        progressDialog.setCancelable(false);

        // Show it.
        progressDialog.show();
    }

    *//* Hide progress dialog. *//*
    private void hideProgressDialog()
    {
        // Close it.
        progressDialog.hide();
    }*/
}
