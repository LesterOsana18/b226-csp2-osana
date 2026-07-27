package com.joysistvi.recordingapp.service;

import java.util.List;

import com.joysistvi.recordingapp.model.Album;


// Service Interface
// Defines the business operations available for Album objects
public interface AlbumService {

    // Retrieve all active albums
    List<Album> getAllAlbums();

    // // Retrieve all archived albums
    // List<Album> getArchivedAlbums();

    // Search albums by title
    List<Album> searchAlbums(String keyword);

    // Validate and create a new album
    boolean createAlbum(Album album);

    // Validate and update an existing album
    boolean updateAlbum(Album album);

    // Delete an album permanently
    boolean deleteAlbum(int id);

    // // Archive (soft delete) an album
    // boolean archiveAlbum(int id);

    // // Restore an archived album
    // boolean restoreAlbum(int id);

}
