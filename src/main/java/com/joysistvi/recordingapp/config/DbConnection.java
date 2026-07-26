package com.joysistvi.recordingapp.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// JDBC Standard Practice
public class DbConnection {

	// Database connection details
	private static final String URL = "jdbc:mysql://localhost:3306/recording_app_db";
	private static final String USERNAME = "root";
	private static final String PASSWORD = ""; // Remove password when pushing to GitHub

	// Create and return a database connection
	public Connection connect() throws SQLException {

		return DriverManager.getConnection(URL, USERNAME, PASSWORD);

	}
	
}
