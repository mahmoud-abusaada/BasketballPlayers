package com.abusada.basketballplayers.viewmodels;

import com.abusada.basketballplayers.service.models.Data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PlayerDetailsViewModel extends ViewModel {

    private MutableLiveData<Data> mPlayerData = new MutableLiveData<>();

    public void init(Data playerData){
        mPlayerData.setValue(playerData);
    }

    public LiveData<Data> getPlayerData(){
        return mPlayerData;
    }
}
