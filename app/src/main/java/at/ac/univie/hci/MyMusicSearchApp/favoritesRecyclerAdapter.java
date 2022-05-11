package at.ac.univie.hci.MyMusicSearchApp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class favoritesRecyclerAdapter extends RecyclerView.Adapter<at.ac.univie.hci.MyMusicSearchApp.favoritesRecyclerAdapter.MyViewHolder> {
    Favorites favorites = Favorites.getInstance();



    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView artistTitle;


        public MyViewHolder(final View view) {
            super(view);
            artistTitle = view.findViewById(R.id.artist_title);
        }

    }


    @NonNull
    @Override
    public favoritesRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_item, parent, false);
        return new favoritesRecyclerAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String title = favorites.artist.get(position).toString();
        holder.artistTitle.setText(title);
    }

    @Override
    public int getItemCount() {
        return favorites.artist.size();
    }


// Class from YT Tutorial - https://www.youtube.com/watch?v=__OMnFR-wZU
}
