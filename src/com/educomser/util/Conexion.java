package com.educomser.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    private static Connection connection;
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/almacendb";
    private static final String USER = "postgres";
    private static final String PASS = "123456";

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException ex) {
            String msg = "ERROR DE DRIVER DB";
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, msg, ex);
        } catch (SQLException ex) {
            String msg = "ERROR CADENA DE CONEXION";
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, msg, ex);
        }
        return connection;
    }

    public static void openConnection() {
        try {
            if (connection != null && connection.isClosed()) {
                connection = getConnection();
            }
        } catch (SQLException ex) {
            String msg = "ERROR ABRIR CONEXION DB";
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, msg, ex);
        }
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException ex) {
            String msg = "ERROR CERRAR CONEXION DB";
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, msg, ex);
        }
    }

}
