package org.iesalandalus.programacion.reservashotel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Clase que nos permite leer datos por teclado asegurándose
 * que el tipo de dato introducido es compatible con el esperado.
 *
 * @author pepino
 */
public class Entrada {

    /**
     * Método estático que lee una cadena por teclado
     * @return la cadena leída
     */
    public static String cadena() {
        String valor = "";
        InputStreamReader flujo = new InputStreamReader(System.in);
        BufferedReader teclado = new BufferedReader(flujo);
        try {
            valor = teclado.readLine();
        } catch (IOException e) {
            System.out.print("Error de Entrada/Salida. Inténtalo de nuevo: ");
        }
        return valor;
    }

    /**
     * Método estático que lee un entero por teclado y se asegura
     * que el valor introducido es compatible con un entero
     * @return el entero leído
     */
    public static int entero() {
        int valor = 0;
        boolean leido = false;
        do {
            try {
                valor = Integer.parseInt(cadena());
                leido = true;
            } catch (NumberFormatException e) {
                System.out.print("ERROR: Esperaba un entero. Inténtalo de nuevo: ");
            }
        } while (!leido);
        return valor;
    }


}
