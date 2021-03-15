package com.zubisofts.kickcounterex.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zubisofts.kickcounterex.db.DatabaseHelper;
import com.zubisofts.kickcounterex.model.Kick;

import java.util.ArrayList;
import java.util.Date;

public class KickHistoryViewModel extends ViewModel {

    public MutableLiveData<ArrayList<Kick>> history = new MutableLiveData<>();

    public LiveData<ArrayList<Kick>> getHistory(Context context){
        fetchHistory(context);
        return history;
    }

    public void fetchHistory(Context context){
        DatabaseHelper databaseHelper=new DatabaseHelper(context);
        ArrayList<Kick> kickData = databaseHelper.getKickData();
        history.setValue(kickData);
    }

}