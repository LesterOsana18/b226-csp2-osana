package com.joysistvi.recordingapp.service;

import java.util.List;

import com.joysistvi.recordingapp.model.Song;

// Service Interface
// Defines the business operations available for Song objects
public interface SongService {

    // Retrieve all active songs
    List<Song> getAllSongs();

    // Retrieve all archived songs
    List<Song> getArchivedSongs();

    // Search songs by title
    List<Song> searchSongs(String keyword);

    // Validate and create a new song
    boolean createSong(Song song);

    // Validate and update an existing song
    boolean updateSong(Song song);

    // Delete a song permanently
    boolean deleteSong(int id);

    // Archive (soft delete) a song
    boolean archiveSong(int id);

    // Restore an archived song
    boolean restoreSong(int id);

}
