package com.example.getsetgo.data.network;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MyApi {

    @FormUrlEncoded
    @POST("userlogin")
    Call<ResponseBody> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("createuser")
    Call<ResponseBody> userSignup(
            @Field(("name")) String name,
            @Field(("email")) String email,
            @Field(("password")) String password
    );

}
