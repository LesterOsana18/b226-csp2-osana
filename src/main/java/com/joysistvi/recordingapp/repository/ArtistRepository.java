package com.joysistvi.recordingapp.repository;

import java.util.List;

import com.joysistvi.recordingapp.model.Artist;


// Repository Interface
// Defines all database operations available for Artist objects
public interface ArtistRepository {

    // --- Read Operation --- 

    // Retrieve all artists from the database
    List<Artist> getAllArtists();

    // Search artists by name
    List<Artist> searchArtists(String keyword);

    // --- Create Operation ---

    // Insert a new artist into the database
    boolean createArtist(Artist artist);

    // --- Update Operation ---

    // Update an existing artist
    boolean updateArtist(Artist artist);

    // --- Delete Operation ---

    // Permanently delete an artist from the database
    boolean deleteArtist(int id);

}
