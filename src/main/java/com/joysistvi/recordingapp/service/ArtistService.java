package com.joysistvi.recordingapp.service;

import java.util.List;

import com.joysistvi.recordingapp.model.Artist;

// Service Interface
// Defines the business operations available for Artist objects
public interface ArtistService {

    // Retrieve all artists
    List<Artist> getAllArtists();

    // Search artists by name
    List<Artist> searchArtists(String keyword);

    // Validate and create a new artist
    boolean createArtist(Artist artist);

    // Validate and update an existing artist
    boolean updateArtist(Artist artist);

    // Delete an artist permanently
    boolean deleteArtist(int id);

}
