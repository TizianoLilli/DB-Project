package org.eschool.model;

import org.eschool.utils.enums.StatoLezione;

import java.time.LocalDate;
import java.time.LocalTime;

public class Lezione {
    private int id;
    private int corso;
    private LocalDate data;
    private LocalTime ora;
    private StatoLezione erogata; // meglio usare una enum
    private int insegnante; // o meglio usare tipo insegnante?

    public Lezione (int id, int corso, LocalDate data, LocalTime ora, StatoLezione erogata, int insegnante){
        this.id = id;
        this.corso = corso;
        this.data = data;
        this.ora = ora;
        this.erogata = erogata;
        this.insegnante = insegnante;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public int getCorso() {return corso;}

    public void setCorso(int corso) {this.corso = corso;}

    public LocalDate getData_lezione() {return data;}

    public void setData_lezione(LocalDate data) {this.data = data;}

    public LocalTime getOra_lezione() {return ora;}

    public void setOra_lezione(LocalTime ora) {this.ora = ora;}

    public StatoLezione getErogata() {return erogata;}

    public void setErogata(StatoLezione erogata) {this.erogata = erogata;}

    public int getInsegnante() {return insegnante;}

    public void setInsegnante(int insegnante) {this.insegnante = insegnante;}
}
