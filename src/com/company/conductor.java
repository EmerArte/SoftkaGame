package com.company;

public class conductor extends jugador{
    private carro carroAsociado;


    public conductor( String nombre, carro carroAsociado) {
        super(nombre);
        this.carroAsociado = carroAsociado;
    }

    public carro getCarroAsociado() {
        return carroAsociado;
    }

    public void setCarroAsociado(carro carroAsociado) {
        this.carroAsociado = carroAsociado;
    }
}
