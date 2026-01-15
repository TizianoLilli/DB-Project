package org.eschool.view;

import org.eschool.model.Corso;
import org.eschool.model.Iscritto;
import org.eschool.utils.Input;

import java.util.List;

public class SegreteriaView {

    public int showMenu() {

        System.out.print("""
                ---------------------------------
                1) Add new subscriber
                2) Delete subscriber
                2) Exit
                ---------------------------------
                """);

        return Input.read(1,3);
    }

    public int showCourses(List<Corso> corsi){
        System.out.println("Select a course");
        System.out.println("Code | Level | Activation date");

        for (Corso c : corsi) {
            System.out.printf(
                    "%s | %s | %s%n",
                    c.getId(),
                    c.getLivello(),
                    c.getData_attivazione()
            );
        }

        System.out.print("Make your choice (insert the numeric Code): ");
        return Input.readInt();
    }

    public int showSubscriber(List<Iscritto> iscritti){
        System.out.println("Select a subscriber");
        System.out.println("ID | Name | Surname | CF");

        for (Iscritto i : iscritti) {
            System.out.printf(
                    "%d | %s | %s | %s%n",
                    i.getId(),
                    i.getNome(),
                    i.getCognome(),
                    i.getCf()
            );
        }

        System.out.print("Make your choice (insert the numeric id): ");
        return Input.readInt();
    }

    public int showActiveCourse(int id){
        System.out.printf("You have an active subscription\nCourse id: %d\n", id);

        System.out.print("""
                ---------------------------------
                Delete subscription for this course?
                1) Yes
                2) No
                ---------------------------------
                """);

        return Input.read(1,2);
    }
}
