package org.eschool.utils.enums;

public enum TipoLivello {
    ELEMENTARY(1),
    INTERMEDIATE(2),
    FIRST_CERTIFICATE(3),
    ADVANCED(4),
    PROFICIENCY(5),
    BUSINESS(6),
    INTENSIVE(7);

    private final int livello;

    TipoLivello(int l) {
        this.livello = l;
    }

    public static TipoLivello fromL(int l){
        for (TipoLivello t : values()){
            if (t.livello == l){
                return t;
            }
        }
        return null;
    }
}
