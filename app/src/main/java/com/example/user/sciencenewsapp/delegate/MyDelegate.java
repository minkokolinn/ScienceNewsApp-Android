package com.example.user.sciencenewsapp.delegate;

import com.example.user.sciencenewsapp.data.vo.Science;

public interface MyDelegate {
    void onSuccess(Science science);
    void onError(String str);
}
