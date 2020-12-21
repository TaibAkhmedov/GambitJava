package com.smthweird.gambitjava;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GambitApi {

    @GET("category/39?page=1")
    Call<List<FoodItem>> getFoodItemList();
}
