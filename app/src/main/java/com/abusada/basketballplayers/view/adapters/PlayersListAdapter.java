package com.abusada.basketballplayers.view.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abusada.basketballplayers.R;
import com.abusada.basketballplayers.service.models.Data;
import com.abusada.basketballplayers.view.ui.PlayerDetailsActivity;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.Locale;

public class PlayersListAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_NORMAL = 1;
    private boolean isLoaderVisible = false;
    Context context;
    private List<Data> mListItems;

    public PlayersListAdapter(Context context, List<Data> items) {
        this.context = context;
        this.mListItems = items;
    }

    @NonNull @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.player_list_item, parent, false));
            case VIEW_TYPE_LOADING:
                return new ProgressHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_list_item, parent, false));
            default:
                return null;
        }
    }
    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }
    @Override
    public int getItemViewType(int position) {
        if (isLoaderVisible) {
            return position == mListItems.size() - 1 ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_NORMAL;
        }
    }
    @Override
    public int getItemCount() {
        return mListItems == null ? 0 : mListItems.size();
    }

    public void addLoading() {
        isLoaderVisible = true;
        mListItems.add(new Data());
        notifyItemInserted(mListItems.size() - 1);
    }
    public void removeLoading() {
        isLoaderVisible = false;
        int position = mListItems.size() - 1;
        Data item = getItem(position);
        if (item != null) {
            mListItems.remove(position);
            notifyItemRemoved(position);
        }
    }

    Data getItem(int position) {
        return mListItems.get(position);
    }

    public class ViewHolder extends BaseViewHolder {
        ImageView playerImage;
        TextView playerName;
        TextView playerTeam;
        CardView playerCard;
        View itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            playerImage = itemView.findViewById(R.id.player_image);
            playerName = itemView.findViewById(R.id.player_name);
            playerTeam = itemView.findViewById(R.id.player_team);
            playerCard = itemView.findViewById(R.id.player_card);
            this.itemView = itemView;
        }
        protected void clear() {
        }
        public void onBind(int position) {
            super.onBind(position);
            Data player = mListItems.get(position);
            Picasso.get().load(String.format(Locale.ENGLISH,"https://cdn2.thecatapi.com/images/%d.jpg", player.getId())).placeholder(context.getResources().getDrawable(R.mipmap.ic_launcher)).into(playerImage);
            playerName.setText(player.getFirstName() + " " + player.getLastName());
            playerTeam.setText(player.getTeam().getFullName());

            int color = R.color.lightBlue;
            switch (player.getPosition()){
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
            playerCard.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(color)));
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, PlayerDetailsActivity.class);
                intent.putExtra("player", player);
                context.startActivity(intent);
            });
        }
    }
    public class ProgressHolder extends BaseViewHolder {
        ProgressHolder(View itemView) {
            super(itemView);
        }
        @Override
        protected void clear() {
        }
    }
}