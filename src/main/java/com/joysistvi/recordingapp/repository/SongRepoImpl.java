package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.config.DbConnection;
import com.joysistvi.recordingapp.model.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

// Repository Implementation
// Handles all database operations related to Song objects
public class SongRepoImpl implements SongRepo {

    // Dependency Injection (Constructor Injection)
    private final DbConnection dbConnection;

    public SongRepoImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // Retrieve all active songs from the database
    @Override
    public List<Song> getAllSongs() {

        List<Song> songs = new ArrayList<>();

        String query =
                "SELECT * FROM songs WHERE is_archived = 0";

        try (
                Connection connection = dbConnection.connect();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {

            while (resultSet.next()) {

                songs.add(new Song(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("length"),
                        resultSet.getString("genre"),
                        resultSet.getInt("album_id")
                ));

            }

        } catch (Exception e) {

            System.out.println("Error retrieving songs: " + e.getMessage());

        }

        return songs;

    }

    // Insert a new song into the database
    @Override
    public boolean createSong(Song song) {

        String query =
                "INSERT INTO songs (title, length, genre, album_id) "
                + "VALUES (?, ?, ?, ?)";

        try (
                Connection connection = dbConnection.connect();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(query)
        ) {

            preparedStatement.setString(1, song.getTitle());
            preparedStatement.setString(2, song.getLength());
            preparedStatement.setString(3, song.getGenre());
            preparedStatement.setInt(4, song.getAlbumId());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (Exception e) {

            System.out.println("Error creating song: " + e.getMessage());

            return false;

        }

    }

}
