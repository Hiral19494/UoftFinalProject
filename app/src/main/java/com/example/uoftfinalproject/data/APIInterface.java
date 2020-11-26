package com.example.uoftfinalproject.data;



import com.example.uoftfinalproject.model.UserInfo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIInterface {

    @FormUrlEncoded
    @POST("/api/login")
    Call<UserInfo> userLogin(@Field("email") String email, @Field("password") String password);
}
