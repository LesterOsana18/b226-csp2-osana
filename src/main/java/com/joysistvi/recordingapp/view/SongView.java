package com.joysistvi.recordingapp.view;

import java.util.Scanner;

import com.joysistvi.recordingapp.controller.SongController;
import com.joysistvi.recordingapp.model.Song;

// View Class
// Handles user interaction for song management
public class SongView {

    // Dependency Injection (Constructor Injection)
    private final SongController songController;

    // Scanner object for user input
    private final Scanner input;

    // Constructor Injection
    public SongView(SongController songController, Scanner scanner) {
        this.songController = songController;
        this.input = scanner;
    }

    // Display the Song Management dashboard
    public void run() {

        boolean running = true;

        while (running) {

            System.out.println("\n==============================");
            System.out.println("      SONG MANAGEMENT");
            System.out.println("==============================");
            System.out.println("1. Add Song");
            System.out.println("2. View All Songs");
            System.out.println("3. Update Song");
            System.out.println("4. Delete Song");
            System.out.println("5. Archive Song");
            System.out.println("6. Restore Song");
            System.out.println("7. Back");

            System.out.print("\nEnter your choice: ");

            int choice = input.nextInt();
            input.nextLine(); // Consume newline

            switch (choice) {

                case 1:

                    // Add a new song (CRUD - Create)
                    System.out.println("\n=== Add Song ===");
                    addSong();
                    
                    System.out.print("\nPress Enter to continue...");
                    input.nextLine();

                    break;

                case 2:

                    // View all songs (CRUD - Read)
                    System.out.println("\n=== View All Songs ===");
                    displaySongs();

                    System.out.print("\nPress Enter to continue...");
                    input.nextLine();

                    break;

                case 3:

                    // Update an existing song (CRUD - Update)
                    // TODO: Implement the update functionality
                    System.out.println("\n=== Update Song ===");
                    break;

                case 4:

                    // Delete a song (CRUD - Delete)
                    // TODO: Implement the delete functionality
                    System.out.println("\n=== Delete Song ===");
                    break;

                case 5:

                    // Archive a song (CRUD - Archive)
                    // TODO: Implement the archive functionality
                    System.out.println("\n=== Archive Song ===");
                    break;

                case 6:

                    // Restore a song (CRUD - Restore)
                    // TODO: Implement the restore functionality
                    System.out.println("\n=== Restore Song ===");
                    break;

                case 7:

                    running = false;
                    System.out.println("\nReturning to the Main Menu...");
                    break;

                default:

                    System.out.println("\nInvalid menu option.");

            }

        }

    }

    // Add a new song by prompting the user for details
    public void addSong() {
        // Prompt the user for song details
        System.out.print("Title: ");
        String title = input.nextLine();

        System.out.print("Song Length (HH:MM:SS): ");
        String songLength = input.nextLine();

        System.out.print("Genre: ");
        String genre = input.nextLine();

        System.out.print("Album ID: ");
        int albumId = input.nextInt();
        input.nextLine(); // Consume newline

        // Create a new Song object with the provided details
        Song song = new Song(title, songLength, genre, albumId);

        // Call the controller to create the song and display the result
        if (songController.createSong(song)) {
            System.out.println("\n" + song.getTitle() + " has been added successfully!");
        } else {
            System.out.println("Failed to add song.");
        }
    }

    // Display all active songs retrieved from the database
    public void displaySongs() {

        for (Song song : songController.listSongs()) {

            System.out.println(
                    song.getId()
                    + " | "
                    + song.getTitle()
            );

        }

    }

}
