package com.joysistvi.recordingapp.controller;

import java.util.List;

import com.joysistvi.recordingapp.model.Song;
import com.joysistvi.recordingapp.service.SongService;

public class SongController {

	// Declare the SongService object
	private final SongService songService;

	// Constructor
	public SongController(SongService songService) {
		this.songService = songService;
	}

	// Retrieve and return all songs
	public List<Song> listSongs() {
		return songService.listSongs();
	}

	// Create a new song
	public boolean createSong(Song song) {
		return songService.createSong(song);
	}

}