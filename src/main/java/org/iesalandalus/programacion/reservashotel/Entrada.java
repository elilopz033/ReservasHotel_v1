package org.iesalandalus.programacion.reservashotel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Clase que nos permite leer datos por teclado asegur�ndose
 * que el tipo de dato introducido es compatible con el esperado.
 *
 * @author pepino
 */
public class Entrada {

    /**
     * M�todo est�tico que lee una cadena por teclado
     * @return la cadena le�da
     */
    public static String cadena() {
        String valor = "";
        InputStreamReader flujo = new InputStreamReader(System.in);
        BufferedReader teclado = new BufferedReader(flujo);
        try {
            valor = teclado.readLine();
        } catch (IOException e) {
            System.out.print("Error de Entrada/Salida. Int�ntalo de nuevo: ");
        }
        return valor;
    }

    /**
     * M�todo est�tico que lee un entero por teclado y se asegura
     * que el valor introducido es compatible con un entero
     * @return el entero le�do
     */
    public static int entero() {
        int valor = 0;
        boolean leido = false;
        do {
            try {
                valor = Integer.parseInt(cadena());
                leido = true;
            } catch (NumberFormatException e) {
                System.out.print("ERROR: Esperaba un entero. Int�ntalo de nuevo: ");
            }
        } while (!leido);
        return valor;
    }


}
