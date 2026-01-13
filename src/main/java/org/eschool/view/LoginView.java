package org.eschool.view;

import org.eschool.model.Account;
import org.eschool.model.Iscritto;
import org.eschool.utils.Input;

import java.io.IOException;
import java.time.LocalDate;
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

    public Iscritto register(){ //per registrare un nuovo iscritto

        Input.readString();

        //dati iscritto
        System.out.print("CF: ");
        String cf = Input.readStringTrimmed();

        System.out.print("Name: ");
        String name = Input.readString();

        System.out.print("Surname: ");
        String surname = Input.readString();

        System.out.print("Date of birth (yyyy-MM-dd): ");
        LocalDate date = LocalDate.parse(Input.readString());

        System.out.print("Address: ");
        String address = Input.readString();

        System.out.print("Delivery (e-mail address or landline number or mobile number): ");
        String delivery = Input.readString();

        return new Iscritto(-1, cf, name, surname, date, address, delivery);

    }

}
