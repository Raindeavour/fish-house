package com.example.mysecondapp.interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ListAPI {
    @GET("/v2/ranks_v3/ranks/14")
    Call<ResponseBody> getList();
}
