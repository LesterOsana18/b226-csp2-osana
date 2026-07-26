package com.joysistvi.recordingapp.model;

// Model Class
// Stores the information of a Song object using encapsulation
public class Song {

	// Private fields (Encapsulation)
    private int id;
    private String title;
    private String length;
    private String genre;
    private int albumId;
    private int isArchived;

    // Default constructor
    public Song() {
    }

    // Constructor for existing records (includes ID)
    public Song(int id, String title, String length, String genre, int albumId) {
        this.id = id;
        this.title = title;
        this.length = length;
        this.genre = genre;
        this.albumId = albumId;
    }

    // Constructor for creating a new song
    public Song(String title, String length, String genre, int albumId) {
        this.title = title;
        this.length = length;
        this.genre = genre;
        this.albumId = albumId;
    }

    // Getter and Setter Methods

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getIsArchived() {
        return isArchived;
    }

    public void setIsArchived(int isArchived) {
        this.isArchived = isArchived;
    }
}
