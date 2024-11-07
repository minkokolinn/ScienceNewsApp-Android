package com.example.user.sciencenewsapp.networks;

import com.example.user.sciencenewsapp.data.vo.Science;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("v1/api.json")
    Call<Science> getScience(@Query("rss_url")String link);
}
