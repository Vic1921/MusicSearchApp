package at.ac.univie.hci.MyMusicSearchApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText searchEditText;
    private Button favoritesButton;
    private Button submitButton;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Find yo Band!");
        searchEditText = findViewById(R.id.search_edit_text);

        submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), SearchResultArtists.class);
            final String searchedArtist = searchEditText.getText().toString();
            intent.putExtra("search_result", searchedArtist);
            startActivity(intent);
        });

        favoritesButton = findViewById(R.id.favorite_artists_button);
        favoritesButton.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), FavoriteArtistsActivity.class);
            startActivity(intent);

        });

        // ToDo: Alert poping out by Adding to Fav
        // Toast.makeText(this, "Added to Favorites!", Toast.LENGTH_LONG).show();
        // 2:19:50

        Cache cache = new DiskBasedCache(getCacheDir(), 1024*1024);
        Network network = new BasicNetwork(new HurlStack());

        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();
    }

    @Override

    public void onClick(View view) {
    }
}