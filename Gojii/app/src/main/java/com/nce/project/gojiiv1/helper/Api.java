package com.nce.project.gojiiv1.helper;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {
    @FormUrlEncoded
@POST("auth/login")

    Call<TokenCustomerResponse> login
        (
                @Field( "email" ) String email,
                @Field("password") String password
        );

    @FormUrlEncoded
    @POST("auth/register")

    Call<RegisterResponse> register(
            @Field( "email" ) String email,
            @Field("password") String password,
            @Field("name") String name

    );





}

