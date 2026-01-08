package org.eschool;

import java.util.Scanner;

public class Main {
    static void main() {

        Scanner scanner = new Scanner(System.in);

        try {

            System.out.print("Hello and Welcome to ILearn!\n");
            System.out.print("""
                    1) Log in 
                    2) Registration
                    """);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
