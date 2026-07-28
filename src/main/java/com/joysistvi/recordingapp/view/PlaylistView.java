package com.joysistvi.recordingapp.view;

import java.util.List;
import java.util.Scanner;

import com.joysistvi.recordingapp.controller.PlaylistController;
import com.joysistvi.recordingapp.model.Playlist;

// View Class
// Handles user interaction for playlist management
public class PlaylistView {

    // Dependency Injection (Constructor Injection)
    private final PlaylistController playlistController;

    // Scanner object for user input
    private final Scanner input;

    // Table border used when displaying playlists
    private static final String PLAYLIST_TABLE_BORDER =
            "+------+------------------------------+----------+";

    // Constructor Injection
    public PlaylistView(PlaylistController playlistController, Scanner scanner) {
        this.playlistController = playlistController;
        this.input = scanner;
    }

    // Display the Playlist Management Dashboard
    public void run() {

        boolean running = true;

        while (running) {

            System.out.println("\n===============================");
            System.out.println("      PLAYLIST MANAGEMENT");
            System.out.println("===============================");
            System.out.println("1. Add Playlist");
            System.out.println("2. View Playlists");
            System.out.println("3. Update Playlist");
            System.out.println("4. Delete Playlist");
            System.out.println("5. Back");

            System.out.print("\nEnter your choice: ");
            int choice = readMenuChoice();

            switch (choice) {

                case 1:

                    System.out.println("\n=== Add Playlist ===");
                    addPlaylist();

                    System.out.print("Press Enter to continue...");
                    input.nextLine();

                    break;

                case 2:

                    System.out.println("\n=== View Playlists ===");
                    displayPlaylists();

                    System.out.print("Press Enter to continue...");
                    input.nextLine();

                    break;

                case 3:

                    System.out.println("\n=== Update Playlist ===");

                    displayPlaylists();

                    updatePlaylist();

                    System.out.print("Press Enter to continue...");
                    input.nextLine();

                    break;

                case 4:

                    System.out.println("\n=== Delete Playlist ===");

                    displayPlaylists();

                    deletePlaylist();

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

    // ==========================================================
    // CRUD METHODS
    // ==========================================================

    // Add a new playlist
    private void addPlaylist() {

        String playlistName = readPlaylistName();
        int userId = readUserId();

        Playlist playlist = new Playlist(playlistName, userId);

        if (playlistController.createPlaylist(playlist)) {

            System.out.println("\n\"" + playlist.getPlaylistName()
                    + "\" has been added successfully!\n");

        } else {

            System.out.println("\nFailed to add playlist.\n");

        }

    }

    // Display all playlists
    private void displayPlaylists() {

        List<Playlist> playlists = playlistController.listPlaylists();

        displayPlaylistsTable(playlists);

    }

    // Display playlists in a formatted table
    private void displayPlaylistsTable(List<Playlist> playlists) {

        if (playlists.isEmpty()) {

            System.out.println("\nNo playlists found.\n");
            return;

        }

        System.out.println(PLAYLIST_TABLE_BORDER);

        System.out.printf("| %-4s | %-28s | %-8s |%n",
                "ID",
                "Playlist Name",
                "User ID");

        System.out.println(PLAYLIST_TABLE_BORDER);

        for (Playlist playlist : playlists) {

            System.out.printf("| %-4d | %-28s | %-8d |%n",
                    playlist.getId(),
                    playlist.getPlaylistName(),
                    playlist.getUserId());

        }

        System.out.println(PLAYLIST_TABLE_BORDER);
        System.out.println("Total playlists: " + playlists.size());

    }

    // Update an existing playlist
    private void updatePlaylist() {

        int id = readPlaylistId();

        String playlistName = readPlaylistName();
        int userId = readUserId();

        Playlist playlist = new Playlist(id, playlistName, userId);

        if (playlistController.updatePlaylist(playlist)) {

            System.out.println("\n\"" + playlist.getPlaylistName()
                    + "\" has been updated successfully!\n");

        } else {

            System.out.println("\nFailed to update playlist.\n");

        }

    }

    // Delete a playlist permanently
    private void deletePlaylist() {

        int id = readPlaylistId();

        System.out.println("Are you sure you want to proceed with the deletion?");
        System.out.println("WARNING: This action cannot be undone and will permanently remove the playlist from the database.");
        System.out.print("Press 'Y' to confirm or any other key to cancel: ");

        String confirmation = input.nextLine();

        if (confirmation.equalsIgnoreCase("Y")) {

            if (playlistController.deletePlaylist(id)) {

                System.out.println("\nPlaylist deleted successfully!\n");

            } else {

                System.out.println("\nFailed to delete playlist.\n");

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

    // Read and validate playlist name
    private String readPlaylistName() {

        String playlistName;

        do {

            System.out.print("Playlist Name: ");
            playlistName = input.nextLine().trim();

            if (playlistName.isEmpty()) {
                System.out.println("\nError: Playlist name cannot be empty.\n");
            }

        } while (playlistName.isEmpty());

        return playlistName;

    }

    // Read and validate playlist ID
    private int readPlaylistId() {

        while (true) {

            System.out.print("Playlist ID: ");

            while (!input.hasNextInt()) {

                System.out.println("\nError: Playlist ID must be a number.\n");
                input.nextLine();

                System.out.print("Playlist ID: ");

            }

            int playlistId = input.nextInt();
            input.nextLine(); // Consume newline

            if (playlistId > 0) {
                return playlistId;
            }

            System.out.println("\nError: Playlist ID must be greater than zero.\n");

        }

    }

    // Read and validate user ID
    private int readUserId() {

        while (true) {

            System.out.print("User ID: ");

            while (!input.hasNextInt()) {

                System.out.println("\nError: User ID must be a number.\n");
                input.nextLine();

                System.out.print("User ID: ");

            }

            int userId = input.nextInt();
            input.nextLine();

            if (userId > 0) {
                return userId;
            }

            System.out.println("\nError: User ID must be greater than zero.\n");

        }

    }

}
