package com.example.mysecondapp.interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Raindeavor丶W
 * Created on 2019/5/7
 */
public interface CStrategyHeaderAPI {
    @GET("/v2/columns")
    Call<ResponseBody> getCStrategyHeader();
}
