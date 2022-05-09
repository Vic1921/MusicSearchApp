package at.ac.univie.hci.MyMusicSearchApp;

public class Album {
    private String title;
    private String releaseDate;
    private String type;
    private String albumUrl;
    private String Id;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAlbumUrl() {
        return albumUrl;
    }

    public void setAlbumUrl(String albumUrl) {
        this.albumUrl = albumUrl;
    }

    public Album(String title, String releaseDate, String type, String Id) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.type = type;
        this.Id = Id;
    }
}
