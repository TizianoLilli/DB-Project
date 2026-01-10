package org.eschool.view;

import org.eschool.model.Account;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LoginView {
    Scanner scanner = new Scanner(System.in);

    public int ShowMenu() throws IOException {

        System.out.print("""
                1) Log in
                2) Registration
                3) Exit
                """);

        boolean choice = false;
        int value = -1;

        while (!choice) {
            try {
                System.out.print("Make your choice: ");
                value = scanner.nextInt();

                if (value > 0 && value < 4) {
                    choice = true;
                } else System.out.print("Invalid option!! Insert a valid number...\n");

            } catch (InputMismatchException e) {
                System.out.print("Invalid option!! Insert a valid number...\n");
                scanner.nextLine(); //pulisce il buffer
            }

        }

        return value;
    }

    public Account auth(){

        scanner.nextLine();
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Password: ");
        String password = scanner.nextLine().trim();

        return new Account(-1, username, password, null);
    }
}
