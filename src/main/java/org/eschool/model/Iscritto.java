package org.eschool.model;

import java.time.LocalDate;

public class Iscritto {
    private int id; //matricola
    private String cf;
    private String nome;
    private String cognome;
    private LocalDate data_nascita;
    private String indirizzo;
    private int recapito; // cellulare

    public Iscritto (int id, String cf, String nome, String cognome, LocalDate data, String indirizzo, int recapito){
        this.id = id;
        this.cf = cf;
        this.nome = nome;
        this.cognome = cognome;
        this.data_nascita = data;
        this.indirizzo = indirizzo;
        this.recapito = recapito;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getCf() {return cf;}

    public void setCf(String cf) {this.cf = cf;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getCognome() {return cognome;}

    public void setCognome(String cognome) {this.cognome = cognome;}

    public LocalDate getData_nascita() {return data_nascita;}

    public void setData_nascita(LocalDate data) {this.data_nascita = data;}

    public String getIndirizzo() {return indirizzo;}

    public void setIndirizzo(String indirizzo) {this.indirizzo = indirizzo;}

    public int getRecapito() {return recapito;}

    public void setRecapito(int recapito) {this.recapito = recapito;}
}
