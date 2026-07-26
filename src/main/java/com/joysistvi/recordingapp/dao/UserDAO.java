package com.joysistvi.recordingapp.dao;

import com.joysistvi.recordingapp.config.DbConnection;

// Class that handles CRUD operations for the users
public class UserDAO {

	// Dependency Injection using Constructor Injection
	@SuppressWarnings("unused")
	private final DbConnection dbConnection;

	// Constructor
	public UserDAO(DbConnection dbConnection) {
		this.dbConnection = dbConnection;
	}

	// User Authentication Methods

	// Login
	public void login() {

	}

	// Register
	public void register() {

	}

}
