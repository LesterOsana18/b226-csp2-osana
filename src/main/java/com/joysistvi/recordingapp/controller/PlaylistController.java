package com.joysistvi.recordingapp.controller;

import java.util.List;

import com.joysistvi.recordingapp.model.Playlist;
import com.joysistvi.recordingapp.service.PlaylistService;

// Controller Class
// Acts as the bridge between the View and the Service layer
public class PlaylistController {

    // Dependency Injection (Constructor Injection)
    private final PlaylistService playlistService;

    // Constructor
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    // Retrieve and return all active playlists
    public List<Playlist> listPlaylists() {
        return playlistService.getAllPlaylists();
    }

    // Search playlists by name
    public List<Playlist> searchPlaylists(String keyword) {
        return playlistService.searchPlaylists(keyword);
    }

    // Create a new playlist
    public boolean createPlaylist(Playlist playlist) {
        return playlistService.createPlaylist(playlist);
    }

    // Update an existing playlist
    public boolean updatePlaylist(Playlist playlist) {
        return playlistService.updatePlaylist(playlist);
    }

    // Permanently delete a playlist
    public boolean deletePlaylist(int id) {
        return playlistService.deletePlaylist(id);
    }

}
