package com.joysistvi.recordingapp.service;

import java.util.List;

import com.joysistvi.recordingapp.model.Album;
import com.joysistvi.recordingapp.repository.AlbumRepository;

// Service Implementation
// Handles the business logic related to Album objects
public class AlbumServiceImpl implements AlbumService {

    // Dependency Injection (Constructor Injection)
    private final AlbumRepository albumRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    // Retrieve all active albums
    @Override
    public List<Album> getAllAlbums() {
        return albumRepository.getAllAlbums();
    }

    // Search albums by title
    @Override
    public List<Album> searchAlbums(String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("Search keyword cannot be empty.");
            return List.of();
        }

        return albumRepository.searchAlbums(keyword.trim());

    }

    // Validate and create a new album
    @Override
    public boolean createAlbum(Album album) {

        if (!isValidAlbum(album)) {
            return false;
        }

        return albumRepository.createAlbum(album);

    }

    // Validate and update an existing album
    @Override
    public boolean updateAlbum(Album album) {

        if (album.getId() <= 0) {
            System.out.println("Invalid album ID.");
            return false;
        }

        if (!isValidAlbum(album)) {
            return false;
        }

        if (!albumRepository.albumExists(album.getId())) {
            System.out.println("Album not found.");
            return false;
        }

        return albumRepository.updateAlbum(album);

    }

    // Delete an album permanently
    @Override
    public boolean deleteAlbum(int id) {

        if (id <= 0) {
            System.out.println("Invalid album ID.");
            return false;
        }

        return albumRepository.deleteAlbum(id);

    }

    // Validate the Album object before sending it to the repository
    private boolean isValidAlbum(Album album) {

        if (album == null) {
            System.out.println("Album object cannot be null.");
            return false;
        }

        if (album.getAlbumName() == null || album.getAlbumName().trim().isEmpty()) {
            System.out.println("Album name cannot be empty.");
            return false;
        }

        if (album.getYearReleased() <= 0) {
            System.out.println("Invalid release year.");
            return false;
        }

        if (album.getArtistId() <= 0) {
            System.out.println("Invalid artist ID.");
            return false;
        }

        return true;

    }

}
