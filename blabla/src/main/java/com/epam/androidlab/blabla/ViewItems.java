package com.epam.androidlab.blabla;

public class ViewItems {
    private final String band;
    private final String album;
    private final int albumCover;

    public ViewItems(String band, String album, int albumCover) {
        this.band = band;
        this.album = album;
        this.albumCover = albumCover;
    }

    public String getBand() {
        return band;
    }

    public String getAlbum() {
        return album;
    }

    public int getAlbumCover() {
        return albumCover;
    }

}
