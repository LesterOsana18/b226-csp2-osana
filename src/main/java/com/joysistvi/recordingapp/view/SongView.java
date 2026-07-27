package com.joysistvi.recordingapp.view;

import java.util.Scanner;
import java.util.List;

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
                    
                    System.out.print("Press Enter to continue...");
                    input.nextLine();

                    break;

                case 2:

                    // View all songs (CRUD - Read)
                    System.out.println("\n=== View All Songs ===");
                    displaySongs();

                    System.out.print("Press Enter to continue...");
                    input.nextLine();

                    break;

                case 3:

                    // Update an existing song (CRUD - Update)
                    System.out.println("\n=== Update Song ===");
                    updateSong();

                    System.out.print("Press Enter to continue...");
                    input.nextLine();

                    break;

                case 4:

                    // Delete a song (CRUD - Delete)
                    System.out.println("\n=== Delete Song ===");
                    deleteSong();

                    System.out.print("Press Enter to continue...");
                    input.nextLine();

                    break;

                case 5:

                    // Archive a song (CRUD - Archive)
                    System.out.println("\n=== Archive Song ===");
                    archiveSong();

                    System.out.print("Press Enter to continue...");
                    input.nextLine();

                    break;

                case 6:

                    // Restore a song (CRUD - Restore)
                    System.out.println("\n=== Restore Song ===");
                    restoreSong();

                    System.out.print("Press Enter to continue...");
                    input.nextLine();

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
            System.out.println("\n" + song.getTitle() + " has been added successfully!\n");
        } else {
            System.out.println("\nFailed to add song.\n");
        }
    }

    // Display all active songs retrieved from the database
    public void displaySongs() {

        List<Song> songs = songController.listSongs();

        if (songs.isEmpty()) {
            System.out.println("\nNo songs found.\n");
            return;
        }

        String border =
                "+------+------------------------------+----------+----------------+----------+";

        System.out.println(border);

        System.out.printf("| %-4s | %-28s | %-8s | %-14s | %-8s |%n",
                "ID", "Title", "Length", "Genre", "Album");

        System.out.println(border);

        for (Song song : songs) {

            System.out.printf("| %-4d | %-28s | %-8s | %-14s | %-8d |%n",
                    song.getId(),
                    song.getTitle(),
                    song.getSongLength(),
                    song.getGenre(),
                    song.getAlbumId());

        }

        System.out.println(border);

    }

    // Update an existing song in the database
    public void updateSong() {
        System.out.print("Song ID: ");
        int id = input.nextInt();
        input.nextLine();

        System.out.print("New Title: ");
        String title = input.nextLine();

        System.out.print("New Song Length (HH:MM:SS): ");
        String songLength = input.nextLine();

        System.out.print("New Genre: ");
        String genre = input.nextLine();

        System.out.print("New Album ID: ");
        int albumId = input.nextInt();
        input.nextLine();

        Song song = new Song(id, title, songLength, genre, albumId);

        if (songController.updateSong(song)) {
            System.out.println("\n" + song.getTitle() + " has been updated successfully!\n");
        } else {
            System.out.println("\nFailed to update song.\n");
        }
    }

    // Delete a song from the database permanently (not recommended, use archive instead)
    public void deleteSong() {
        System.out.print("Song ID: ");
        int id = input.nextInt();
        input.nextLine();

        System.out.println("Are you sure you want to proceed with the deletion?");
        System.out.println("WARNING: This action cannot be undone and will permanently remove the song from the database.");
        System.out.print("Press 'Y' to confirm or any other key to cancel: ");
        String confirmation = input.nextLine();

        if (confirmation.equalsIgnoreCase("Y")) {
            if (songController.deleteSong(id)) {
                System.out.println("\nSong deleted successfully!\n");
            } else {
                System.out.println("\nFailed to delete song.\n");
            }
        } else {
            System.out.println("\nDeletion cancelled.\n");
        }
    }

    // Archive a song by marking it as archived in the database
    public void archiveSong() {
        System.out.print("Song ID: ");
        int id = input.nextInt();
        input.nextLine();

        if (songController.archiveSong(id)) {
            System.out.println("\nSong archived successfully!\n");
        } else {
            System.out.println("\nFailed to archive song.\n");
        }
    }

    // Restore a song by marking it as active in the database
    public void restoreSong() {
        System.out.print("Song ID: ");
        int id = input.nextInt();
        input.nextLine();

        if (songController.restoreSong(id)) {
            System.out.println("\nSong restored successfully!\n");
        } else {
            System.out.println("\nFailed to restore song.\n");
        }
    }
}
