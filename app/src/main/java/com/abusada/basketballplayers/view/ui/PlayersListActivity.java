package com.abusada.basketballplayers.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.abusada.basketballplayers.R;
import com.abusada.basketballplayers.service.models.Data;
import com.abusada.basketballplayers.view.adapters.PaginationListener;
import com.abusada.basketballplayers.view.adapters.PlayersListAdapter;
import com.abusada.basketballplayers.viewmodels.PlayersListViewModel;

import java.util.ArrayList;

public class PlayersListActivity extends AppCompatActivity {

    private RecyclerView playersList;
    private ProgressBar progressBar;
    private ArrayList<Data> players = new ArrayList<>();

    private PlayersListAdapter mPlayersAdapter;
    private PlayersListViewModel mPlayersListViewModel;

    private boolean isLastPage = false;
    private boolean isLoading = false;
    private static int currentPage = 1;
    public static final int PAGE_SIZE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_list);

        playersList = findViewById(R.id.players_list);
        progressBar = findViewById(R.id.progress_bar);

        mPlayersListViewModel = ViewModelProviders.of(this).get(PlayersListViewModel.class);
        mPlayersListViewModel.isLoading().observe(this, isLoading -> {
            this.isLoading = isLoading;
        });

        initPlayersList();
        getPlayers();
    }

    void getPlayers(){
        mPlayersListViewModel.getPlayersData(currentPage, PAGE_SIZE).observe(this, response -> {
            players.clear();
            players.addAll(response.getData());
            mPlayersAdapter.notifyDataSetChanged();
            progressBar.setVisibility(View.GONE);
            currentPage = response.getMeta().getCurrentPage();
            if (response.getMeta().getCurrentPage() < response.getMeta().getTotalPages()) {
                mPlayersAdapter.addLoading();
            }
        });
    }

    void getNextPage(){
        mPlayersListViewModel.getPlayersData(currentPage+1, PAGE_SIZE).observe(this, response -> {
            players.clear();
            players.addAll(response.getData());
            mPlayersAdapter.notifyDataSetChanged();
            if (response.getMeta().getCurrentPage() < response.getMeta().getTotalPages()) {
                mPlayersAdapter.addLoading();
            } else {
                mPlayersAdapter.removeLoading();
                isLastPage = true;
            }
        });
    }

    void initPlayersList(){
        mPlayersAdapter = new PlayersListAdapter(this, players);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        playersList.setLayoutManager(layoutManager);
        playersList.setAdapter(mPlayersAdapter);
        playersList.addOnScrollListener(new PaginationListener(layoutManager){
            @Override
            protected void loadMoreItems() {
                getNextPage();
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
    }
}
