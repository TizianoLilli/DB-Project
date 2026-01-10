package org.eschool.view;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class IscrittoView {

    public int ShowMenu() throws IOException {

        System.out.print("""
                1) Subscribe to a new course
                2) Delete subscription to a course
                3) Exit
                """);

        Scanner scanner = new Scanner(System.in);
        boolean choice = false;
        int value = -1;
        
        while (!choice){
                try{
                    System.out.print("Make your choice: ");
                    value = scanner.nextInt();

                    if (value>0 && value<4){
                        choice = true;
                    } else System.out.print("Invalid option!! Insert a valid number...\n");

                } catch (InputMismatchException e) {
                    System.out.print("Invalid option!! Insert a valid number...\n");
                    scanner.nextLine(); //pulisce il buffer
                }
        }

        return value;
    }
}
