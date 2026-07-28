package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.config.DbConnection;
import com.joysistvi.recordingapp.model.Playlist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

// Repository Implementation
// Handles all database operations related to Playlist objects
public class PlaylistRepositoryImpl implements PlaylistRepository {

    // Dependency Injection (Constructor Injection)
    private final DbConnection dbConnection;

    public PlaylistRepositoryImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // ==========================================================
    // Read Operations
    // ==========================================================

    // Retrieve all playlists from the database
    @Override
    public List<Playlist> getAllPlaylists() {

        List<Playlist> playlists = new ArrayList<>();

        String query =
                "SELECT playlists.id, "
                + "playlists.playlist_name, "
                + "playlists.user_id, "
                + "users.username "
                + "FROM playlists "
                + "JOIN users "
                + "ON playlists.user_id = users.id "
                + "ORDER BY users.id";

        try (
                Connection connection = dbConnection.connect();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {

            while (resultSet.next()) {

                playlists.add(new Playlist(
                    resultSet.getInt("id"),
                    resultSet.getString("playlist_name"),
                    resultSet.getInt("user_id")
                ));

            }

        } catch (SQLException e) {

            System.out.println("Error retrieving playlists: " + e.getMessage());

        }

        return playlists;

    }

    // Search playlists by name
    @Override
    public List<Playlist> searchPlaylists(String keyword) {

        List<Playlist> playlists = new ArrayList<>();

        String query =
                "SELECT playlists.id, "
                + "playlists.playlist_name, "
                + "playlists.user_id, "
                + "users.username "
                + "FROM playlists "
                + "JOIN users "
                + "ON playlists.user_id = users.id "
                + "WHERE playlists.playlist_name LIKE ?";

        try (
                Connection connection = dbConnection.connect();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {

            preparedStatement.setString(1, "%" + keyword + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {

                    playlists.add(new Playlist(
                        resultSet.getInt("id"),
                        resultSet.getString("playlist_name"),
                        resultSet.getInt("user_id")
                    ));

                }

            }

        } catch (SQLException e) {

            System.out.println("Error searching playlists: " + e.getMessage());

        }

        return playlists;

    }

    // ==========================================================
    // Create Operation
    // ==========================================================

    // Insert a new playlist into the database
    @Override
    public boolean createPlaylist(Playlist playlist) {

        String query =
                "INSERT INTO playlists (playlist_name, user_id) "
                + "VALUES (?, ?)";

        try (
                Connection connection = dbConnection.connect();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {

            preparedStatement.setString(1, playlist.getPlaylistName());
            preparedStatement.setInt(2, playlist.getUserId());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {

            System.out.println("Error creating playlist: " + e.getMessage());

            return false;

        }

    }

    // ==========================================================
    // Update Operation
    // ==========================================================

    // Update an existing playlist
    @Override
    public boolean updatePlaylist(Playlist playlist) {

        String query =
                "UPDATE playlists "
                + "SET playlist_name = ?, user_id = ? "
                + "WHERE id = ?";

        try (
                Connection connection = dbConnection.connect();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {

            preparedStatement.setString(1, playlist.getPlaylistName());
            preparedStatement.setInt(2, playlist.getUserId());
            preparedStatement.setInt(3, playlist.getId());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {

            System.out.println("Error updating playlist: " + e.getMessage());

            return false;

        }

    }

    // ==========================================================
    // Delete Operation
    // ==========================================================

    // Permanently delete a playlist from the database
    @Override
    public boolean deletePlaylist(int id) {

        String query =
                "DELETE FROM playlists "
                + "WHERE id = ?";

        try (
                Connection connection = dbConnection.connect();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {

            System.out.println("Error deleting playlist: " + e.getMessage());

            return false;

        }

    }

    // Check if a playlist exists
    @Override
    public boolean playlistExists(int playlistId) {

        String query =
                "SELECT COUNT(*) AS count "
                + "FROM playlists "
                + "WHERE id = ?";

        try (
                Connection connection = dbConnection.connect();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {

            preparedStatement.setInt(1, playlistId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    return resultSet.getInt("count") > 0;
                }

            }

        } catch (SQLException e) {

            System.out.println("Error checking if playlist exists: " + e.getMessage());

        }

        return false;
    }
}
