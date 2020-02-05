package com.abusada.basketballplayers.service.repository;

import com.abusada.basketballplayers.service.models.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BallDontLieApiService {

    @GET("players")
    Call<Response> getPlayers(@Query("page") Integer page,
                              @Query("per_page") Integer perPage);
}
