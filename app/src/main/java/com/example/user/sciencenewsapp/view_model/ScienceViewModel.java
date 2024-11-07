package com.example.user.sciencenewsapp.view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.user.sciencenewsapp.data.model.ScienceModel;
import com.example.user.sciencenewsapp.data.vo.Enclosure;
import com.example.user.sciencenewsapp.data.vo.Items;
import com.example.user.sciencenewsapp.data.vo.MyItem;
import com.example.user.sciencenewsapp.data.vo.Science;
import com.example.user.sciencenewsapp.delegate.MyDelegate;
import com.example.user.sciencenewsapp.local_db.database.ScienceDB;
import com.example.user.sciencenewsapp.local_db.entity.EnclosureTable;
import com.example.user.sciencenewsapp.local_db.entity.ItemsTable;

import java.util.ArrayList;
import java.util.List;

public class ScienceViewModel extends AndroidViewModel {
    MutableLiveData<Science> liveDate=new MutableLiveData<>();
    ScienceDB db;

    public ScienceViewModel(@NonNull Application application) {
        super(application);
        db=ScienceDB.getIns(application);
        loadData();
    }


    public void loadData(){
        ScienceModel sm=new ScienceModel();
        sm.getScience(new MyDelegate() {
            @Override
            public void onSuccess(Science science) {
                db.getDao().deleteEnclosure();
                db.getDao().deleteItem();
                ArrayList<Items> items= (ArrayList<Items>) science.getItems();
                for (Items i:items){
                    Enclosure en=i.getEnclosure();

                    EnclosureTable enclosureTable=new EnclosureTable(en.getLink());

                    long id=db.getDao().insertEnclosure(enclosureTable);

                    ItemsTable itemsTable=new ItemsTable(i.getTitle(),i.getDescription(),id);
                    db.getDao().insertItem(itemsTable);
                }
            }

            @Override
            public void onError(String str) {
                Log.d("hello",str);
                liveDate.setValue(null);
            }
        });
    }

    public LiveData<List<MyItem>> getScienceLiveData(){
        return db.getDao().getMovie();
    }
}
