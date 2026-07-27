package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.config.DbConnection;
import com.joysistvi.recordingapp.model.Album;

import java.sql.Connection;
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

}
