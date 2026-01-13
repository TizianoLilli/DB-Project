package org.eschool.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
    private static Scanner scanner = new Scanner(System.in);

    public static int read(int min, int max){

        boolean choice = false;
        int value = -1;

        while (!choice){
            try{
                System.out.print("Make your choice: ");
                value = scanner.nextInt();

                if (value>=min && value<=max){
                    choice = true;
                } else System.out.println("Invalid option!! Insert a valid number...");

            } catch (InputMismatchException e) {
                System.out.println("Invalid option!! Insert a valid number...");
                scanner.nextLine(); //pulisce il buffer
            }
        }

        return value;
    }

    public static int readInt(){
        return scanner.nextInt();
    }

    public static String readString(){
        return scanner.nextLine();
    } //anche per pulire il buffer

    public static String readStringTrimmed(){
        return scanner.nextLine().trim();
    }
}
