package com.joysistvi.recordingapp.controller;

import java.util.List;

import com.joysistvi.recordingapp.model.Song;
import com.joysistvi.recordingapp.service.SongService;

// Controller Class
// Acts as the bridge between the View and the Service layer
public class SongController {

    // Dependency Injection (Constructor Injection)
    private final SongService songService;

    // Constructor
    public SongController(SongService songService) {
        this.songService = songService;
    }

    // Retrieve and return all active songs
    public List<Song> listSongs() {
        return songService.getAllSongs();
    }

    // Retrieve archived songs
    public List<Song> listArchivedSongs() {
        return songService.getArchivedSongs();
    }

    // Search songs by title
    public List<Song> searchSong(String keyword) {
        return songService.searchSongs(keyword);
    }

    // Create a new song
    public boolean createSong(Song song) {
        return songService.createSong(song);
    }

    // Update an existing song
    public boolean updateSong(Song song) {
        return songService.updateSong(song);
    }

    // Permanently delete a song
    public boolean deleteSong(int id) {
        return songService.deleteSong(id);
    }

    // Archive a song
    public boolean archiveSong(int id) {
        return songService.archiveSong(id);
    }

    // Restore an archived song
    public boolean restoreSong(int id) {
        return songService.restoreSong(id);
    }

}
