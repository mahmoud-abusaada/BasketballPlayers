package com.abusada.basketballplayers.service.repository;

import android.util.Log;

import com.abusada.basketballplayers.service.models.Response;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;

public class PlayersRepository {

    private BallDontLieApiService apiService;
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    MutableLiveData<Response> data = new MutableLiveData<>();

    public PlayersRepository(){
        apiService = RetrofitRequest.getRetrofitInstance().create(BallDontLieApiService.class);
    }

    public LiveData<Response> getPlayersData(Integer page, Integer perPage){
        isLoading.setValue(true);
        apiService.getPlayers(page, perPage).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if(data.getValue() == null){
                    data.setValue(response.body());
                }else {
                    Response temp = data.getValue();
                    temp.getData().addAll(response.body().getData());
                    temp.setMeta(response.body().getMeta());
                    data.setValue(temp);
                }
                //data.setValue(response.body());
                isLoading.setValue(false);
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                isLoading.setValue(false);
            }
        });

        return data;
    }

    public LiveData<Boolean> isLoading(){
        return isLoading;
    }
}