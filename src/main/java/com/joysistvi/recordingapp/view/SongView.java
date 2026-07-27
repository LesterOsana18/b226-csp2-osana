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

                    System.out.println("\n=== Add Song ===");
                    break;

                case 2:

                    System.out.println("\n=== View All Songs ===");
                    displaySongs();

                    System.out.print("\nPress Enter to continue...");
                    input.nextLine();

                    break;

                case 3:

                    System.out.println("\n=== Update Song ===");
                    break;

                case 4:

                    System.out.println("\n=== Delete Song ===");
                    break;

                case 5:

                    System.out.println("\n=== Archive Song ===");
                    break;

                case 6:

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
