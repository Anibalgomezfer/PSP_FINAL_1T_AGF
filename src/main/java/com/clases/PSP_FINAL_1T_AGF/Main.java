package com.clases.PSP_FINAL_1T_AGF;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int numRegistros = 0;
        int numHilos = 0;
        int numRegistrosPorHilo;

        ArrayList<Empleado> arrayEmpleados = new ArrayList<>();
        Scanner teclado = new Scanner(System.in);


        try {
            while (numRegistros <= 0) {
                System.out.println("Introduce el numero de registros que quieres introducir: ");
                numRegistros = teclado.nextInt();
                if (numRegistros <= 0) {
                    System.out.println("Debes introducir al menos 1 registro.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Parece que has introducido una letra y se esperaba un numero.");
            System.out.println("Inicia el programa de nuevo.");

        }


        try {
            while (numHilos <= 0) {
                System.out.println("Introduce el numero de hilos que deseas utilizar: ");
                numHilos = teclado.nextInt();
                if (numHilos <= 0) {
                    System.out.println("Debes utilizar al menos un hilo.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Parece que has introducido una letra y se esperaba un numero.");
            System.out.println("Inicia el programa de nuevo.");
        }

        int modulo = (numRegistros % numHilos);
        numRegistrosPorHilo = numRegistros / numHilos;

        for (int indice = 0; indice < numHilos; indice++) {

            if (indice == (numHilos-1)){
                numRegistrosPorHilo = numRegistrosPorHilo+modulo;
                Empleado hilo = new Empleado(numRegistros, numHilos, numRegistrosPorHilo, indice);
                arrayEmpleados.add(hilo);
            }else{
                Empleado hilo = new Empleado(numRegistros, numHilos, numRegistrosPorHilo, indice);
                arrayEmpleados.add(hilo);
            }

        }
        for (int j = 0; j < arrayEmpleados.size(); j++) {
            arrayEmpleados.get(j).start();
        }
    }


}
