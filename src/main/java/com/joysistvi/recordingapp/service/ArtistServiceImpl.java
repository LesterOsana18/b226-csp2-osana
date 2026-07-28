package com.joysistvi.recordingapp.service;

import java.util.List;

import com.joysistvi.recordingapp.model.Artist;
import com.joysistvi.recordingapp.repository.ArtistRepository;

// Service Implementation
// Handles the business logic related to Artist objects
public class ArtistServiceImpl implements ArtistService {

    // Dependency Injection (Constructor Injection)
    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    // Retrieve all artists
    @Override
    public List<Artist> getAllArtists() {
        return artistRepository.getAllArtists();
    }

    // Search artists by name
    @Override
    public List<Artist> searchArtists(String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("Search keyword cannot be empty.");
            return List.of();
        }

        return artistRepository.searchArtists(keyword.trim());

    }

    // Validate and create a new artist
    @Override
    public boolean createArtist(Artist artist) {

        if (!isValidArtist(artist)) {
            return false;
        }

        return artistRepository.createArtist(artist);

    }

    // Validate and update an existing artist
    @Override
    public boolean updateArtist(Artist artist) {

        if (artist.getId() <= 0) {
            System.out.println("Invalid artist ID.");
            return false;
        }

        if (!isValidArtist(artist)) {
            return false;
        }

        if (!artistRepository.artistExists(artist.getId())) {
            System.out.println("\nArtist ID does not exist.");
            return false;
        }

        return artistRepository.updateArtist(artist);

    }

    // Delete an artist permanently
    @Override
    public boolean deleteArtist(int id) {

        if (id <= 0) {
            System.out.println("Invalid artist ID.");
            return false;
        }

        if (!artistRepository.artistExists(id)) {
            System.out.println("\nArtist ID does not exist.");
            return false;
        }

        return artistRepository.deleteArtist(id);

    }

    // Helper method to validate Artist object
    private boolean isValidArtist(Artist artist) {

        if (artist == null) {
            System.out.println("Artist object cannot be null.");
            return false;
        }

        if (artist.getArtistName() == null || artist.getArtistName().trim().isEmpty()) {
            System.out.println("Artist name cannot be empty.");
            return false;
        }

        return true;

    }

}
