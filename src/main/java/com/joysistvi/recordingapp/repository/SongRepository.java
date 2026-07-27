package com.joysistvi.recordingapp.repository;

import java.util.List;

import com.joysistvi.recordingapp.model.Song;

// Repository Interface
// Defines all database operations available for Song objects
public interface SongRepository {

    // --- Read Operation ---

    // Retrieve all active songs from the database
    List<Song> getAllSongs();

    // Retrieve all archived songs from the database
    List<Song> getArchivedSongs();

    // Search songs by title
    List<Song> searchSongs(String keyword);

    // --- Create Operation ---

    // Insert a new song into the database
    boolean createSong(Song song);

    // --- Update Operation ---

    // Update an existing song
    boolean updateSong(Song song);

    // --- Delete Operation ---

    // Permanently delete a song from the database
    boolean deleteSong(int id);

    // Archive Operation (Soft Delete)

    // Archive a song instead of permanently deleting it
    boolean archiveSong(int id);

    // Restore Operation

    // Restore an archived song
    boolean restoreSong(int id);

    // Check if an album exists
    boolean albumExists(int albumId);
}
