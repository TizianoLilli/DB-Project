package org.eschool.view;

import org.eschool.model.Lezione;
import org.eschool.utils.Input;
import org.eschool.utils.enums.StatoLezione;

import java.time.LocalDate;
import java.time.LocalTime;

public class InsegnanteView {

    public int showMenu() {

        System.out.print("""
                ---------------------------------
                1) Assign absence
                2) Produce Report
                3) Exit
                ---------------------------------
                """);

        return Input.read(1,3);
    }

    public int subscriberForm(){
        System.out.println("Insert subscriber ID");

        System.out.print("Subscriber ID: ");

        return Input.readInt();
    }

    public Lezione lessonForm(){
        System.out.println("Insert lesson information");

        System.out.print("Course ID: ");
        int course = Input.readInt();

        Input.readString(); //pulisco il buffer

        System.out.print("Date (yyyy-MM-dd): ");
        String tempDate = Input.readString();
        LocalDate date = LocalDate.parse(tempDate);

        System.out.print("Time (hh:mm): ");
        String tempTime = Input.readString();
        LocalTime time = LocalTime.parse(tempTime);

        return new Lezione(-1, course, date, time, StatoLezione.EROGATA, -1);
    }
}
