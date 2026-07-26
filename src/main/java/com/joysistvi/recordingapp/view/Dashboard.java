package com.joysistvi.recordingapp.view;

import com.joysistvi.recordingapp.config.DbConnection;
import com.joysistvi.recordingapp.controller.SongController;
import com.joysistvi.recordingapp.repository.SongRepo;
import com.joysistvi.recordingapp.repository.SongRepoImpl;
import com.joysistvi.recordingapp.service.SongService;

// Application Entry Point
// Initializes the MVC components and launches the dashboard
public class Dashboard {

    public static void main(String[] args) {

        // Create the database connection
        DbConnection dbConnection = new DbConnection();

        // Initialize the Repository layer
        SongRepo songRepo = new SongRepoImpl(dbConnection);

        // Initialize the Service layer
        SongService songService = new SongService(songRepo);

        // Initialize the Controller layer
        SongController songController = new SongController(songService);

        // Launch the application dashboard
        SongView songView = new SongView(songController);
        songView.dashboard();

    }

}
