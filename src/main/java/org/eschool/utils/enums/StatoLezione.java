package org.eschool.utils.enums;

public enum StatoLezione {
    PROGRAMMATA(1),
    EROGATA(2),
    ANNULLATA(3);

    private final int stato;

    StatoLezione(int s){this.stato = s;}

    public static StatoLezione fromState(int s){
        for (StatoLezione sl : values()){
            if (sl.stato == s){
                return sl;
            }
        }
        return null;
    }
}
