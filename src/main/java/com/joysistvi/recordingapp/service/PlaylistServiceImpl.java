package com.joysistvi.recordingapp.service;

import java.util.List;

import com.joysistvi.recordingapp.model.Playlist;
import com.joysistvi.recordingapp.repository.PlaylistRepository;

// Service Implementation
// Handles the business logic related to Playlist objects
public class PlaylistServiceImpl implements PlaylistService {
    
    // Dependency Injection (Constructor Injection)
    private final PlaylistRepository playlistRepository;

    public PlaylistServiceImpl(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    // Retrieve all playlists
    @Override
    public List<Playlist> getAllPlaylists() {
        return playlistRepository.getAllPlaylists();
    }

    // Search playlists by name
    @Override
    public List<Playlist> searchPlaylists(String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("Search keyword cannot be empty.");
            return List.of();
        }

        return playlistRepository.searchPlaylists(keyword.trim());

    }

    // Validate and create a new playlist
    @Override
    public boolean createPlaylist(Playlist playlist) {

        if (!isValidPlaylist(playlist)) {
            return false;
        }

        return playlistRepository.createPlaylist(playlist);

    }

    // Validate and update an existing playlist
    @Override
    public boolean updatePlaylist(Playlist playlist) {

        if (playlist.getId() <= 0) {
            System.out.println("Invalid playlist ID.");
            return false;
        }

        if (!isValidPlaylist(playlist)) {
            return false;
        }

        if (!playlistRepository.playlistExists(playlist.getId())) {
            System.out.println("Playlist not found.");
            return false;
        }

        return playlistRepository.updatePlaylist(playlist);

    }

    // Delete a playlist permanently
    @Override
    public boolean deletePlaylist(int id) {

        if (id <= 0) {
            System.out.println("Invalid playlist ID.");
            return false;
        }

        if (!playlistRepository.playlistExists(id)) {
            System.out.println("Playlist not found.");
            return false;
        }

        return playlistRepository.deletePlaylist(id);

    }

    // Validate the Playlist object before sending it to the repository
    private boolean isValidPlaylist(Playlist playlist) {

        if (playlist == null) {
            System.out.println("Playlist object cannot be null.");
            return false;
        }

        if (playlist.getPlaylistName() == null || playlist.getPlaylistName().trim().isEmpty()) {
            System.out.println("Playlist name cannot be empty.");
            return false;
        }

        if (playlist.getUserId() <= 0) {
            System.out.println("Invalid user ID.");
            return false;
        }

        return true;

    }

}
