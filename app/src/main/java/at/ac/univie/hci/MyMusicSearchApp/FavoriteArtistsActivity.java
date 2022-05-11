package at.ac.univie.hci.MyMusicSearchApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

public class FavoriteArtistsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_artists);

        recyclerView = findViewById(R.id.favorites_view);

        setAdapter();
    }



    // YT Tutorial - https://www.youtube.com/watch?v=__OMnFR-wZU
    private void setAdapter() {
        Favorites favorites = Favorites.getInstance();
        favoritesRecyclerAdapter adapter = new favoritesRecyclerAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
    //
}