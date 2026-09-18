package login;

import java.util.Scanner;

/**
 * Main.java
 *
 * Simple console (no GUI / no JOptionPane, per the brief) driver that
 * lets a user register an account and then log in, using the Login
 * class.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Registration ===");

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter username (must contain '_' and be <= 5 characters): ");
        String username = scanner.nextLine();

        System.out.print("Enter password (8+ chars, capital, number, special char): ");
        String password = scanner.nextLine();

        System.out.print("Enter SA cell phone number (e.g. +27838968976): ");
        String cellPhoneNumber = scanner.nextLine();

        Login login = new Login(firstName, lastName, username, password, cellPhoneNumber);
        System.out.println(login.registerUser());

        System.out.println();
        System.out.println("=== Login ===");

        System.out.print("Enter username: ");
        String loginUsername = scanner.nextLine();

        System.out.print("Enter password: ");
        String loginPassword = scanner.nextLine();

        boolean success = login.loginUser(loginUsername, loginPassword);
        System.out.println(login.returnLoginStatus(success));

        scanner.close();
    }
}
