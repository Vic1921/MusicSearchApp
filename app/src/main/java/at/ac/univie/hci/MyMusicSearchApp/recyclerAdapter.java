package at.ac.univie.hci.MyMusicSearchApp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {
    private ArrayList<Album> albumsList;

    public recyclerAdapter(ArrayList<Album> albumsList) {
        this.albumsList = albumsList;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView albumTitle;
        private TextView albumType;
        private TextView releaseDate;
        private ImageView imageAlbum;

        public MyViewHolder(final View view) {
            super(view);
            albumTitle = view.findViewById(R.id.title_text_view);
            albumType = view.findViewById(R.id.album_type_text_view);
            releaseDate = view.findViewById(R.id.release_year_text_view);
            imageAlbum = view.findViewById(R.id.band_image_view);
        }

    }

    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.band_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {
        String title = albumsList.get(position).getTitle();
        String releaseYear = albumsList.get(position).getReleaseDate();
        String type = albumsList.get(position).getType();
        String albumUrl = albumsList.get(position).getAlbumUrl();


        holder.albumTitle.setText(title);
        holder.albumType.setText(type);
        holder.releaseDate.setText(releaseYear);
        // Picasso for Image displaying
        Picasso.get().load(albumUrl).into(holder.imageAlbum);
    }

    @Override
    public int getItemCount() {
        return albumsList.size();
    }
}

// Class from YT Tutorial - https://www.youtube.com/watch?v=__OMnFR-wZU