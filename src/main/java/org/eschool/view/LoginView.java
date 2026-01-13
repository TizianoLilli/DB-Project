package org.eschool.view;

import org.eschool.model.Account;
import org.eschool.utils.Input;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LoginView {

    public int ShowMenu() {

        System.out.print("""
                ---------------------------------
                1) Log in
                2) Registration
                3) Exit
                ---------------------------------
                """);

        return Input.read(1,3);
    }

    public Account auth(){

        Input.readString();
        System.out.print("Username: ");
        String username = Input.readStringTrimmed();

        System.out.print("Password: ");
        String password = Input.readStringTrimmed();

        return new Account(-1, username, password, null);
    }

}
