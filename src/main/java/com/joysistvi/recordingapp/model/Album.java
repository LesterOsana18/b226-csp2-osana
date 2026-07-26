package com.joysistvi.recordingapp.model;

// Model Class
// Stores the information of an Album object using encapsulation
public class Album {
	
	// Private fields (Encapsulation)
	private int id;
	private String albumName;
	private int yearReleased;
	private int artistId;
	private String createdAt;
	
	// Default constructor
	public Album() {
		
	}
	
	// Constructor for existing records (includes ID)
	public Album(int id, String albumName, int yearReleased, int artistId, String createdAt) {
		this.id = id;
		this.albumName = albumName;
		this.yearReleased = yearReleased;
		this.artistId = artistId;
		this.createdAt = createdAt;
	}
	
	// Constructor for creating a new album
	public Album(String albumName, int yearReleased, int artistId, String createdAt) {
		this.albumName = albumName;
		this.yearReleased = yearReleased;
		this.artistId = artistId;
		this.createdAt = createdAt;
	}
	
	// Getter (Accessor) and Setter (Mutator) Methods

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public int getYearReleased() {
		return yearReleased;
	}

	public void setYearReleased(int yearReleased) {
		this.yearReleased = yearReleased;
	}

	public int getArtistId() {
		return artistId;
	}

	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
}
