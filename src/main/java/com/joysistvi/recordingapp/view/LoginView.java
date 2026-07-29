package com.joysistvi.recordingapp.view;

import java.util.Scanner;

import com.joysistvi.recordingapp.controller.UserController;
import com.joysistvi.recordingapp.model.User;

// View Class
// Handles user login and registration
public class LoginView {

    // Dependency Injection (Constructor Injection)
    private final UserController userController;

    // Scanner object for user input
    private final Scanner input;

    // Constructor
    public LoginView(UserController userController, Scanner scanner) {
        this.userController = userController;
        this.input = scanner;
    }

    // Display the Login Menu
    public boolean run() {

        while (true) {

            System.out.println("\n=========================================");
            System.out.println("  USER LOGIN FOR RECORDING STUDIO APP");
            System.out.println("=========================================");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("0. Exit");

            System.out.print("\nChoice: ");
            int choice = readMenuChoice();

            switch (choice) {

                case 1:

                    if (login()) {
                        return true;
                    }

                    break;

                case 2:

                    register();

                    break;

                case 0:

                    return false;

                default:

                    System.out.println("\nInvalid menu option.");

            }

        }

    }

    // ==========================================================
    // LOGIN
    // ==========================================================

    private boolean login() {

        System.out.println("\n=== User Login ===");

        System.out.print("Username: ");
        String username = input.nextLine().trim();

        System.out.print("Password: ");
        String password = input.nextLine();

        User user = userController.login(username, password);

        if (user != null) {

            System.out.println("\nWelcome, " + user.getUsername() + "!\n");
            return true;

        }

        System.out.println("\nInvalid username or password.");

        return false;

    }

    // ==========================================================
    // REGISTER
    // ==========================================================

    private void register() {

        System.out.println("\n=== User Registration ===");

        String username = readUsername();
        String password = readPassword();

        User user = new User(username, password);

        if (userController.createUser(user)) {

            System.out.println("\nRegistration successful!");

        } else {

            System.out.println("\nRegistration failed.");

        }

    }

    // ==========================================================
    // INPUT HELPERS
    // ==========================================================

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

    private String readUsername() {

        String username;

        do {

            System.out.print("Username: ");
            username = input.nextLine().trim();

            if (username.isEmpty()) {

                System.out.println("\nError: Username cannot be empty.\n");

            }

        } while (username.isEmpty());

        return username;

    }

    private String readPassword() {

        String password;

        do {

            System.out.print("Password: ");
            password = input.nextLine();

            if (password.trim().isEmpty()) {

                System.out.println("\nError: Password cannot be empty.\n");

            }

        } while (password.trim().isEmpty());

        return password;

    }

}
