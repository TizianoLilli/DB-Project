package org.eschool.view;

import org.eschool.model.Corso;
import org.eschool.utils.Input;

import java.util.List;

public class IscrittoView {

    public int showMenu() {

        System.out.print("""
                ---------------------------------
                1) Subscribe to a new course
                2) Delete subscription to a course
                3) Exit
                ---------------------------------
                """);

        return Input.read(1,3);
    }

    public int showCourses(List<Corso> corsi){
        System.out.println("Select a course to subscribe:");
        System.out.println("Code | Level | Activation date");

        for (Corso c : corsi) {
            System.out.printf(
                    "%s | %s | %s%n",
                    c.getId(),
                    c.getLivello(),
                    c.getData_attivazione()
            );
        }

        System.out.print("Make your choice (insert the numeric code): ");
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
