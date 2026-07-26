package com.joysistvi.recordingapp.service;

import java.util.List;

import com.joysistvi.recordingapp.model.Song;
import com.joysistvi.recordingapp.repository.SongRepo;

// Service Class
// Handles the business logic related to Song objects
public class SongService {

    // Dependency Injection (Constructor Injection)
    private final SongRepo songRepo;

    public SongService(SongRepo songRepo) {
        this.songRepo = songRepo;
    }

    // Retrieve all active songs
    public List<Song> listSongs() {
        return songRepo.getAllSongs();
    }

    // Validate the song information before saving it
    public boolean createSong(Song song) {

        if (song == null) {
            System.out.println("Song object cannot be null.");
            return false;
        }

        if (song.getTitle() == null || song.getTitle().trim().isEmpty()) {
            System.out.println("Song title cannot be empty.");
            return false;
        }

        return songRepo.createSong(song);
    }

}
