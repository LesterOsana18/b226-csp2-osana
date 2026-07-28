package com.joysistvi.recordingapp;

import com.joysistvi.recordingapp.config.DbConnection;

import com.joysistvi.recordingapp.controller.*;
import com.joysistvi.recordingapp.repository.*;
import com.joysistvi.recordingapp.service.*;
import com.joysistvi.recordingapp.view.*;

import java.util.Scanner;

// Main Application
// Entry point of the Recording Studio App
public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        DbConnection dbConnection = new DbConnection();

        // ======================================================
        // Dependency Injection
        // Instantiate and connect the Song module components
        // ======================================================

        AlbumRepository albumRepository = new AlbumRepositoryImpl(dbConnection);
        AlbumService albumService = new AlbumServiceImpl(albumRepository);
        AlbumController albumController = new AlbumController(albumService);

        ArtistRepository artistRepository = new ArtistRepositoryImpl(dbConnection);
        ArtistService artistService = new ArtistServiceImpl(artistRepository);
        ArtistController artistController = new ArtistController(artistService);

        SongRepository songRepository = new SongRepositoryImpl(dbConnection);
        SongService songService = new SongServiceImpl(songRepository);
        SongController songController = new SongController(songService);
        
        // Instantiate the views with their dependencies
        SongView songView =
                new SongView(songController, albumController, scanner);

        AlbumView albumView =
                new AlbumView(albumController, scanner);

        ArtistView artistView =
                new ArtistView(artistController, scanner);

        int choice;

        do {

            printMainMenu();

            choice = readInt(scanner);

            switch (choice) {

                case 1 -> songView.run();

                case 2 -> albumView.run();

                case 3 -> artistView.run();

                case 4 ->
                        System.out.println("Playlist Management is not implemented yet.");

                case 5 ->
                        System.out.println("User Management is not implemented yet.");

                case 0 ->
                        System.out.println("Exiting Recording Studio App. Goodbye!");

                default ->
                        System.out.println("Invalid choice. Please try again.");

            }

        } while (choice != 0);

        scanner.close();

    }

    // Display the application's main menu
    private static void printMainMenu() {

        clearScreen();

        System.out.println("===== RECORDING STUDIO APP =====");
        System.out.println("1. Song Management");
        System.out.println("2. Album Management");
        System.out.println("3. Artist Management");
        System.out.println("4. Playlist Management");
        System.out.println("5. User Management");
        System.out.println("0. Exit");
        System.out.print("\nChoice: ");

    }

    // Read an integer safely from the console
    private static int readInt(Scanner scanner) {

        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }

        int value = scanner.nextInt();
        scanner.nextLine();

        return value;

    }

    // Clear the console screen
    public static void clearScreen() {

        System.out.print("\033[H\033[2J");
        System.out.flush();

    }

}
