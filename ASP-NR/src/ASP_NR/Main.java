/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ASP_NR;

import java.util.Hashtable;
import java.io.IOException;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author Arturo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, Exception {
        // TODO code application logic here
        Archivo arch= new Archivo("src\\ASP_NR\\");
        arch.setFileNameIN("archivoEntrada.txt");
        Vector producciones=arch.leer();

        ASP  anal= new ASP();
        anal.setTablaHash(producciones);
        anal.CalcularPrimeros();
        anal.CalcularSiguientes();
        anal.imprimirConjuntosPrimeros();
//        anal.hacerTablaASP();
//        anal.imprimirTablaASP();
    }
}