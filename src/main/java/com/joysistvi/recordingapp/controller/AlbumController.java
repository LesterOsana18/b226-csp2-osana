package com.joysistvi.recordingapp.controller;

import java.util.List;

import com.joysistvi.recordingapp.model.Album;
import com.joysistvi.recordingapp.service.AlbumService;

// Controller Class
// Acts as the bridge between the View and the Service layer
public class AlbumController {
    
    // Dependency Injection (Constructor Injection)
    private final AlbumService albumService;

    // Constructor
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    // Retrieve and return all active albums
    public List<Album> listAlbums() {
        return albumService.getAllAlbums();
    }

    // // Retrieve archived albums
    // public List<Album> listArchivedAlbums() {
    //     return albumService.getArchivedAlbums();
    // }

    // // Search albums by title
    // public List<Album> searchAlbum(String keyword) {
    //     return albumService.searchAlbums(keyword);
    // }

    // // Create a new album
    // public boolean createAlbum(Album album) {
    //     return albumService.createAlbum(album);
    // }

    // // Update an existing album
    // public boolean updateAlbum(Album album) {
    //     return albumService.updateAlbum(album);
    // }

    // // Permanently delete an album
    // public boolean deleteAlbum(int id) {
    //     return albumService.deleteAlbum(id);
    // }

    // // Archive an album
    // public boolean archiveAlbum(int id) {
    //     return albumService.archiveAlbum(id);
    // }

    // // Restore an archived album
    // public boolean restoreAlbum(int id) {
    //     return albumService.restoreAlbum(id);
    // }

}
