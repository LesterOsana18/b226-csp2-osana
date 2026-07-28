package com.joysistvi.recordingapp.controller;

import java.util.List;

import com.joysistvi.recordingapp.model.Artist;
import com.joysistvi.recordingapp.service.ArtistService;

// Controller Class
// Acts as the bridge between the View and the Service layer
public class ArtistController {

    // Dependency Injection (Constructor Injection)
    private final ArtistService artistService;

    // Constructor
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    // Retrieve and return all artists
    public List<Artist> listArtists() {
        return artistService.getAllArtists();
    }

    // Search artists by name
    public List<Artist> searchArtist(String keyword) {
        return artistService.searchArtists(keyword);
    }

    // Create a new artist
    public boolean createArtist(Artist artist) {
        return artistService.createArtist(artist);
    }

    // Update an existing artist
    public boolean updateArtist(Artist artist) {
        return artistService.updateArtist(artist);
    }

    // Permanently delete an artist
    public boolean deleteArtist(int id) {
        return artistService.deleteArtist(id);
    }

}
