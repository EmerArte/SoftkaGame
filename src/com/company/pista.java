package com.company;

public class pista {
    private long pistaId;
    private long distancia;

    public pista(long pistaId, long distancia) {
        this.pistaId = pistaId;
        this.distancia = distancia;
    }

    public long getPistaId() {
        return pistaId;
    }

    public void setPistaId(long pistaId) {
        this.pistaId = pistaId;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(long distancia) {
        this.distancia = distancia;
    }
    public double distanciaMetros(){
        return (getDistancia()*1000);
    }
}
