package com.example.audioplayer;

public class Music {
    private String title;
    private String artist;
    private int filePath;

    public Music(String title, String artist, int filePath) {
        this.title = title;
        this.artist = artist;
        this.filePath = filePath;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getFilePath() {
        return filePath;
    }
}
