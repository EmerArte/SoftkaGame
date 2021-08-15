package com.company.SQL;


import java.sql.Connection;
import java.sql.DriverManager;

public class conexion {

    private static Connection conn;
    private static String driver = "com.mysql.jdbc.Driver";
    private static String user = "root";
    private static String pass = "";
    //El nombre de la variable name debe coincidir con el nombre de la db creada en tu local host
    //Editar de ser necesario
    private static String name = "sofkagame";
    private static String url = "jdbc:mysql://localhost:3306/" + name;
    public conexion() {
        conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pass);
            if (conn != null) {
            }
        } catch (Exception e) {
            System.out.println("Error al conectar " + e);
        }
    }

//conectarse a la base de datos
    public Connection getConn(){
        return conn;
    }
    // Deconectarse de la base de datos
    public void desconectar(){
        conn=null;
    }

}
