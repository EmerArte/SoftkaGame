package com.company;

public class podio {
    private String primerLugar, segundoLugar, tercerLugar;

    public podio(String primerLugar, String segundoLugar, String tercerLugar) {
        this.primerLugar = primerLugar;
        this.segundoLugar = segundoLugar;
        this.tercerLugar = tercerLugar;
    }

    public String getPrimerLugar() {
        return primerLugar;
    }

    public void setPrimerLugar(String primerLugar) {
        this.primerLugar = primerLugar;
    }

    public String getSegundoLugar() {
        return segundoLugar;
    }

    public void setSegundoLugar(String segundoLugar) {
        this.segundoLugar = segundoLugar;
    }

    public String getTercerLugar() {
        return tercerLugar;
    }

    public void setTercerLugar(String tercerLugar) {
        this.tercerLugar = tercerLugar;
    }
}
