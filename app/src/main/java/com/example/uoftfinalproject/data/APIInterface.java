package com.example.uoftfinalproject.data;


import com.example.uoftfinalproject.model.Product;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("devStepin2IT/apitest/productData")
    Call<ArrayList<Product>> productDetails();
}
