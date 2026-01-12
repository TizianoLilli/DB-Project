package org.eschool.view;

import org.eschool.model.Insegnante;
import org.eschool.model.Livello;
import org.eschool.utils.Input;

import java.util.List;

public class AdminView {

    public int showMenu() {

        System.out.print("""
                1) Insert a new course
                2) Insert a new teacher
                3) Exit
                """);

        return Input.read(1,3);
    }

    public void showLivelli(List<Livello> livelli){
        System.out.println("Here are the available levels:");
        System.out.println("Name | Book | Test needed");

        for (Livello l : livelli) {
            System.out.printf(
                    "%s | %s | %s%n",
                    l.getNome(),
                    l.getLibro(),
                    l.getEsame()
            );
        }
    }

    public String courseForm(List<Livello> lvls){
        showLivelli(lvls);

        System.out.print("Select a level to insert a new course (insert the name): ");
        return Input.readString();
    }

    public Insegnante teacherForm(){

        System.out.println("Insert teacher information");

        Input.readString();
        System.out.print("Name: ");
        String name = Input.readString();

        System.out.print("Surname: ");
        String surname = Input.readString();

        System.out.print("Address: ");
        String address = Input.readString();

        System.out.print("Country: ");
        String country = Input.readString();

        return new Insegnante(-1, name, surname, address, country);
    }

}
