package org.eschool.model;

import java.time.LocalDate;

public class Corso {
    private String id;
    private String livello;
    private LocalDate data_attivazione;

    public Corso(String id, String livello, LocalDate data){
        this.id = id;
        this.livello = livello;
        this.data_attivazione = data;
    }

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getLivello() {return livello;}

    public void setLivello(String livello) {this.livello = livello;}

    public LocalDate getData_attivazione() {return data_attivazione;}

    public void setData_attivazione(LocalDate data) {this.data_attivazione = data;}
}
