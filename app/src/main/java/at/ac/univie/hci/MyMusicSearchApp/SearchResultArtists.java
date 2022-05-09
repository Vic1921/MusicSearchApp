package at.ac.univie.hci.MyMusicSearchApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchResultArtists extends AppCompatActivity {
    private RequestQueue requestQueue;
    private TextView artistNameTextView;
    private RecyclerView recyclerView;
    private ArrayList<Album> albumArrayList = new ArrayList<>();

    final String[] artistId = new String[1];
    final String[] artistName = new String[1];

    @Override
    protected void onResume() {

        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result_artists);

        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
        Network network = new BasicNetwork(new HurlStack());

        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();


        String selectedArtist = getIntent().getStringExtra("search_result");
        String URL = "https://musicbrainz.org/ws/2/artist/?query=" + selectedArtist + "&fmt=json";
        // GET the ID Request
        JsonRequest<JSONObject> artistIdRequest = new JsonObjectRequest(
                Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("API_RESPONSE", response.toString());
                        try {
                            JSONArray artists = response.getJSONArray("artists");
                            artistId[0] = artists.getJSONObject(0).getString("id");
                            artistName[0] = artists.getJSONObject(0).getString("name");
                            //artistId[0] = ((JSONObject)((JSONArray)response.get("artists")).get(0)).get("id").toString();
                            //artistName[0] =  ((JSONObject)((JSONArray)response.get("artists")).get(0)).get("name").toString();
                            executeGetAlbums();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Execute request here?
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SearchResultArtists.this,
                                "Please try again!",
                                Toast.LENGTH_LONG).show();
                        Log.e("API_ERROR", error.getMessage());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("User-Agent", "MusicSearchApp/1.0.0 (a12022144@unet.univie.ac.at)");
                return headers;
            }
        };
        requestQueue.add(artistIdRequest);
        //getCoverArtRequest(albumArrayList);


        artistNameTextView = findViewById(R.id.band_name_text_view);
        artistNameTextView.setText(artistId[0]);
        recyclerView = findViewById(R.id.albums_meta_view);
        setAdapter();
    }

    public void getCoverArtRequest(ArrayList<Album> albums) {
        // GET the CoverArt
        if (albums == null)
            return;
        for (Album album : albums) {
            String URL = "https://coverartarchive.org/release-group/" + album.getId();
            JsonRequest<JSONObject> photoAlbumRequest = new JsonObjectRequest(
                    Request.Method.GET, URL, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i("API_RESPONSE", response.toString());
                            try {
                                JSONArray images = response.getJSONArray("images");
                                album.setAlbumUrl(images.getJSONObject(0).getString("image"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(SearchResultArtists.this,
                                    "Please try again!",
                                    Toast.LENGTH_LONG).show();
                            Log.e("API_ERROR", error.getMessage());
                        }
                    }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("User-Agent", "MusicSearchApp/version / 1.0.0 (victor.binzar61@gmail.com)");
                    return headers;
                }
            };
            requestQueue.add(photoAlbumRequest);
        }
    }

    private void executeGetAlbums() {
        // GET the Album Metadata Request
        String newURL = "https://musicbrainz.org/ws/2/artist/" + artistId[0] + "?inc=release-groups&fmt=json";
        JsonRequest<JSONObject> albumsRequest = new JsonObjectRequest(
                Request.Method.GET, newURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("API_RESPONSE", response.toString());
                        processAlbumResponse(response);
                        getCoverArtRequest(albumArrayList);
                        setAdapter();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SearchResultArtists.this,
                                "Please try again!",
                                Toast.LENGTH_LONG).show();
                        Log.e("API_ERROR", "if(error.getMessage().isEmpty()");
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("User-Agent", "MusicSearchApp/version / 1.0.0 (victor.binzar61@gmail.com)");
                return headers;
            }
        };
        requestQueue.add(albumsRequest);
    }

    public void processAlbumResponse(JSONObject apiResponse) {
        try {
            JSONArray albums = apiResponse.getJSONArray("release-groups");

            for (int i = 0; i < albums.length(); i++) {
                JSONObject album = albums.getJSONObject(i);
                albumArrayList.add(new Album(album.getString("title"), album.getString("first-release-date"),
                        album.getString("primary-type"), album.getString("id")));
            }

        } catch (JSONException e) {
            Toast.makeText(SearchResultArtists.this,
                    "Could not parse API response!",
                    Toast.LENGTH_LONG).show();
            Log.e("PARSE_ERROR", e.getMessage());
        }
    }

    // YT Tutorial - https://www.youtube.com/watch?v=__OMnFR-wZU
    private void setAdapter() {
        recyclerAdapter adapter = new recyclerAdapter(albumArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
    //

    Favorites favorites = Favorites.getInstance();
    public void addFavorites(View view) {
        Toast.makeText(this, "Added to Favorites!", Toast.LENGTH_LONG).show();
        favorites.artist.add(artistName[0]);
    }

}