package org.eschool.utils;

import org.eschool.utils.enums.StatoLezione;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DataReport {
    private int insegnante;
    private int corso;
    private StatoLezione stato;
    private LocalDate data_lezione;
    private LocalTime ora_lezione;
    private List<Integer> assenti = new ArrayList<>();

    public DataReport(int corso, StatoLezione stato, LocalDate data, LocalTime ora){
        this.corso = corso;
        this.stato = stato;
        this.data_lezione = data;
        this.ora_lezione = ora;
    }

    public DataReport(int insegnante, int corso, StatoLezione stato, LocalDate data, LocalTime ora){
        this.insegnante = insegnante;
        this.corso = corso;
        this.stato = stato;
        this.data_lezione = data;
        this.ora_lezione = ora;
    }

    public int getInsegnante() {return insegnante;}

    public void setInsegnante(int insegnante) {this.insegnante = insegnante;}

    public int getCorso(){return this.corso;}

    public void setCorso(int corso) {this.corso = corso;}

    public StatoLezione getStato() {return stato;}

    public void setStato(StatoLezione stato) {this.stato = stato;}

    public LocalDate getData_lezione() {return data_lezione;}

    public void setData_lezione(LocalDate data_lezione) {this.data_lezione = data_lezione;}

    public LocalTime getOra_lezione() {return ora_lezione;}

    public void setOra_lezione(LocalTime ora_lezione) {this.ora_lezione = ora_lezione;}

    public List<Integer> getAssenti() {return assenti;}

    public void setAssenti(List<Integer> assenti) {this.assenti = assenti;}
}
