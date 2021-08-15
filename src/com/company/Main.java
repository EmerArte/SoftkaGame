package com.company;

import com.company.SQL.conexion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    Scanner sc = new Scanner(System.in);
    String query;
    Statement s;
    conexion conn = new conexion();

// Se crea una pista de tamaño aleatorio y se le asigna un id
    public pista crearPista(){
        long id=Math.round(Math.random()*1000+1);
        long tam=(long) Math.floor(Math.random()*10+5);
        System.out.println("La pista id: " + id + " Contiene: " +tam + "KM");
        pista pistaCreada= new pista(id,tam);
        return pistaCreada;
    }
    // Se crean los carriles y se asocian a una pista
    public carril crearCarriles(pista pistaAsociada){
        int id=(int) Math.round(Math.random()*100+1);
        carril carrilNuevo= new carril(id, pistaAsociada);
        return carrilNuevo;
    }
    //Se crea un objeto de tipo carro y se asocia a un carril
    public carro crearCarro(carril carrilAsociado){
        int id=(int) Math.round(Math.random()*100+1);
        carro carroNuevo = new carro(id,carrilAsociado);
        return carroNuevo;
    }
    //Se crea un conductor y se le asigna un carro
    public conductor crearConductor(String nombre, carro carroAsociado){
        conductor conductorNuevo= new conductor(nombre,carroAsociado);
        return conductorNuevo;
    }
    //Se crea una lista con los jugadores que competiran en la carrera
    public ArrayList<jugador> juegoDatosJugadores(int numPlayers){
        ArrayList<jugador> jugadorArrayList= new ArrayList<jugador>();
        jugador jugador;
        for (int i=1; i<=numPlayers;i++){
            System.out.println("Ingrese el nombre del jugador "+i);
                String nom=sc.nextLine();
                jugador=new jugador(nom);
                jugadorArrayList.add(jugador);
        }
        return jugadorArrayList;
    }

    //Método que retorna una lista con todos los carriles necesarios para la carrera
    //Se pasa por parametro la cantidad de carros participantes, necesario para crear los carriles necesarios
    public ArrayList<carril> carriles_de_pista(int numCarros){
        pista pistaCreada=crearPista();
        carril carrilCreado;
        ArrayList<carril> carriles= new ArrayList<carril>();
        for (int i = 1; i <= numCarros; i++) {
            carrilCreado = crearCarriles(pistaCreada);
            carriles.add(carrilCreado);
        }
        return carriles;
    }
    //Método que retorna una lista con todos los carros necesarios para la carrera
    public ArrayList<carro> carros_asignados(int numConductores){
        ArrayList<carro> listCarros = new ArrayList<carro>();
        ArrayList<carril> carrilesCreados=carriles_de_pista(numConductores);
        for (int i = 0; i < carrilesCreados.size(); i++) {
           listCarros.add(crearCarro(carrilesCreados.get(i)));
        }
        return listCarros;
    }
    //Método que retorna la lista de conductores que competiran, recibe por parametro la lista de jugadores
    public ArrayList<conductor> conductores(ArrayList<jugador> jugadores){
        ArrayList<conductor> conductores=new ArrayList<conductor>();
        ArrayList<carro> listaDeCarros=carros_asignados(jugadores.size());
        for (int i = 0; i < listaDeCarros.size(); i++) {
            conductores.add(crearConductor(jugadores.get(i).getNombre(),listaDeCarros.get(i)));
        }
        return conductores;
    }

    //-----------------METODOS DE BASES DE DATOS---------------------

    // Método que aumenta el contador de veces que un Jugador ha ganado la carrera
    public void update(int totalGanado,String nombreGanador){
        try {
            s= conn.getConn().createStatement();
            query = "UPDATE `ganadores` SET vecesGanado='"+totalGanado+"' WHERE nombre ='"+nombreGanador+"'";
            s.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // Método para insertar un nuevo ganador a la base de datos
    public void insert(String nombreGanador){
        try {
            query= "INSERT INTO `ganadores` (`nombre`, `vecesGanado`) VALUES ('"+nombreGanador+"','"+1+"')";
            s = conn.getConn().createStatement();
            s.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    // Método que consulta si un jugador ya há ganado o existe en la base de datos
    // Retorna un objeto de tipo ResultSet
    public ResultSet consultaGanadores(String nom_primerLugar){
        conexion conn = new conexion();
        ResultSet rs;
        try {
            Statement s = conn.getConn().createStatement();
            rs= s.executeQuery("SELECT * FROM `ganadores` WHERE nombre ='"+nom_primerLugar+"'");
            return rs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        conn.desconectar();
        return null;
    }
    // Método utilizado para guardar los resultados del juego en la Base de datos
    public void guardarResultado(ResultSet rs, String nom_primerLugar){
        try {
            if(!rs.next()){
                insert(nom_primerLugar);
            }else{
               int totalganado=(rs.getInt(2))+1;
               update(totalganado,nom_primerLugar);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        conn.desconectar();
    }


    public static void main(String[] args) {
        System.out.println("----------¡¡¡SofkaGame!!!---------");
        Main main = new Main();

        System.out.println("Para empezar el juego debes ingresar los jugadores!");
        System.out.println("Ingresa la cantidad de jugadores de este juego-> (Min=3)");
        int tam = Integer.parseInt(main.sc.nextLine());
        while (tam<3){
            System.out.println("(Debes ingresar Mínimo 3 jugadores) Intentalo nuevamente:");
            tam= Integer.parseInt(main.sc.nextLine());
        }
        ArrayList<conductor> conductorArrayList = main.conductores(main.juegoDatosJugadores(tam));
        int id=(int)Math.round(Math.random()*100+1);
        Juego game= new Juego(id,conductorArrayList);
        podio podio = game.crearPodio(game.iniciarJuego());
        System.out.println("------La carrera Ha empezado------");
        for (int i = 3; i >= 1; i--) {
            System.out.println("Podio en: "+i);
        }
        System.out.println("Juego finalizado");
        System.out.println("\tPrimer lugar: " + podio.getPrimerLugar());
        System.out.println("\tSegundo lugar: " + podio.getSegundoLugar());
        System.out.println("\tTercer lugar: " + podio.getTercerLugar());
        main.guardarResultado(main.consultaGanadores(podio.getPrimerLugar()),podio.getPrimerLugar());
        System.out.println("GRACIAS POR JUGAR");
        System.out.println("By: Emer Arteaga");
    }
}
