package com.abusada.basketballplayers.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.abusada.basketballplayers.R;
import com.abusada.basketballplayers.viewmodels.PlayerDetailsViewModel;
import com.squareup.picasso.Picasso;

import java.util.Locale;

public class PlayerDetailsActivity extends AppCompatActivity {

    ConstraintLayout background;
    CircleImageView playerImage;
    TextView playerName;
    TextView playerTeam;
    TextView playerCity;

    private PlayerDetailsViewModel mPlayerDetailsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_details);

        getSupportActionBar().setTitle("Player Details");

        background = findViewById(R.id.background);
        playerImage = findViewById(R.id.player_image);
        playerName = findViewById(R.id.player_name);
        playerTeam = findViewById(R.id.player_team);
        playerCity = findViewById(R.id.player_city);

        if(!getIntent().hasExtra("player"))
            return;

        mPlayerDetailsViewModel = ViewModelProviders.of(this).get(PlayerDetailsViewModel.class);
        mPlayerDetailsViewModel.init(getIntent().getParcelableExtra("player"));
        mPlayerDetailsViewModel.getPlayerData().observe(this, data -> {
            Picasso.get().load(String.format(Locale.ENGLISH,"https://cdn2.thecatapi.com/images/%d.jpg", data.getId())).placeholder(getResources().getDrawable(R.mipmap.ic_launcher)).into(playerImage);
            playerName.setText("Player: " + data.getFirstName() + " " + data.getLastName());
            playerTeam.setText("Team: " + data.getTeam().getFullName());
            playerCity.setText("City: " + data.getTeam().getCity());
            int color = R.color.lightBlue;
            switch (data.getPosition()){
                case "G-F":
                    color = R.color.lightGreen;
                    break;
                case "C":
                    color = R.color.lightOrange;
                    break;
                case "F":
                    color = R.color.lightPurple;
                    break;
                case "G":
                    color = R.color.lightRed;
                    break;
            }
            background.setBackgroundColor(getResources().getColor(color));
        });
    }
}
