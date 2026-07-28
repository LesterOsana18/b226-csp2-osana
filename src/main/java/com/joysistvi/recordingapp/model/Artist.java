package com.joysistvi.recordingapp.model;

// Model Class
// Stores the information of an Artist object using encapsulation
public class Artist {
	
	// Private fields (Encapsulation)
	private int id;
	private String artistName;
	
	// Default constructor
	public Artist() {
	}
	
	// Constructor for existing records (includes ID)
	public Artist(int id, String artistName) {
		this.id = id;
		this.artistName = artistName;
	}
	
	// Constructor for creating a new artist
	public Artist(String artistName) {
		this.artistName = artistName;
	}
	
	// Getter (Accessor) and Setter (Mutator) Methods
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
}
