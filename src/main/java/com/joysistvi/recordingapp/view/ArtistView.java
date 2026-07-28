package com.joysistvi.recordingapp.view;

import java.util.List;
import java.util.Scanner;

import com.joysistvi.recordingapp.controller.ArtistController;
import com.joysistvi.recordingapp.model.Artist;

// View Class
// Handles user interaction for artist management
public class ArtistView {

    // Depenency Injection (Constructor Injection)
    private final ArtistController artistController;

    // Scanner object for user input
    private final Scanner input;

    // Table border used when displaying artists
    private static final String TABLE_BORDER =
                "+------+------------------------------+";
    
    // Constructor Injection
    public ArtistView( 
            ArtistController artistController,
            Scanner scanner) {

        this.artistController = artistController;
        this.input = scanner;

    }

    // Display the Artist Management Dashboard
    public void run() {
        
        boolean running = true;

        while (running) {

            System.out.println("\n===============================");
            System.out.println("        ARTIST MANAGEMENT      ");
            System.out.println("===============================");
            System.out.println("1. Add Artist");
            System.out.println("2. View Artists");
            System.out.println("3. Update Artist");
            System.out.println("4. Delete Artist");
            System.out.println("5. Back");

            System.out.print("\nEnter your choice: ");
            int choice = readMenuChoice();

            switch (choice) {

                case 1:

                    // Add a new artist (CRUD - Create)
                    System.out.println("\n=== Add Artist ===");
                    addArtist();
                    
                    System.out.print("Press Enter to continue...");
                    input.nextLine();

                    break;

                case 2:

                    // View all artists (CRUD - Read)
                    System.out.println("\n=== View Artists ===");
                    displayArtists();

                    System.out.print("Press Enter to continue...");
                    input.nextLine();

                    break;

                case 3:

                    // Update an existing artist (CRUD - Update)
                    System.out.println("\n=== Update Artist ===");

                    displayArtists();

                    updateArtist();

                    System.out.print("Press Enter to continue...");
                    input.nextLine();

                    break;

                case 4:

                    // Delete an artist (CRUD - Delete)
                    System.out.println("\n=== Delete Artist ===");

                    displayArtists();

                    deleteArtist();

                    System.out.print("Press Enter to continue...");
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

    // Add a new artist to the database
    private void addArtist() {

        String artistName = readArtistName();

        Artist artist = new Artist(artistName);

        if (artistController.createArtist(artist)) {
            System.out.println("\n\"" + artist.getArtistName() + "\" has been added successfully!\n");
        } else {
            System.out.println("Failed to add artist.\n");
        }

    }

    // Display all artists retrieved from the database
    private void displayArtists() {

        List<Artist> artists = artistController.listArtists();
        displayArtistsTable(artists);

    }

    // Update an existing artist's information
    private void updateArtist() {

        int id = readArtistId();

        String artistName = readArtistName();

        Artist artist = new Artist(id, artistName);

        if (artistController.updateArtist(artist)) {
            System.out.println("\n" + artist.getArtistName() + " has been updated successfully!\n");
        } else {
            System.out.println("\nFailed to update artist.\n");
        }
    }

    // Delete an artist from the database permanently
    private void deleteArtist() {
        int id = readArtistId();

        System.out.println("Are you sure you want to proceed with the deletion?");
        System.out.println("WARNING: This action cannot be undone and will permanently remove the artist from the database.");
        System.out.print("Press 'Y' to confirm or any other key to cancel: ");
        String confirmation = input.nextLine();

        if (confirmation.equalsIgnoreCase("Y")) {
            if (artistController.deleteArtist(id)) {
                System.out.println("\nArtist deleted successfully!\n");
            } else {
                System.out.println("\nFailed to delete artist.\n");
            }
        } else {
            System.out.println("\nDeletion cancelled.\n");
        }
    }

    // Read and validate the artist ID
    private int readArtistId() {
        int artistId;

        while (true) {
            System.out.print("Artist ID: ");

            while (!input.hasNextInt()) {

                System.out.println("\nError: Artist ID must be a number.\n");
                input.nextLine();

                System.out.print("Artist ID: ");

            }

            artistId = input.nextInt();
            input.nextLine(); // Consume newline

            if (artistId > 0) {
                return artistId;
            }

            System.out.println("\nError: Artist ID must be greater than zero.\n");
        }

    }

    // Read and validate the artist name
    private String readArtistName() {

        String artistName;

        do {

            System.out.print("Artist Name: ");
            artistName = input.nextLine().trim();

            if (artistName.isEmpty()) {
                System.out.println("\nError: Artist name cannot be empty.\n");
            }

        } while (artistName.isEmpty());

        return artistName;

    }

    // Display the given list of artists in a formatted table
    private void displayArtistsTable(List<Artist> artists) {

        if (artists.isEmpty()) {
            System.out.println("\nNo artists found.\n");
            return;
        }

        System.out.println(TABLE_BORDER);

        System.out.printf("| %-4s | %-28s |%n",
                "ID",
                "Artist Name");

        System.out.println(TABLE_BORDER);

        for (Artist artist : artists) {

            System.out.printf("| %-4d | %-28s |%n",
                    artist.getId(),
                    artist.getArtistName());

        }

        System.out.println(TABLE_BORDER);
        System.out.println("Total artists: " + artists.size());
        System.out.println(TABLE_BORDER);

    }
}
