package com.example.user.sciencenewsapp.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.user.sciencenewsapp.R;
import com.example.user.sciencenewsapp.adapter.ScienceAdapter;
import com.example.user.sciencenewsapp.data.vo.Science;
import com.example.user.sciencenewsapp.view_model.ScienceViewModel;

public class MainActivity extends AppCompatActivity {
    SwipeRefreshLayout srl;
    Toolbar tb;
    RecyclerView rv;
    ScienceViewModel svm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        srl=findViewById(R.id.srl_am);
        tb=findViewById(R.id.tb_am);
        setSupportActionBar(tb);
        rv=findViewById(R.id.rv_am);
        rv.setLayoutManager(new LinearLayoutManager(this));

        svm=ViewModelProviders.of(this).get(ScienceViewModel.class);
        svm.getScienceLiveData().observe(this, new Observer<Science>() {
            @Override
            public void onChanged(@Nullable Science science) {
                if(science==null){
                    if(srl.isRefreshing()){
                        srl.setRefreshing(false);
                    }
                    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                }else {
                    if(science.getItems().size()<=0){
                        srl.setRefreshing(true);
                    }else {
                        if(srl.isRefreshing()){
                            srl.setRefreshing(false);
                        }
                        ScienceAdapter adapter=new ScienceAdapter(MainActivity.this,science.getItems());
                        rv.setAdapter(adapter);
                    }
                }
            }
        });



        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                svm.loadData();
            }
        });

    }
}
