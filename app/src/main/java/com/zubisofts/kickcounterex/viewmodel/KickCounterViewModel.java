package com.zubisofts.kickcounterex.viewmodel;

import android.content.Context;
import android.text.format.DateUtils;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.zubisofts.kickcounterex.db.DatabaseHelper;
import com.zubisofts.kickcounterex.model.Kick;

import java.util.Date;

public class KickCounterViewModel extends ViewModel {

    private MutableLiveData<Boolean> mActive = new MutableLiveData<>(false);
    private MutableLiveData<Integer> mKickCount = new MutableLiveData<>(0);
    private MutableLiveData<Date> startTime = new MutableLiveData<>();
    private MutableLiveData<Date> lastTime = new MutableLiveData<>();

    public LiveData<Integer> getKickCount() {
        return mKickCount;
    }

    public void increaseKickCount() {
        mKickCount.setValue(mKickCount.getValue() + 1);
    }

    public void resetKickCount() {
        mKickCount.setValue(0);
    }

    public void setActive(boolean active) {
        mActive.setValue(active);
    }

    public LiveData<Boolean> getActiveSession() {
        return mActive;
    }

    public void setFirstTime() {
        startTime.setValue(new Date());
    }

    public void setLastTime() {
        lastTime.setValue(new Date());
    }

    public LiveData<Date> getFirstTime() {
        return startTime;
    }

    public LiveData<Date> getLastTime() {
        return lastTime;
    }

    public void addKick(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        databaseHelper.addKickData(
                new Kick(
                        mKickCount.getValue(),
                        startTime.getValue().getTime(),
                        lastTime.getValue().getTime(),
                        lastTime.getValue().getTime() - startTime.getValue().getTime()

                )
        );
    }

}