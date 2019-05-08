package com.example.mysecondapp.interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HomePageAPI {
    @GET("/v2/channels/108/items_v2")
//    Call<ResponseBody> getHomePage(@Query("ad") String ad,
//                               @Query("gender") String gender, @Query("generation") String generation, @Query("limit") String limit, @Query("offset") String offset);
    Call<ResponseBody> getHomePage();
}
