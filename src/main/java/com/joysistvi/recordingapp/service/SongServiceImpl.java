package com.joysistvi.recordingapp.service;

import java.util.List;

import com.joysistvi.recordingapp.model.Song;
import com.joysistvi.recordingapp.repository.SongRepository;

// Service Implementation
// Handles the business logic related to Song objects
public class SongServiceImpl implements SongService {

    // Dependency Injection (Constructor Injection)
    private final SongRepository songRepository;

    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    // Retrieve all active songs
    @Override
    public List<Song> getAllSongs() {
        return songRepository.getAllSongs();
    }

    // Retrieve all archived songs
    @Override
    public List<Song> getArchivedSongs() {
        return songRepository.getArchivedSongs();
    }

    // Search songs by title
    @Override
    public List<Song> searchSongs(String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("Search keyword cannot be empty.");
            return List.of();
        }

        return songRepository.searchSongs(keyword.trim());

    }

    // Validate and create a new song
    @Override
    public boolean createSong(Song song) {

        if (!isValidSong(song)) {
            return false;
        }

        return songRepository.createSong(song);

    }

    // Validate and update an existing song
    @Override
    public boolean updateSong(Song song) {

        if (song.getId() <= 0) {
            System.out.println("Invalid song ID.");
            return false;
        }

        if (!isValidSong(song)) {
            return false;
        }

        return songRepository.updateSong(song);

    }

    // Delete a song permanently
    @Override
    public boolean deleteSong(int id) {

        if (id <= 0) {
            System.out.println("Invalid song ID.");
            return false;
        }

        return songRepository.deleteSong(id);

    }

    // Archive (soft delete) a song
    @Override
    public boolean archiveSong(int id) {

        if (id <= 0) {
            System.out.println("Invalid song ID.");
            return false;
        }

        return songRepository.archiveSong(id);

    }

    // Restore an archived song
    @Override
    public boolean restoreSong(int id) {

        if (id <= 0) {
            System.out.println("Invalid song ID.");
            return false;
        }

        return songRepository.restoreSong(id);

    }

    // Validate the Song object before sending it to the repository
    private boolean isValidSong(Song song) {

        if (song == null) {
            System.out.println("Song object cannot be null.");
            return false;
        }

        if (song.getTitle() == null || song.getTitle().trim().isEmpty()) {
            System.out.println("Song title cannot be empty.");
            return false;
        }

        if (song.getSongLength() == null || song.getSongLength().trim().isEmpty()) {
            System.out.println("Song length cannot be empty.");
            return false;
        }

        if (song.getGenre() == null || song.getGenre().trim().isEmpty()) {
            System.out.println("Song genre cannot be empty.");
            return false;
        }

        if (song.getAlbumId() <= 0) {
            System.out.println("Invalid album ID.");
            return false;
        }

        return true;

    }

}
