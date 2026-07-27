package com.joysistvi.recordingapp.view;

import java.util.List;
import java.util.Scanner;

// Importing necessary classes from the controller and model packages
import com.joysistvi.recordingapp.controller.AlbumController;
import com.joysistvi.recordingapp.controller.SongController;
import com.joysistvi.recordingapp.model.Album;
import com.joysistvi.recordingapp.model.Song;

// View Class
// Handles user interaction for song management
public class SongView {

    // Dependency Injection (Constructor Injection)
    private final SongController songController;
    private final AlbumController albumController;

    // Scanner object for user input
    private final Scanner input;

    // Table border used when displaying songs
    private static final String TABLE_BORDER =
                "+------+------------------------------+----------+----------------+----------+";

    // Constructor Injection
    public SongView(
            SongController songController,
            AlbumController albumController,
            Scanner scanner) {

        this.songController = songController;
        this.albumController = albumController;
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
            int choice = readMenuChoice();

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

    // Add a new song to the database
    private void addSong() {

        String title = readSongTitle();
        String songLength = readSongLength();
        String genre = readGenre();

        // Display available albums for selection
        displayAlbums(); 
        int albumId = readAlbumId();

        Song song = new Song(title, songLength, genre, albumId);

        if (songController.createSong(song)) {
            System.out.println("\n" + song.getTitle() + " has been added successfully!\n");
        } else {
            System.out.println("Failed to add song.\n");
        }

    }

    // Display all active songs retrieved from the database
    private void displaySongs() {

        List<Song> songs = songController.listSongs();

        if (songs.isEmpty()) {
            System.out.println("\nNo songs found.\n");
            return;
        }

        System.out.println(TABLE_BORDER);

        System.out.printf("| %-4s | %-28s | %-8s | %-14s | %-8s |%n",
                "ID", "Title", "Length", "Genre", "Album");

        System.out.println(TABLE_BORDER);

        for (Song song : songs) {

            System.out.printf("| %-4d | %-28s | %-8s | %-14s | %-8d |%n",
                    song.getId(),
                    song.getTitle(),
                    song.getSongLength(),
                    song.getGenre(),
                    song.getAlbumId());

        }

        System.out.println(TABLE_BORDER);

        System.out.printf("| %-63s | %-8d |%n",
                "Total Songs",
                songs.size());

        System.out.println(TABLE_BORDER);

    }

    // Update an existing song in the database
    private void updateSong() {
        int id = readSongId();

        String title = readSongTitle();
        String songLength = readSongLength();
        String genre = readGenre();
        int albumId = readAlbumId();

        Song song = new Song(id, title, songLength, genre, albumId);

        if (songController.updateSong(song)) {
            System.out.println("\n" + song.getTitle() + " has been updated successfully!\n");
        } else {
            System.out.println("\nFailed to update song.\n");
        }
    }

    // Delete a song from the database permanently
    private void deleteSong() {
        int id = readSongId();

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

    // Archive a song by marking it as inactive in the database
    private void archiveSong() {
        int id = readSongId();

        if (songController.archiveSong(id)) {
            System.out.println("\nSong archived successfully!\n");
        } else {
            System.out.println("\nFailed to archive song.\n");
        }
    }

    // Restore a song by marking it as active in the database
    private void restoreSong() {
        int id = readSongId();

        if (songController.restoreSong(id)) {
            System.out.println("\nSong restored successfully!\n");
        } else {
            System.out.println("\nFailed to restore song.\n");
        }
    }

    // Read and validate a menu option
    private int readMenuChoice() {

        while (!input.hasNextInt()) {
            
            System.out.println("\nError: Please enter a valid menu number.\n");
            input.nextLine();

            System.out.print("Enter your choice: ");
        }

        int choice = input.nextInt();
        input.nextLine(); // Consume newline

        return choice;
    }

    // Read and validate the song ID
    private int readSongId() {
        int songId;

        while (true) {
            System.out.print("Song ID: ");

            while (!input.hasNextInt()) {

                System.out.println("\nError: Song ID must be a number.\n");
                input.nextLine();

                System.out.print("Song ID: ");

            }

            songId = input.nextInt();
            input.nextLine(); // Consume newline

            if (songId > 0) {
                return songId;
            }

            System.out.println("\nError: Song ID must be greater than zero.\n");
        }

    }

    // Read and validate the song title
    private String readSongTitle() {

        String title;

        do {

            System.out.print("Title: ");
            title = input.nextLine().trim();

            if (title.isEmpty()) {
                System.out.println("\nError: Song title cannot be empty.\n");
            }

        } while (title.isEmpty());

        return title;

    }

    // Read and validate the song length
    private String readSongLength() {

        String songLength;

        do {

            System.out.print("Song Length (HH:MM:SS): ");
            songLength = input.nextLine().trim();

            if (!songLength.matches("\\d{2}:\\d{2}:\\d{2}")) {
                System.out.println("\nError: Song length must follow the format HH:MM:SS.\n");
            }

        } while (!songLength.matches("\\d{2}:\\d{2}:\\d{2}"));

        return songLength;

    }

    // Read and validate the genre
    private String readGenre() {

        String genre;

        do {

            System.out.print("Genre: ");
            genre = input.nextLine().trim();

            if (genre.isEmpty()) {
                System.out.println("\nError: Genre cannot be empty.\n");
            }

        } while (genre.isEmpty());

        return genre;

    }

    // Read and validate the album ID
    private int readAlbumId() {

        int albumId;

        while (true) {

            System.out.print("Album ID: ");

            while (!input.hasNextInt()) {
                System.out.println("\nError: Album ID must be a number.\n");
                input.next();
                System.out.print("Album ID: ");
            }

            albumId = input.nextInt();
            input.nextLine();

            if (albumId > 0) {
                return albumId;
            }

            System.out.println("\nError: Album ID must be greater than zero.\n");

        }

    }

    // Display all available albums for selection
    private void displayAlbums() {

        List<Album> albums = albumController.listAlbums();

        if (albums.isEmpty()) {
            System.out.println("\nNo albums found.\n");
            return;
        }

        String border =
                "+------+------------------------------+------------------------------+";

        System.out.println("\nAvailable Albums");

        System.out.println(border);

        System.out.printf("| %-4s | %-28s | %-28s |%n",
                "ID",
                "Album",
                "Artist");

        System.out.println(border);

        for (Album album : albums) {

            System.out.printf("| %-4d | %-28s | %-28s |%n",
                    album.getId(),
                    album.getAlbumName(),
                    album.getArtistName());

        }

        System.out.println(border);

    }
}
