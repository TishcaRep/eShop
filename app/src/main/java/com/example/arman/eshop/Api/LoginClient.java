package com.example.arman.eshop.Api;

import com.example.arman.eshop.Api.Models.MLoginClient;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginClient {
    @FormUrlEncoded
    @POST("api/login/")
    Call<MLoginClient> Login(
            @Field("User") String User,
            @Field("Password") String Password
    );
}
