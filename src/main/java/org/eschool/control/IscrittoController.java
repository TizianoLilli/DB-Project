package org.eschool.control;

import org.eschool.model.Iscritto;
import org.eschool.view.IscrittoView;

import java.io.IOException;

public class IscrittoController implements Controller{

    private Iscritto iscritto;
    private IscrittoView view;

    public IscrittoController (Iscritto iscritto) throws IOException {
        this.iscritto = iscritto;
        this.view = new IscrittoView();
    }

    private int choice;

    @Override
    public void start() throws IOException {
        boolean exit = false;

        while(!exit){
            choice = view.ShowMenu();
/*
            switch (choice){
                case 1 -> Subscribe();
                case 2 -> DeleteSubscription();
                case 3 -> exit = true;
            }*/
        }

        System.out.print("Exiting...");
    }
}
