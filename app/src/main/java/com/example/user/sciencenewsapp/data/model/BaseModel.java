package com.example.user.sciencenewsapp.data.model;

import com.example.user.sciencenewsapp.networks.ApiService;
import com.example.user.sciencenewsapp.util.AppConstant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseModel {
    protected ApiService api;

    public BaseModel(){
        Retrofit retrofit=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(AppConstant.BASE_URL)
                .build();
        api=retrofit.create(ApiService.class);
    }
}
