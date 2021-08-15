package com.company;

public class carro {
    private int id;
    private carril carrilAsociado;
    private long distanciaRecorrida;

    public carro(int id, carril carrilAsociado) {
        this.id = id;
        this.carrilAsociado = carrilAsociado;
        this.distanciaRecorrida=0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public carril getCarrilAsociado() {
        return carrilAsociado;
    }

    public void setCarrilAsociado(carril carrilAsociado) {
        this.carrilAsociado = carrilAsociado;
    }

    public long getDistanciaRecorrida() {
        return distanciaRecorrida;
    }

    public void setDistanciaRecorrida(long distanciaRecorrida) {
        this.distanciaRecorrida = distanciaRecorrida;
    }

    public void avanceCarro(long distancia){
        setDistanciaRecorrida(getDistanciaRecorrida()+distancia);
    }
}
