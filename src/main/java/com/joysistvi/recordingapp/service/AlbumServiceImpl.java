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

}
