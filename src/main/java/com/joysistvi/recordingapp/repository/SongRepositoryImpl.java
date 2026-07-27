package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.config.DbConnection;
import com.joysistvi.recordingapp.model.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

// Repository Implementation
// Handles all database operations related to Song objects
public class SongRepositoryImpl implements SongRepository {

    // Dependency Injection (Constructor Injection)
    private final DbConnection dbConnection;

    public SongRepositoryImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // ==========================================================
    // Read Operations
    // ==========================================================

    // Retrieve all active songs from the database
    @Override
    public List<Song> getAllSongs() {

        List<Song> songs = new ArrayList<>();

        String query =
                "SELECT id, title, song_length, genre, album_id "
                + "FROM songs "
                + "WHERE is_archived = 0";

        try (
                Connection connection = dbConnection.connect();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {

            while (resultSet.next()) {

                songs.add(new Song(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("song_length"),
                        resultSet.getString("genre"),
                        resultSet.getInt("album_id")
                ));

            }

        } catch (SQLException e) {

            System.out.println("Error retrieving songs: " + e.getMessage());

        }

        return songs;

    }

    // Retrieve all archived songs from the database
    @Override
    public List<Song> getArchivedSongs() {

        List<Song> songs = new ArrayList<>();

        String query =
                "SELECT id, title, song_length, genre, album_id "
                + "FROM songs "
                + "WHERE is_archived = 1";

        try (
                Connection connection = dbConnection.connect();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {

            while (resultSet.next()) {

                songs.add(new Song(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("song_length"),
                        resultSet.getString("genre"),
                        resultSet.getInt("album_id")
                ));

            }

        } catch (SQLException e) {

            System.out.println("Error retrieving archived songs: " + e.getMessage());

        }

        return songs;

    }

    // Search songs by title
    @Override
    public List<Song> searchSongs(String keyword) {

        List<Song> songs = new ArrayList<>();

        String query =
                "SELECT id, title, song_length, genre, album_id "
                + "FROM songs "
                + "WHERE is_archived = 0 "
                + "AND title LIKE ?";

        try (
                Connection connection = dbConnection.connect();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(query)
        ) {

            preparedStatement.setString(1, "%" + keyword + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                songs.add(new Song(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("song_length"),
                        resultSet.getString("genre"),
                        resultSet.getInt("album_id")
                ));

            }

        } catch (SQLException e) {

            System.out.println("Error searching songs: " + e.getMessage());

        }

        return songs;

    }

    // ==========================================================
    // Create Operation
    // ==========================================================

    // Insert a new song into the database
    @Override
    public boolean createSong(Song song) {

        String query =
                "INSERT INTO songs (title, song_length, genre, album_id) "
                + "VALUES (?, ?, ?, ?)";

        try (
                Connection connection = dbConnection.connect();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(query)
        ) {

            preparedStatement.setString(1, song.getTitle());
            preparedStatement.setString(2, song.getSongLength());
            preparedStatement.setString(3, song.getGenre());
            preparedStatement.setInt(4, song.getAlbumId());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {

            System.out.println("Error creating song: " + e.getMessage());

            return false;

        }

    }

    // ==========================================================
    // Update Operation
    // ==========================================================

    // Update an existing song
    @Override
    public boolean updateSong(Song song) {

        String query =
                "UPDATE songs "
                + "SET title = ?, song_length = ?, genre = ?, album_id = ? "
                + "WHERE id = ?";

        try (
                Connection connection = dbConnection.connect();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(query)
        ) {

            preparedStatement.setString(1, song.getTitle());
            preparedStatement.setString(2, song.getSongLength());
            preparedStatement.setString(3, song.getGenre());
            preparedStatement.setInt(4, song.getAlbumId());
            preparedStatement.setInt(5, song.getId());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {

            System.out.println("Error updating song: " + e.getMessage());

            return false;

        }

    }

    // ==========================================================
    // Delete Operation
    // ==========================================================

    // Permanently delete a song from the database
    @Override
    public boolean deleteSong(int id) {

        String query =
                "DELETE FROM songs WHERE id = ?";

        try (
                Connection connection = dbConnection.connect();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(query)
        ) {

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {

            System.out.println("Error deleting song: " + e.getMessage());

            return false;

        }

    }

    // ==========================================================
    // Archive Operation
    // ==========================================================

    // Archive a song instead of permanently deleting it
    @Override
    public boolean archiveSong(int id) {

        String query =
                "UPDATE songs SET is_archived = 1 WHERE id = ?";

        try (
                Connection connection = dbConnection.connect();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(query)
        ) {

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {

            System.out.println("Error archiving song: " + e.getMessage());

            return false;

        }

    }

    // ==========================================================
    // Restore Operation
    // ==========================================================

    // Restore an archived song
    @Override
    public boolean restoreSong(int id) {

        String query =
                "UPDATE songs SET is_archived = 0 WHERE id = ?";

        try (
                Connection connection = dbConnection.connect();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(query)
        ) {

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {

            System.out.println("Error restoring song: " + e.getMessage());

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
