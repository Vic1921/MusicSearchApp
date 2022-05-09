package at.ac.univie.hci.MyMusicSearchApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class FavoriteArtistsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_artists);

        TextView artistName = findViewById(R.id.artist_list_txt);
        Favorites favs = Favorites.getInstance();
        for(int i = 0; i < favs.artist.size(); i++) {
            artistName.append(favs.artist.get(i)+"\n");
        }
    }
}