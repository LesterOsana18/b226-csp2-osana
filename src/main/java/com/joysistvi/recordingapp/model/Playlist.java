package com.joysistvi.recordingapp.model;

// Model Class
// Stores the information of a Playlist object using encapsulation
public class Playlist {
	
	// Private fields (Encapsulation)
	private int id;
	private String playlistName;
	private int userId;
	
	// Default constructor
	public Playlist() {
	}
	
	// Constructor for existing methods (includes ID)
	public Playlist(int id, String playlistName, int userId) {
		this.id = id;
		this.playlistName = playlistName;
		this.userId = userId;
	}
	
	// Constructor for creating a new playlist
	public Playlist(String playlistName, String dateCreated, int userId, String createdAt) {
		this.playlistName = playlistName;
		this.userId = userId;
	}
	
	// Getter (Accessor) and Setter (Mutator) Methods

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlaylistName() {
		return playlistName;
	}

	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
