package org.eschool.model;

public class Livello {
    private String nome;
    private String libro;
    private boolean esame;

    public Livello(String nome, String libro, boolean esame){
        this.nome = nome;
        this.libro = libro;
        this.esame = esame;
    }

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getLibro() {return libro;}

    public void setLibro(String libro) {this.libro = libro;}

    public boolean getEsame() {return esame;}

    public void setEsame(boolean esame) {this.esame = esame;}

}
