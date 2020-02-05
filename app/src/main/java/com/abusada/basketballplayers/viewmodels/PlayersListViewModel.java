package com.abusada.basketballplayers.viewmodels;

import com.abusada.basketballplayers.service.models.Response;
import com.abusada.basketballplayers.service.repository.PlayersRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class PlayersListViewModel extends ViewModel {

    private PlayersRepository mPlayerRepository;
    private LiveData<Response> mPlayersList;
    private LiveData<Boolean> isLoading;

    public LiveData<Response> getPlayersData(int page, int perPage){
        if(mPlayerRepository == null)
            mPlayerRepository = new PlayersRepository();

        mPlayersList = mPlayerRepository.getPlayersData(page, perPage);
        isLoading = mPlayerRepository.isLoading();

        return mPlayersList;
    }

    public LiveData<Boolean> isLoading(){
        if(mPlayerRepository == null)
            mPlayerRepository = new PlayersRepository();

        isLoading = mPlayerRepository.isLoading();

        return isLoading;
    }
}
