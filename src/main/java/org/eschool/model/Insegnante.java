package org.eschool.model;

public class Insegnante {
    private int id;
    private String nome;
    private String cognome;
    private String indirizzo;
    private String nazione;

    public Insegnante(int id, String nome, String cognome, String indirizzo, String nazione){
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.nazione = nazione;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getCognome() {return cognome;}

    public void setCognome(String cognome) {this.cognome = cognome;}

    public String getIndirizzo() {return indirizzo;}

    public void setIndirizzo(String indirizzo) {this.indirizzo = indirizzo;}

    public String getNazione() {return nazione;}

    public void setNazione(String nazione) {this.nazione = nazione;}
}
