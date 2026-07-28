package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.config.DbConnection;
import com.joysistvi.recordingapp.model.Artist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

// Repository Implementation
// Handles all database operations related to Artist objects
public class ArtistRepositoryImpl implements ArtistRepository {

    // Dependency Injection (Constructor Injection)
    private final DbConnection dbConnection;

    public ArtistRepositoryImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // ==========================================================
    // Read Operations
    // ==========================================================

    // Retrieve all artists from the database
    @Override
    public List<Artist> getAllArtists() {

        List<Artist> artists = new ArrayList<>();

        String query = "SELECT * FROM artists";

        try (
                Connection connection = dbConnection.connect();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {

            while (resultSet.next()) {

                artists.add(new Artist(
                        resultSet.getInt("id"),
                        resultSet.getString("artist_name")
                ));

            }

        } catch (SQLException e) {

            System.out.println("Error retrieving artists: " + e.getMessage());

        }

        return artists;

    }

    // Search artists by name
    @Override
    public List<Artist> searchArtists(String keyword) {

        List<Artist> artists = new ArrayList<>();

        String query = "SELECT * FROM artists WHERE artist_name LIKE ?";

        try (
                Connection connection = dbConnection.connect();
                PreparedStatement preparedStatement = 
                        connection.prepareStatement(query)
        ) {

                preparedStatement.setString(1, "%" + keyword + "%");

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {

                    artists.add(new Artist(
                            resultSet.getInt("id"),
                            resultSet.getString("artist_name")
                    ));
                }

        } catch (SQLException e) {

            System.out.println("Error searching artists: " + e.getMessage());

        }

        return artists;

    }

    // ==========================================================
    // Create Operation
    // ==========================================================

    // Insert a new artist into the database
    @Override
    public boolean createArtist(Artist artist) {

        String query = "INSERT INTO artists (artist_name) VALUES (?)";

        try (
                Connection connection = dbConnection.connect();
                PreparedStatement preparedStatement = 
                        connection.prepareStatement(query)
        ) {

            preparedStatement.setString(1, artist.getArtistName());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {

            System.out.println("Error creating artist: " + e.getMessage());

            return false;

        }

    }

    // ==========================================================
    // Update Operation
    // ==========================================================

    // Update an existing artist
    @Override
    public boolean updateArtist(Artist artist) {

        String query = "UPDATE artists SET artist_name = ? WHERE id = ?";

        try (
                Connection connection = dbConnection.connect();
                PreparedStatement preparedStatement = 
                        connection.prepareStatement(query)
        ) {

            preparedStatement.setString(1, artist.getArtistName());
            preparedStatement.setInt(2, artist.getId());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {

            System.out.println("Error updating artist: " + e.getMessage());

            return false;

        }

    }

    // ==========================================================
    // Delete Operation
    // ==========================================================

    // Permanently delete an artist from the database
    @Override
    public boolean deleteArtist(int id) {

        String query = "DELETE FROM artists WHERE id = ?";

        try (
                Connection connection = dbConnection.connect();
                PreparedStatement preparedStatement = 
                        connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {

            System.out.println("Error deleting artist: " + e.getMessage());

            return false;

        }
    }

    // Check if an artist exists by ID
    @Override
    public boolean artistExists(int id) {

        String query = "SELECT COUNT(*) FROM artists WHERE id = ?";

        try (
            Connection connection = dbConnection.connect();
            PreparedStatement statement = connection.prepareStatement(query)
        ) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }

            }

        } catch (SQLException e) {
            System.out.println("Error checking artist ID: " + e.getMessage());
        }

        return false;

    }
}
