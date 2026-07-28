package com.joysistvi.recordingapp.service;

import java.util.List;

import com.joysistvi.recordingapp.model.Playlist;

// Service Interface
// Defines the business operations available for Playlist objects
public interface PlaylistService {

    // Retrieve all playlists
    List<Playlist> getAllPlaylists();

    // Search playlists by name 
    List<Playlist> searchPlaylists(String keyword);

    // Validate and create a new playlist
    boolean createPlaylist(Playlist playlist);

    // Validate and update an existing playlist
    boolean updatePlaylist(Playlist playlist);

    // Delete a playlist permanently
    boolean deletePlaylist(int id);

}
