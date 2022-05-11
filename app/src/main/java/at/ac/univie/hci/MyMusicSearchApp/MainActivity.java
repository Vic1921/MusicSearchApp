package at.ac.univie.hci.MyMusicSearchApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText searchEditText;
    private Button favoritesButton;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Find yo Band!");

        searchEditText = findViewById(R.id.search_edit_text);

        submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(view2 -> {
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


        // ToDo: Create event handlers instead of having everything in onCreate

    }


    @Override
    public void onClick(View view) {

    }

}