package com.joysistvi.recordingapp.repository;

import java.util.List;
import com.joysistvi.recordingapp.model.Song;

// Repository Interface
// Defines the database operations available for Song objects
public interface SongRepo {

    // Retrieve all active songs from the database
    List<Song> getAllSongs();

    // Insert a new song into the database
    boolean createSong(Song song);

}
