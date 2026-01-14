package org.eschool.view;

import org.eschool.model.Corso;
import org.eschool.model.Insegnante;
import org.eschool.model.Livello;
import org.eschool.utils.Input;

import java.util.List;

public class AdminView {

    public int showMenu() {

        System.out.print("""
                ---------------------------------
                1) Insert new course
                2) Insert new teacher
                3) Assign teacher to a course
                4) Produce Report
                5) Exit
                ---------------------------------
                """);

        return Input.read(1,5);
    }

    public void showLevels(List<Livello> livelli){
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
        showLevels(lvls);

        System.out.print("Select a level to insert a new course (insert the name): ");
        Input.readString(); //pulisco il buffer

        return Input.readString();
    }

    public Insegnante teacherForm(){

        System.out.println("Insert teacher information");

        Input.readString(); //pulisco il buffer
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

    public int referenceToCourse(List<Corso> c){
        showCourses(c);

        System.out.print("Select a course (insert the numeric code): ");
        Input.readString(); //pulisco il buffer

        return Input.readInt();
    }

    public int referenceToTeacher(List<Insegnante> i){
        showTeachers(i);

        System.out.print("Select a teacher (insert the numeric code): ");
        Input.readString(); //pulisco il buffer

        return Input.readInt();
    }

    public void showCourses(List<Corso> corsi){
        System.out.println("Select a course:");
        System.out.println("Code | Level | Activation date");

        for (Corso c : corsi) {
            System.out.printf(
                    "%s | %s | %s%n",
                    c.getId(),
                    c.getLivello(),
                    c.getData_attivazione()
            );
        }
    }

    public void showTeachers(List<Insegnante> insegnanti){
        System.out.println("Select a teacher:");
        System.out.println("Code | Name | Surname | Address | Country");

        for (Insegnante i : insegnanti) {
            System.out.printf(
                    "%s | %s | %s | %s | %s%n",
                    i.getId(),
                    i.getNome(),
                    i.getCognome(),
                    i.getIndirizzo(),
                    i.getNazione()
            );
        }
    }
}
