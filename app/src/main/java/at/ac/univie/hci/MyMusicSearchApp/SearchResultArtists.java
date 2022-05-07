package at.ac.univie.hci.MyMusicSearchApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SearchResultArtists extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result_artists);

        String searchedText = getIntent().getStringExtra("search_result");

    }
}