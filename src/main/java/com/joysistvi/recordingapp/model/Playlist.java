package com.joysistvi.recordingapp.model;

// Model Class
// Stores the information of a Playlist object using encapsulation
public class Playlist {
	
	// Private fields (Encapsulation)
	private int id;
	private String playlistName;
	private String dateCreated;
	private int userId;
	private String createdAt;
	
	// Default constructor
	public Playlist() {
	}
	
	// Constructor for existing methods (includes ID)
	public Playlist(int id, String playlistName, String dateCreated, int userId, String createdAt) {
		this.id = id;
		this.playlistName = playlistName;
		this.dateCreated = dateCreated;
		this.userId = userId;
		this.createdAt = createdAt;
	}
	
	// Constructor for creating a new playlist
	public Playlist(String playlistName, String dateCreated, int userId, String createdAt) {
		this.playlistName = playlistName;
		this.dateCreated = dateCreated;
		this.userId = userId;
		this.createdAt = createdAt;
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

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
}
