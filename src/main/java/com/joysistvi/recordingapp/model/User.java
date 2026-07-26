package com.joysistvi.recordingapp.model;

// Model Class
// Stores the information of a User object using encapsulation
public class User {
	
	// Private fields (Encapsulation)
	private int id;
	private String username;
	private String password;
	private String createdAt;
	
	// Default constructor
	public User() {
	}
	
    // Constructor for existing records (includes ID)
	public User(int id, String username, String password, String createdAt) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.createdAt = createdAt;
	}
	
    // Constructor for creating a new user
	public User(String username, String password, String createdAt) {
		this.username = username;
		this.password = password;
		this.createdAt = createdAt;
	}
	
	// Getter (Accessor) and Setter (Mutator) Methods

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
}
