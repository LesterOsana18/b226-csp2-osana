package com.joysistvi.recordingapp.repository;

import java.util.List;

import com.joysistvi.recordingapp.model.Playlist;

// Repository Interface
// Defines all database operations available for Playlist objects
public interface PlaylistRepository {

    // --- Read Operation ---

    // Retrieve all active playlists from the database
    List<Playlist> getAllPlaylists();

    // Search playlists by name
    List<Playlist> searchPlaylists(String keyword);

    // --- Create Operation ---

    // Insert a new playlist into the database
    boolean createPlaylist(Playlist playlist);

    // --- Update Operation ---

    // Update an existing playlist
    boolean updatePlaylist(Playlist playlist);

    // --- Delete Operation ---

    // Permanently delete a playlist from the database
    boolean deletePlaylist(int id);

    // Check if a playlist exists
    boolean playlistExists(int playlistId);
}
