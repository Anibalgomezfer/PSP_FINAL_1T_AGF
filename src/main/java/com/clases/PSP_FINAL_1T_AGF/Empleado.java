package com.clases.PSP_FINAL_1T_AGF;

import com.github.javafaker.Faker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Empleado extends Thread {

    private int id;
    private String email;
    private int ingresos;
    int numRegistros;
    int numHilos;
    int numRegistrosPorHilo;
    int indice;


    public Empleado(int numRegistros, int numHilos, int numRegistrosPorHilo, int indice) {
        this.numRegistros = numRegistros;
        this.numHilos = numHilos;
        this.numRegistrosPorHilo = numRegistrosPorHilo;
        this.indice = indice;
    }

    @Override
    public void run() {
        super.run();
        insertarDatos(indice);
    }


    public void insertarDatos(int indice) {
        synchronized (this) {
            try {
                for (int j = (numRegistrosPorHilo * indice); j < numRegistrosPorHilo * (indice + 1); j++) {
                    ingresos = (int) (Math.random() * 990) + 10;
                    email = Faker.instance().internet().emailAddress();
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/bbdd_psp_1", "DAM2020_PSP", "DAM2020_PSP");
                    Statement consulta = conexion.createStatement();
                    consulta.executeUpdate("INSERT INTO empleados (EMAIL, INGRESOS) VALUES ('" + email + "' , " + ingresos + ")");
                    conexion.close();
                }
            } catch (SQLException e) {
                System.out.println("La conexion a la base de datos ha fallado. \nAsegurate de que tienes encendido xampp y los modulos de Apache y MySQL.");
            }
        }
    }
}
