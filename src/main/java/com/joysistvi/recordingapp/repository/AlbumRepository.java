package com.joysistvi.recordingapp.repository;

import java.util.List;

import com.joysistvi.recordingapp.model.Album;

// Repository Interface
// Defines all database operations available for Album objects
public interface AlbumRepository {
    
    // --- Read Operation ---

    // Retrieve all active albums from the database
    List<Album> getAllAlbums();

    // // Retrieve all archived albums from the database
    // List<Album> getArchivedAlbums();

    // // Search albums by title
    // List<Album> searchAlbums(String keyword);

    // // --- Create Operation ---

    // // Insert a new album into the database
    // boolean createAlbum(Album album);

    // // --- Update Operation ---

    // // Update an existing album
    // boolean updateAlbum(Album album);

    // // --- Delete Operation ---

    // // Permanently delete an album from the database
    // boolean deleteAlbum(int id);

    // // Archive Operation (Soft Delete)

    // // Archive an album instead of permanently deleting it
    // boolean archiveAlbum(int id);

    // // Restore Operation
    // boolean restoreAlbum(int id);

    // // Check if an album exists
    // boolean albumExists(int albumId);
}
