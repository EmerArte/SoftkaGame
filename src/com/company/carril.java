package com.company;

public class carril {
    private int id;
    private pista pistaAsignada;

    public carril(int id, pista pistaAsignada) {
        this.id = id;
        this.pistaAsignada = pistaAsignada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public pista getPistaAsignada() {
        return pistaAsignada;
    }

    public void setCarroAsignado(pista pistaAsignada) {
        this.pistaAsignada = pistaAsignada;
    }
}
