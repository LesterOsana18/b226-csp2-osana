package com.joysistvi.recordingapp.dao;

import com.joysistvi.recordingapp.config.DbConnection;
import com.joysistvi.recordingapp.util.PasswordUtil;

// Main class used for testing DAO methods
public class Main {

	public static void main(String[] args) {

		// Create the database connection object
		DbConnection dbConnection = new DbConnection();

		// Create the SongDAO object
		SongDAO songDAO = new SongDAO(dbConnection);

		// Display all songs from the database
		songDAO.readSongs();

		// Sample CRUD operations for testing
		// songDAO.createSong("Binhi", "00:03:55", "OPM", 6);
		// songDAO.updateSong("Ngayoy Naririto", "00:03:50", "OPM", 21);
		
		// Test the hashPassword() method
		String password = "admin123";
		
		String hashedPassword = PasswordUtil.hashPassword(password);
		
		// Print out the results
		System.out.println("Original Password: " + password);
		System.out.println("Hashed Password: " + hashedPassword);
		
	}
}
