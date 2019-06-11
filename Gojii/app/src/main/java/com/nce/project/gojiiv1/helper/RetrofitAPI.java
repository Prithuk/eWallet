package com.nce.project.gojiiv1.helper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAPI {
    public static final String BASE_URL = "http://10.0.10.116:3000/v1/";
    private static Retrofit retrofit;
    private static RetrofitAPI mInstance;

    private RetrofitAPI(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static  synchronized RetrofitAPI getInstance(){
        if (mInstance == null){
            mInstance = new RetrofitAPI();
        }
        return mInstance;
    }

    public Api getApi (){
        return retrofit.create(Api.class);
    }

}
