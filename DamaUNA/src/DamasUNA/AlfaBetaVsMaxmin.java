package DamasUNA;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *///package chessmaster;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JDesktopPane;

/**
 *
 * @author Ramon
 */
public class AlfaBetaVsMaxmin extends Thread{

    private TableroGUI ITablero;

    public AlfaBetaVsMaxmin() {
        this.ITablero= new TableroGUI('A');
    }

    
    
    public void run() {
        //                           0  1   2   3  4  5  6  7
        int matTablero[][] = {{1, 0, 1, 0, 1, 0, 1, 0},//0
            {0, 1, 0, 1, 0, 1, 0, 1},//1
            {1, 0, 1, 0, 1, 0, 1, 0},//2
            {0, 0, 0, 0, 0, 0, 0, 0},//3
            {0, 0, 0, 0, 0, 0, 0, 0},//4
            {0, -1, 0, -1, 0, -1, 0, -1},//5
            {-1, 0, -1, 0, -1, 0, -1, 0},//6
            {0, -1, 0, -1, 0, -1, 0, -1}};//7

        Tablero table = new Tablero(matTablero);
        System.out.println(table.toString());
        System.out.println("------------------------------------------------------");

        //IEstrategia jugador1 = new Aleatorio();
        IEstrategia jugador1 = new AlfaBeta(2);//verde
        IEstrategia jugador2 = new MaxMin(2);//azul
        Tablero jugada = null;

        long fin = System.currentTimeMillis() + 300000;
        while (true) {

            table.setTurnoBlancas(true);
            if (table.tieneFichasEnTablero() != 0) {
                Tablero aux = new Tablero(matTablero);
                aux.setTurnoBlancas(true);
                jugada = jugador1.jugar(aux);
                if (jugada == null) {
                    System.out.println(" 1 Ha ganado el Jugador con fichas negras");
                    break;
                } else {
                    //actualizar el tablero original...
                    jugada.coronarPeon();
                    matTablero = jugada.getTablero();
                    table.Tabla = jugada.clonarTabla(jugada.Tabla);
                    table.setTurnoBlancas(false);
                   
                    //Desde aca muestra se hace para mostrar en la consola

                    if (table.getTurnoBlancas()) {
                        System.out.println("Jugó blancas");
                    } else {
                        System.out.println("Jugó negras");
                    }
                    System.out.println(table.toString());

                    //Desde aca vamos a usar la interfaz grafica...

                    //Hacemos el movimiento dependiendo si se comieron piezas o si solo se movio una.
                    if (jugada.VectorDeFichasComidas.size() != 0) {
                        //Ubicamos a la pieza en su nueva posicion luego de comer piezas...
                        Posicion pinicial = jugada.fichaMovida.getPosAnterior();
                        Posicion pfinal = jugada.fichaMovida.getPos();
                        long t = System.currentTimeMillis() + 2000;
                        while (t > System.currentTimeMillis());
                        this.ITablero.moverPieza(pinicial.fila, pinicial.columna, pfinal.fila, pfinal.columna);
                        this.ITablero.cambiarTurno();
                        //Esperamos 2 segundo para borrar las piezas comidas...
                       

                        for (int i = 0; i < jugada.VectorDeFichasComidas.size(); i++) {
                            t = System.currentTimeMillis() + 2000;
                            while (t > System.currentTimeMillis());
                            Ficha comida = (Ficha) jugada.VectorDeFichasComidas.get(i);
                            this.ITablero.comerPieza(comida.getPos().fila, comida.getPos().columna);

                        }

                    } else {
                        //Ubicamos a la pieza en su nueva posicion...
                        Posicion pinicial = jugada.fichaMovida.getPosAnterior();
                        Posicion pfinal = jugada.fichaMovida.getPos();
                        long t = System.currentTimeMillis() + 2000;
                        while (t > System.currentTimeMillis());
                        this.ITablero.moverPieza(pinicial.fila, pinicial.columna, pfinal.fila, pfinal.columna);
                        this.ITablero.cambiarTurno();
                    }
                     jugada = null;

                }
            } else {
                System.out.println(" 2 Ha ganado el Jugador con fichas negras");
                break;
            }






            if (table.tieneFichasEnTablero() != 0) {
                Tablero aux = new Tablero(matTablero);
                aux.setTurnoBlancas(false);
                jugada = jugador2.jugar(aux);
                if (jugada == null) {
                    System.out.println(" 3 Ha ganado el Jugador con fichas blancas");
                    break;
                } else {
                    //actualizar el tablero original...
                    jugada.coronarPeon();
                    matTablero = jugada.getTablero();
                    table.Tabla = jugada.clonarTabla(jugada.Tabla);
                    table.setTurnoBlancas(true);
                    
                    //Desde aca muestra se hace para mostrar en la consola

                    if (table.getTurnoBlancas()) {
                        System.out.println("Jugó blancas");
                    } else {
                        System.out.println("Jugó negras");
                    }
                    System.out.println(table.toString());

                    //Desde aca vamos a usar la interfaz grafica...

                    //Hacemos el movimiento dependiendo si se comieron piezas o si solo se movio una.
                    if (jugada.VectorDeFichasComidas.size() != 0) {
                        //Ubicamos a la pieza en su nueva posicion luego de comer piezas...
                        Posicion pinicial = jugada.fichaMovida.getPosAnterior();
                        Posicion pfinal = jugada.fichaMovida.getPos();
                        long t = System.currentTimeMillis() + 2000;
                        while (t > System.currentTimeMillis());
                       this.ITablero.moverPieza(pinicial.fila, pinicial.columna, pfinal.fila, pfinal.columna);
                        this.ITablero.cambiarTurno();
       
                        //Esperamos 2 segundo para borrar las piezas comidas...
                        

                        for (int i = 0; i < jugada.VectorDeFichasComidas.size(); i++) {
                            t = System.currentTimeMillis() + 2000;
                            while (t > System.currentTimeMillis());
                            Ficha comida = (Ficha) jugada.VectorDeFichasComidas.get(i);
                            this.ITablero.comerPieza(comida.getPos().fila, comida.getPos().columna);

                        }

                    } else {
                        //Ubicamos a la pieza en su nueva posicion...
                        Posicion pinicial = jugada.fichaMovida.getPosAnterior();
                        Posicion pfinal = jugada.fichaMovida.getPos();
                        long t = System.currentTimeMillis() + 2000;
                        while (t > System.currentTimeMillis());
                        this.ITablero.moverPieza(pinicial.fila, pinicial.columna, pfinal.fila, pfinal.columna);
                        this.ITablero.cambiarTurno();
                    }
                    jugada = null;
                }
            } else {
                System.out.println(" 4 Ha ganado el Jugador con fichas blancas");
                break;
            }
        }

    }

    void inicializar(JDesktopPane mdiForm) {
        
        this.ITablero.muestra(mdiForm);

    }
}