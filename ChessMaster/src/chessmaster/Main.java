/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chessmaster;

import java.util.ArrayList;



/**
 *
 * @author Ramon
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int tablero [][]=  {{1, 0 , 1 , 0, 0, 0, 1, 0},
                           { 0, 2 , 0 , 0, 0, 0, 0, 1},
                           { 1, 0 , 0 , 0, 0, 0,-1, 0},
                           { 0,-1 , 0 , 0, 0, 0, 0, 0},
                           {-1, 0 , 0 , 0, 0, 0, 2, 0},
                           { 0,-2 , 0 ,-1, 0,-1, 0, 0},
                           { 0, 0 ,-1 , 0, 0, 0, 0, 0},
                           { 0,-1 , 0 ,-1, 0, 0, 0,-1}};
                           
        Tablero table= new Tablero(tablero);
        table.setTurnoBlancas(true);
       // System.out.println(table.CalcularValorDeEstado());
        //Posicion p= new Posicion(3,5);
        //System.out.println(p.esValida());
        Ficha[][] is = table.getFichas();
        for (int i = 0; i < is.length; i++) {
            for (int j = 0; j < is.length; j++) {
                if(((i+j)%2)==0)
                {
                    Ficha f1= is[i][j];
                    if(f1!=null && table.puedeComer(f1))
                        System.out.println("La ficha ("+f1.getPos().fila+","+f1.getPos().columna+") SI puede comer..");
                }
                
            }
             
             
            
        }
        
    }

}