package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.config.DbConnection;
import com.joysistvi.recordingapp.model.Album;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

// Repository Implementation
// Handles all database operations related to Album objects
public class AlbumRepositoryImpl implements AlbumRepository {

    // Dependency Injection (Constructor Injection)
    private final DbConnection dbConnection;

    public AlbumRepositoryImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // ==========================================================
    // Read Operations
    // ==========================================================

    // Retrieve all albums from the database
    @Override
    public List<Album> getAllAlbums() {

        List<Album> albums = new ArrayList<>();

        String query =
                "SELECT albums.id, "
                + "albums.album_name, "
                + "albums.year_released, "
                + "albums.artist_id, "
                + "artists.artist_name "
                + "FROM albums "
                + "JOIN artists "
                + "ON albums.artist_id = artists.id "
                + "ORDER BY artists.id";

        try (
                Connection connection = dbConnection.connect();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {

            while (resultSet.next()) {

                Album album = new Album();

                album.setId(resultSet.getInt("id"));
                album.setAlbumName(resultSet.getString("album_name"));
                album.setYearReleased(resultSet.getInt("year_released"));
                album.setArtistId(resultSet.getInt("artist_id"));
                album.setArtistName(resultSet.getString("artist_name"));

                albums.add(album);

            }

        } catch (SQLException e) {

            System.out.println("Error retrieving albums: " + e.getMessage());

        }

        return albums;

    }

    // Search albums by title
    @Override
    public List<Album> searchAlbums(String keyword) {

        List<Album> albums = new ArrayList<>();

        String query = 
                "SELECT id, album_name, year_released, artist_id "
                + "FROM albums "
                + "WHERE album_name LIKE ?";

        try (
                Connection connection = dbConnection.connect();
                PreparedStatement preparedStatement = 
                        connection.prepareStatement(query);
        ) {

            preparedStatement.setString(1, "%" + keyword + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                albums.add(new Album(
                    resultSet.getInt("id"),
                    resultSet.getString("album_name"),
                    resultSet.getInt("year_released"),
                    resultSet.getInt("artist_id")
                ));

            }

        } catch (SQLException e) {

            System.out.println("Error searching albums: " + e.getMessage());

        }

        return albums;

    }

    // ==========================================================
    // Create Operation
    // ==========================================================

    // Insert a new album into the database
    @Override
    public boolean createAlbum(Album album) {

        String query = 
                "INSERT INTO albums (album_name, year_released, artist_id) "
                + "VALUES (?, ?, ?)";

        try (
                Connection connection = dbConnection.connect();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(query)
        ) {

            preparedStatement.setString(1, album.getAlbumName());
            preparedStatement.setInt(2, album.getYearReleased());
            preparedStatement.setInt(3, album.getArtistId());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {

            System.out.println("Error creating album: " + e.getMessage());

            return false;

        }

    }

    // ==========================================================
    // Update Operation
    // ==========================================================

    // Update an existing album
    @Override
    public boolean updateAlbum(Album album) {

        String query = 
                "UPDATE albums "
                + "SET album_name = ?, year_released = ?, artist_id = ? "
                + "WHERE id = ?";

        try (
                Connection connection = dbConnection.connect();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(query)
        ) {

            preparedStatement.setString(1, album.getAlbumName());
            preparedStatement.setInt(2, album.getYearReleased());
            preparedStatement.setInt(3, album.getArtistId());
            preparedStatement.setInt(4, album.getId());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {

            System.out.println("Error updating album: " + e.getMessage());

            return false;

        }

    }

    // ==========================================================
    // Delete Operation
    // ==========================================================

    // Permanently delete an album from the database
    @Override
    public boolean deleteAlbum(int id) {

        String query = 
                "DELETE FROM albums WHERE id = ?";

        try (
                Connection connection = dbConnection.connect();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(query)
        ) {

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {

            System.out.println("Error deleting album: " + e.getMessage());

            return false;

        }

    }

    // Check if an album exists
    @Override
    public boolean albumExists(int albumId) {
        String query = "SELECT COUNT(*) FROM albums WHERE id = ?";

        try (
                Connection connection = dbConnection.connect();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(query)
        ) {

            preparedStatement.setInt(1, albumId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }

        } catch (Exception e) {

            System.out.println("Error checking album: " + e.getMessage());

        }

        return false;
    }
}
