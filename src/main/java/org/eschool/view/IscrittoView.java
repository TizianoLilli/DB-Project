package org.eschool.view;

import org.eschool.model.Corso;
import org.eschool.utils.Input;

import java.util.List;

public class IscrittoView {

    public int showMenu() {

        System.out.print("""
                ---------------------------------
                1) Show all courses
                2) Show my subscription
                3) Exit
                ---------------------------------
                """);

        return Input.read(1,3);
    }

    public void showCourses(List<Corso> corsi){
        System.out.println("Here are all courses");
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

    public void showActiveCourse(int id){
        System.out.printf("You have an active subscription\nCourse id: %d\n", id);
    }
}
