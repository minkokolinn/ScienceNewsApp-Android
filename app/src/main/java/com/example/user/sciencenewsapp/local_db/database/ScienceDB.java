package com.example.user.sciencenewsapp.local_db.database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.user.sciencenewsapp.local_db.dao.ScienceDao;
import com.example.user.sciencenewsapp.local_db.entity.EnclosureTable;
import com.example.user.sciencenewsapp.local_db.entity.ItemsTable;


@Database(entities = {ItemsTable.class,EnclosureTable.class},version = 1,exportSchema = false)
public abstract class ScienceDB extends RoomDatabase {
    private static ScienceDB Ins;

    public abstract ScienceDao getDao();

    public static ScienceDB getIns(Context ctxt){
        if(Ins==null){
            Ins=Room.databaseBuilder(ctxt,ScienceDB.class,"news.db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return Ins;
    }
}
