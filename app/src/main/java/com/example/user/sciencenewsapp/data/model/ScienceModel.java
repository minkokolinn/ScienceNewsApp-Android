package com.example.user.sciencenewsapp.data.model;

import com.example.user.sciencenewsapp.data.vo.Science;
import com.example.user.sciencenewsapp.delegate.MyDelegate;
import com.example.user.sciencenewsapp.util.AppConstant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScienceModel extends BaseModel{
    public ScienceModel(){
        super();
    }

    public void getScience(final MyDelegate delegate){
        api.getScience(AppConstant.LINK_URL).enqueue(new Callback<Science>() {
            @Override
            public void onResponse(Call<Science> call, Response<Science> response) {
                delegate.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Science> call, Throwable t) {
                delegate.onError(t.getMessage());
            }
        });
    }
}
