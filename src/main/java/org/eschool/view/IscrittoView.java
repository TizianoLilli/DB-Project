package org.eschool.view;

import java.io.IOException;
import java.util.Scanner;

public class IscrittoView {

    public int ShowMenu() throws IOException {

        System.out.print("""
                1) Subscribe to a new course
                2) Delete subscription to a course
                3) Exit""");

        Scanner scanner = new Scanner(System.in);
        boolean choice = false;
        int value = -1;
        
        while (!choice){
                choice = true;
                value = scanner.nextInt();

                if (value<1 || value>3){
                    System.out.print("Invalid option!! Insert a valid number...\n");
                    choice = false;
                }
        }

        return value;
    }
}
