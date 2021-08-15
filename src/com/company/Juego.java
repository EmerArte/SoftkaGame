package com.company;

import java.util.ArrayList;

public class Juego {
    int id;
    ArrayList<conductor> conductorArrayList;

    public Juego(int id, ArrayList<conductor> conductorArrayList) {
        this.id = id;
        this.conductorArrayList = conductorArrayList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<conductor> getConductorArrayList() {
        return conductorArrayList;
    }

    public void setConductorArrayList(ArrayList<conductor> conductorArrayList) {
        this.conductorArrayList = conductorArrayList;
    }
    public long dado(){
        long num=Math.round(Math.random()*6+1);
        return num;
    }
    public String[] iniciarJuego() {
        int contador = 0;
        String podios[] = new String[3];
        while (contador < 3) {
            for (int i = 0; i < conductorArrayList.size(); i++) {
                if (conductorArrayList.get(i).getCarroAsociado().getDistanciaRecorrida() < conductorArrayList.get(i).getCarroAsociado().getCarrilAsociado().getPistaAsignada().distanciaMetros()) {
                    long recorrido = dado() * 100;
                    conductorArrayList.get(i).getCarroAsociado().avanceCarro(recorrido);
                } else {
                    contador++;
                    podios[(contador-1)]=conductorArrayList.get(i).getNombre();
                    conductorArrayList.remove(i);
                }
            }
        }
        return podios;
    }
    public podio crearPodio(String[] podios){
        podio pod = new  podio(podios[0],podios[1],podios[2]);
       return pod;
    }
}
