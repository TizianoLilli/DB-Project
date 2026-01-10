package org.eschool.utils;

public enum Ruolo {
    ISCRITTO(1),
    INSEGNANTE(2),
    PERSONALE_AMMINISTRATIVO(3),
    PERSONALE_SEGRETERIA(4);

    private final int i;

    Ruolo(int idx) {
        this.i = idx;
    }

    public int getIdx(){return i;}

    public static Ruolo fromIdx(int idx){
        for (Ruolo r : values()){
            if (r.i == idx){
                return r;
            }
        }
        return null;
    }
}
