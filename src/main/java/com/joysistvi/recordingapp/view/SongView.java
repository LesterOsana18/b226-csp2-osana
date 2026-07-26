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
    private final Scanner input = new Scanner(System.in);

    public SongView(SongController songController) {
        this.songController = songController;
    }

    // Display the dashboard menu
    public void dashboard() {

        boolean running = true;

        while (running) {

            System.out.println("\n==============================");
            System.out.println("     RECORDING APPLICATION");
            System.out.println("==============================");
            System.out.println("1. Add Song");
            System.out.println("2. View All Songs");
            System.out.println("3. Update Song");
            System.out.println("4. Delete Song");
            System.out.println("5. Archive Song");
            System.out.println("6. Restore Song");
            System.out.println("7. Exit");

            System.out.print("\nEnter your choice: ");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:

                    System.out.println("Add Song");
                    break;

                case 2:

                    displaySongs();

                    break;

                case 3:

                    System.out.println("Update Song");
                    break;

                case 4:

                    System.out.println("Delete Song");
                    break;

                case 5:

                    System.out.println("Archive Song");
                    break;

                case 6:

                    System.out.println("Restore Song");
                    break;

                case 7:

                    running = false;
                    System.out.println("\nThank you for using the Recording Application!");
                    break;

                default:

                    System.out.println("\nInvalid menu option.");

            }

        }

        input.close();

    }

    // Display all songs retrieved from the database
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
