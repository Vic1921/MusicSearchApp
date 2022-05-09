package at.ac.univie.hci.MyMusicSearchApp;

import java.util.ArrayList;

public class Favorites {
    private static Favorites single_instance = null;
    public ArrayList<String> artist;

    private Favorites() {
        this.artist = new ArrayList<>();
    }

    public static Favorites getInstance() {
        if(single_instance == null)
            single_instance = new Favorites();

        return single_instance;
    }

    public ArrayList<String> getArtist() {
        return artist;
    }

    public void setArtist(ArrayList<String> artist) {
        this.artist = artist;
    }
}
// https://www.geeksforgeeks.org/singleton-class-java/