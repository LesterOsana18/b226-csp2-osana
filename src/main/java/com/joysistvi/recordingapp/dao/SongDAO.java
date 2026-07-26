package com.joysistvi.recordingapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.joysistvi.recordingapp.config.DbConnection;

public class SongDAO {

	// Declare the database connection object
	private final DbConnection dbConnection;

	// Constructor
	public SongDAO(DbConnection dbConnection) {
		this.dbConnection = dbConnection;
	}

	// ==========================================
	// CRUD Operations
	// ==========================================

	// Retrieve and display all active songs from the database
	public void readSongs() {

		String query = "SELECT * FROM songs WHERE is_archived = 0";

		try (
				// Connect to the database
				Connection connection = dbConnection.connect();

				// Create the SQL statement
				Statement statement = connection.createStatement();

				// Execute the query and store the results
				ResultSet resultSet = statement.executeQuery(query);
		) {

			// Display the table header
			System.out.println("+----+------------------------------+----------+----------------+----------+");
			System.out.printf("| %-2s | %-28s | %-8s | %-14s | %-8s |%n",
					"ID", "Title", "Length", "Genre", "Album ID");
			System.out.println("+----+------------------------------+----------+----------------+----------+");

			// Display each song retrieved from the database
			while (resultSet.next()) {

				int id = resultSet.getInt("id");
				String title = resultSet.getString("title");
				String length = resultSet.getString("length");
				String genre = resultSet.getString("genre");
				int albumId = resultSet.getInt("album_id");

				System.out.printf("| %-2d | %-28s | %-8s | %-14s | %-8d |%n",
						id, title, length, genre, albumId);
			}

			System.out.println("+----+------------------------------+----------+----------------+----------+");

		} catch (SQLException e) {

			System.out.println("Read Songs: " + e.getMessage());

		}
	}

	// Insert a new song into the database
	public void createSong(String title, String length, String genre, int albumId) {

		String query = "INSERT INTO songs (title, length, genre, album_id) VALUES (?, ?, ?, ?)";

		try (
				Connection connection = dbConnection.connect();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
		) {

			// Assign values to the SQL parameters
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, length);
			preparedStatement.setString(3, genre);
			preparedStatement.setInt(4, albumId);

			// Execute the INSERT statement
			preparedStatement.executeUpdate();

			System.out.println("\nSong \"" + title + "\" added successfully!\n");

			// Refresh the displayed list of songs
			readSongs();

		} catch (SQLException e) {

			System.out.println("Create Song: " + e.getMessage());

		}
	}

	// Update an existing song in the database
	public void updateSong(String title, String length, String genre, int id) {

		String query = "UPDATE songs SET title = ?, length = ?, genre = ? WHERE id = ?";

		try (
				Connection connection = dbConnection.connect();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
		) {

			// Assign values to the SQL parameters
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, length);
			preparedStatement.setString(3, genre);
			preparedStatement.setInt(4, id);

			// Execute the UPDATE statement
			preparedStatement.executeUpdate();

			System.out.println("\nSong updated successfully!\n");

			// Refresh the displayed list of songs
			readSongs();

		} catch (SQLException e) {

			System.out.println("Update Song: " + e.getMessage());

		}
	}

	// Delete a song from the database
	public void deleteSong() {

		// TODO: Implement delete functionality

	}

	// Archive a song instead of permanently deleting it
	public void archiveSong() {

		// TODO: Implement archive functionality

	}

	// Restore an archived song
	public void restoreSong() {

		// TODO: Implement restore functionality

	}

	// Search for a song in the database
	public void searchSong() {

		// TODO: Implement search functionality

	}

}
