package com.joysistvi.recordingapp.view;

import java.util.List;
import java.util.Scanner;

import com.joysistvi.recordingapp.controller.UserController;
import com.joysistvi.recordingapp.model.User;

// View Class
// Handles user interaction for user management
public class UserView {

    // Dependency Injection (Constructor Injection)
    private final UserController userController;

    // Scanner object for user input
    private final Scanner input;

    // Table border used when displaying users
    private static final String USER_TABLE_BORDER =
            "+------+------------------------------+------------------------------+";

    // Constructor Injection
    public UserView(UserController userController, Scanner scanner) {
        this.userController = userController;
        this.input = scanner;
    }

    // Display the User Management Dashboard
    public void run() {

        boolean running = true;

        while (running) {

            System.out.println("\n===============================");
            System.out.println("         USER MANAGEMENT");
            System.out.println("===============================");
            System.out.println("1. Add User");
            System.out.println("2. View Users");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. Back");

            System.out.print("\nEnter your choice: ");
            int choice = readMenuChoice();

            switch (choice) {

                case 1:

                    System.out.println("\n=== Add User ===");
                    addUser();

                    System.out.print("Press Enter to continue...");
                    input.nextLine();
                    break;

                case 2:

                    System.out.println("\n=== View Users ===");
                    displayUsers();

                    System.out.print("Press Enter to continue...");
                    input.nextLine();
                    break;

                case 3:

                    System.out.println("\n=== Update User ===");

                    displayUsers();

                    updateUser();

                    System.out.print("Press Enter to continue...");
                    input.nextLine();
                    break;

                case 4:

                    System.out.println("\n=== Delete User ===");

                    displayUsers();

                    deleteUser();

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

    // Add a new user
    private void addUser() {

        String username = readUsername();
        String password = readPassword();

        User user = new User(username, password);

        if (userController.createUser(user)) {

            System.out.println("\n" + user.getUsername()
                    + " has been added successfully!\n");

        } else {

            System.out.println("\nFailed to add user.\n");

        }

    }

    // Display all users
    private void displayUsers() {

        List<User> users = userController.listUsers();

        displayUsersTable(users);

    }

    // Display users in a formatted table
    private void displayUsersTable(List<User> users) {

        if (users.isEmpty()) {

            System.out.println("\nNo users found.\n");
            return;

        }

        System.out.println(USER_TABLE_BORDER);

        System.out.printf("| %-4s | %-28s | %-28s |%n",
                "ID",
                "Username",
                "Password");

        System.out.println(USER_TABLE_BORDER);

        for (User user : users) {

            System.out.printf("| %-4d | %-28s | %-28s |%n",
                user.getId(),
                user.getUsername(),
                "********");

        }

        System.out.println(USER_TABLE_BORDER);
        System.out.println("Total users: " + users.size());

    }

    // Update an existing user
    private void updateUser() {

        int id = readUserId();

        String username = readUsername();
        String password = readPassword();

        User user = new User(id, username, password);

        if (userController.updateUser(user)) {

            System.out.println("\n" + user.getUsername()
                    + " has been updated successfully!\n");

        } else {

            System.out.println("\nFailed to update user.\n");

        }

    }

    // Delete a user permanently
    private void deleteUser() {

        int id = readUserId();

        System.out.println("Are you sure you want to proceed with the deletion?");
        System.out.println("WARNING: This action cannot be undone and will permanently remove the user from the database.");
        System.out.print("Press 'Y' to confirm or any other key to cancel: ");

        String confirmation = input.nextLine();

        if (confirmation.equalsIgnoreCase("Y")) {

            if (userController.deleteUser(id)) {

                System.out.println("\nUser deleted successfully!\n");

            } else {

                System.out.println("\nFailed to delete user.\n");

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

    // Read and validate the user ID
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

    // Read and validate the username
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

    // Read and validate the password
    private String readPassword() {

        String password;

        do {

            System.out.print("Password: ");
            password = input.nextLine().trim();

            if (password.isEmpty()) {
                System.out.println("\nError: Password cannot be empty.\n");
            }

        } while (password.isEmpty());

        return password;

    }

}
