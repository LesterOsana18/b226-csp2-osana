package com.joysistvi.recordingapp.view;

import java.util.List;
import java.util.Scanner;
import java.time.Year;

import com.joysistvi.recordingapp.controller.AlbumController;
import com.joysistvi.recordingapp.model.Album;

// View Class
// Handles user interaction for album management
public class AlbumView {

    // Dependency Injection (Constructor Injection)
    private final AlbumController albumController;

    // Scanner object for user input
    private final Scanner input;

    // Table border used when displaying albums
    private static final String ALBUM_TABLE_BORDER =
            "+------+------------------------------+------------+------------+";

    // Minimum valid release year
    private static final int MIN_YEAR = 1900;

    // Constructor Injection
    public AlbumView(AlbumController albumController, Scanner scanner) {
        this.albumController = albumController;
        this.input = scanner;
    }

    // Display the Album Management Dashboard
    public void run() {

        boolean running = true;

        while (running) {

            System.out.println("\n==============================");
            System.out.println("      ALBUM MANAGEMENT");
            System.out.println("==============================");
            System.out.println("1. Add Album");
            System.out.println("2. View Albums");
            System.out.println("3. Update Album");
            System.out.println("4. Delete Album");
            System.out.println("5. Back");

            System.out.print("\nEnter your choice: ");
            int choice = readMenuChoice();

            switch (choice) {

                case 1:

                    System.out.println("\n=== Add Album ===");
                    addAlbum();

                    System.out.print("Press Enter to continue...");
                    input.nextLine();

                    break;

                case 2:

                    System.out.println("\n=== View Albums ===");
                    displayAlbums();

                    System.out.print("\nPress Enter to continue...");
                    input.nextLine();

                    break;

                case 3:

                    System.out.println("\n=== Update Album ===");

                    displayAlbums();
                    updateAlbum();

                    System.out.print("\nPress Enter to continue...");
                    input.nextLine();

                    break;

                case 4:

                    System.out.println("\n=== Delete Album ===");

                    displayAlbums();
                    deleteAlbum();

                    System.out.print("\nPress Enter to continue...");
                    input.nextLine();

                    break;

                case 5:

                    running = false;
                    System.out.println("\nReturning to the Main Menu...");
                    break;

                default:

                    System.out.println("\nInvalid menu option.");

            }

        }

    }

    // ==========================================================
    // CRUD METHODS
    // ==========================================================

    // Add a new album
    private void addAlbum() {

        String albumName = readAlbumName();
        int yearReleased = readYearReleased();
        int artistId = readArtistId();

        Album album = new Album(albumName, yearReleased, artistId);

        if (albumController.createAlbum(album)) {
            System.out.println("\n\"" + album.getAlbumName() + "\" has been added successfully!\n");
        } else {
            System.out.println("\nFailed to add album.\n");
        }

    }

    // Display all active albums
    private void displayAlbums() {

        List<Album> albums = albumController.listAlbums();

        if (albums.isEmpty()) {
            System.out.println("\nNo albums found.\n");
            return;
        }

        displayAlbumTable(albums);

    }

    // Display albums in a formatted table
    private void displayAlbumTable(List<Album> albums) {

        System.out.println(ALBUM_TABLE_BORDER);

        System.out.printf("| %-4s | %-28s | %-10s | %-10s |%n",
                "ID",
                "Album Name",
                "Year",
                "Artist ID");

        System.out.println(ALBUM_TABLE_BORDER);

        for (Album album : albums) {

            System.out.printf("| %-4d | %-28s | %-10d | %-10d |%n",
                    album.getId(),
                    album.getAlbumName(),
                    album.getYearReleased(),
                    album.getArtistId());

        }

        System.out.println(ALBUM_TABLE_BORDER);
        System.out.println("Total albums: " + albums.size());

    }

    // Update an existing album
    private void updateAlbum() {

        int id = readAlbumId();

        String albumName = readAlbumName();
        int yearReleased = readYearReleased();
        int artistId = readArtistId();

        Album album = new Album(id, albumName, yearReleased, artistId);

        if (albumController.updateAlbum(album)) {
            System.out.println("\n\"" + album.getAlbumName() + "\" has been updated successfully!\n");
        } else {
            System.out.println("\nFailed to update album.\n");
        }

    }

    // Delete an album permanently
    private void deleteAlbum() {

        int id = readAlbumId();

        System.out.println("\nAre you sure you want to proceed with the deletion?");
        System.out.println("WARNING: This action cannot be undone and will permanently remove the album from the database.");
        System.out.print("Press 'Y' to confirm or any other key to cancel: ");

        String confirmation = input.nextLine();

        if (confirmation.equalsIgnoreCase("Y")) {

            if (albumController.deleteAlbum(id)) {
                System.out.println("\nAlbum deleted successfully!\n");
            } else {
                System.out.println("\nFailed to delete album.\n");
            }

        } else {

            System.out.println("\nDeletion cancelled.\n");

        }

    }

    // ==========================================================
    // INPUT HELPERS
    // ==========================================================

    // Read and validate a menu option
    private int readMenuChoice() {

        while (!input.hasNextInt()) {

            System.out.println("\nError: Please enter a valid menu number.\n");
            input.nextLine();

            System.out.print("Enter your choice: ");

        }

        int choice = input.nextInt();
        input.nextLine();

        return choice;

    }

    // Read and validate album ID
    private int readAlbumId() {

        while (true) {

            System.out.print("Album ID: ");

            while (!input.hasNextInt()) {

                System.out.println("\nError: Album ID must be a number.\n");
                input.nextLine();

                System.out.print("Album ID: ");

            }

            int id = input.nextInt();
            input.nextLine();

            if (id > 0) {
                return id;
            }

            System.out.println("\nError: Album ID must be greater than zero.\n");

        }

    }

    // Read and validate album name
    private String readAlbumName() {

        String albumName;

        do {

            System.out.print("Album Name: ");
            albumName = input.nextLine().trim();

            if (albumName.isEmpty()) {
                System.out.println("\nError: Album name cannot be empty.\n");
            }

        } while (albumName.isEmpty());

        return albumName;

    }

    // Read and validate release year
    private int readYearReleased() {

        int currentYear = Year.now().getValue();

        while (true) {

            System.out.print("Year Released: ");

            while (!input.hasNextInt()) {

                System.out.println("\nError: Year must be numeric.\n");
                input.nextLine();

                System.out.print("Year Released: ");

            }

            int year = input.nextInt();
            input.nextLine();

            if (year >= MIN_YEAR && year <= currentYear) {
                return year;
            }

            System.out.printf("\nError: Year must be between %d and %d.%n%n",
                    MIN_YEAR,
                    currentYear);

        }

    }

    // Read and validate artist ID
    private int readArtistId() {

        while (true) {

            System.out.print("Artist ID: ");

            while (!input.hasNextInt()) {

                System.out.println("\nError: Artist ID must be a number.\n");
                input.nextLine();

                System.out.print("Artist ID: ");

            }

            int artistId = input.nextInt();
            input.nextLine();

            if (artistId > 0) {
                return artistId;
            }

            System.out.println("\nError: Artist ID must be greater than zero.\n");

        }

    }

}
