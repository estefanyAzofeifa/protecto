/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tictactoeei;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author nelsonrivas
 */
public class TicTacToe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Se crea el tablero de juego como una matriz 3x3, donde cada casilla puede ser 'X', 'O' o nula.
        String[][] tablero = new String[3][3];
        // Se crean varias listas para gestionar el estado del juego

        ArrayList<ArrayList<Integer>> espaciosVacios = new ArrayList<>();
        ArrayList<ArrayList<Integer>> marcasDelRival = new ArrayList<>();
        ArrayList<ArrayList<Integer>> marcasPropias = new ArrayList<>();
        ArrayList<ArrayList<ArrayList<Integer>>> posiblesJugadasChallenger = new ArrayList<>();
        ArrayList<ArrayList<ArrayList<Integer>>> posiblesJugadasRival = new ArrayList<>();
        // Se solicita la entrada del jugador para decidir si quiere jugar con 'X' o 'O'.

        String challenger = JOptionPane.showInputDialog("1. X\n2. O");
        String rival;
        // Si el jugador selecciona '1', se le asigna 'X', si no, se asigna 'O'.

        challenger = challenger.equals("1") ? "X" : "O";
        rival = challenger.equals("X") ? "O" : "X";// El rival toma el símbolo opuesto.

        int number = 1;// Variable para llevar el contador de movimientos.

        System.out.println("\n\nmovimiento " + number++); // Imprime el primer movimiento.
        smartMove(tablero, challenger); // El challenger realiza un movimiento inteligente.
        visualizacionRecursiva(tablero, espaciosVacios, 0, 0); // Muestra el tablero después del movimiento.
        revisionMarcasRecursivo(tablero, marcasPropias, 0, 0, challenger); // Revisa las marcas del challenger.
        revisionMarcasRecursivo(tablero, marcasDelRival, 0, 0, rival); // Revisa las marcas del rival.
        posiblesJugadasRecursivo(tablero, marcasPropias, posiblesJugadasChallenger, 0, challenger); // Calcula las posibles jugadas del challenger.
        posiblesJugadasRecursivo(tablero, marcasDelRival, posiblesJugadasRival, 0, rival); // Calcula las posibles jugadas del rival.

        System.out.println("\n\nmovimiento " + number++); // Imprime el segundo movimiento.
        randomMove(tablero, espaciosVacios, rival); // El rival realiza un movimiento aleatorio.
        visualizacionRecursiva(tablero, espaciosVacios, 0, 0); // Muestra el tablero después del movimiento.
        revisionMarcasRecursivo(tablero, marcasPropias, 0, 0, challenger); // Revisa las marcas del challenger.
        revisionMarcasRecursivo(tablero, marcasDelRival, 0, 0, rival); // Revisa las marcas del rival.
        posiblesJugadasRecursivo(tablero, marcasPropias, posiblesJugadasChallenger, 0, challenger); // Calcula las posibles jugadas del challenger.
        posiblesJugadasRecursivo(tablero, marcasDelRival, posiblesJugadasRival, 0, rival); // Calcula las posibles jugadas del rival.

        System.out.println("\n\nmovimiento " + number++); // Imprime el tercer movimiento.
        smartMove(tablero, challenger); // El challenger realiza otro movimiento inteligente.
        visualizacionRecursiva(tablero, espaciosVacios, 0, 0); // Muestra el tablero después del movimiento.
        revisionMarcasRecursivo(tablero, marcasPropias, 0, 0, challenger); // Revisa las marcas del challenger.
        revisionMarcasRecursivo(tablero, marcasDelRival, 0, 0, rival); // Revisa las marcas del rival.
        posiblesJugadasRecursivo(tablero, marcasPropias, posiblesJugadasChallenger, 0, challenger); // Calcula las posibles jugadas del challenger.
        posiblesJugadasRecursivo(tablero, marcasDelRival, posiblesJugadasRival, 0, rival); // Calcula las posibles jugadas del rival.
// Variable para determinar si el juego sigue en curso.

        boolean continuar = true; //Se utilizara para determinar si hay empate, victoria o derrota

        OUTER:
        while (!espaciosVacios.isEmpty() && continuar == true) {
            // Se verifica si el challenger ha ganado.
            continuar = verificadorDeVictoria(tablero, posiblesJugadasChallenger, challenger);

            if (continuar == true) { // Si el challenger sigue en el juego.
                System.out.println("\n\nmovimiento " + number++); // Imprime el siguiente movimiento.
                randomMove(tablero, espaciosVacios, rival); // El rival hace un movimiento aleatorio.
                visualizacionRecursiva(tablero, espaciosVacios, 0, 0); // Muestra el tablero después del movimiento.
                revisionMarcasRecursivo(tablero, marcasPropias, 0, 0, challenger); // Revisa las marcas del challenger.
                revisionMarcasRecursivo(tablero, marcasDelRival, 0, 0, rival); // Revisa las marcas del rival.
                posiblesJugadasRecursivo(tablero, marcasPropias, posiblesJugadasChallenger, 0, challenger); // Calcula las posibles jugadas del challenger.
                posiblesJugadasRecursivo(tablero, marcasDelRival, posiblesJugadasRival, 0, rival); // Calcula las posibles jugadas del rival.
            } else {
                break OUTER; // Si el challenger ha ganado, se termina el juego.
            }
// Se verifica si el rival ha ganado.
            continuar = verificadorDeVictoria(tablero, posiblesJugadasRival, rival);

            if (continuar == true) { // Si el rival sigue en el juego.
                System.out.println("\n\nmovimiento " + number++); // Imprime el siguiente movimiento.
                verificarMejorJugada(tablero, posiblesJugadasChallenger, challenger, posiblesJugadasRival, rival, continuar); // El challenger hace su mejor jugada.
                visualizacionRecursiva(tablero, espaciosVacios, 0, 0); // Muestra el tablero después del movimiento.
                revisionMarcasRecursivo(tablero, marcasPropias, 0, 0, challenger); // Revisa las marcas del challenger.
                revisionMarcasRecursivo(tablero, marcasDelRival, 0, 0, rival); // Revisa las marcas del rival.
                posiblesJugadasRecursivo(tablero, marcasPropias, posiblesJugadasChallenger, 0, challenger); // Calcula las posibles jugadas del challenger.
                posiblesJugadasRecursivo(tablero, marcasDelRival, posiblesJugadasRival, 0, rival); // Calcula las posibles jugadas del rival.
            } else {
                break OUTER; // Si el rival ha ganado, se termina el juego.
            }
        }

    }
// Función que verifica si un jugador ha ganado.

    public static boolean verificadorDeVictoria(String[][] tablero, ArrayList<ArrayList<ArrayList<Integer>>> posiblesJugadas, String jugador) {
        // Recorre todas las posibles jugadas de un jugador.

        for (ArrayList<ArrayList<Integer>> jugada : posiblesJugadas) {
            int marcasEnLinea = 0;
            // Recorre cada posición dentro de una jugada posible.

            for (ArrayList<Integer> spot : jugada) {
                if (jugador.equalsIgnoreCase(tablero[spot.get(0)][spot.get(1)])) {
                    marcasEnLinea++;
                    // Cuenta las marcas del jugador en la línea.

                }
                if (marcasEnLinea == 3) {// Si hay 3 marcas seguidas, el jugador ha ganado.

                    return false;
                }
            }
        }
        return true; // Si no hay victoria, sigue el juego.
    }
    // Función que realiza la mejor jugada para el jugador challenger.
    public static void verificarMejorJugada(String[][] tablero, ArrayList<ArrayList<ArrayList<Integer>>> posiblesJugadasChallenger, String challenger, ArrayList<ArrayList<ArrayList<Integer>>> posiblesJugadasRival, String rival, boolean salir) {
        //Hace falta verificar las diagonales
    // Se verifica si hay una jugada de ataque que garantice la victoria del challenger.
        boolean ataque = siguienteMoviento(tablero, posiblesJugadasChallenger, challenger, challenger);
        if (!ataque) {
            // Si no hay ataque, se verifica si el rival tiene una jugada de ataque y se bloquea.
            boolean defensa = siguienteMoviento(tablero, posiblesJugadasRival, rival, challenger);
            if (!defensa) {
                // Si no hay ataque ni defensa, se hace una jugada aleatoria.
                while (true) {
                    Random random = new Random();
                    int lineaRandom = random.nextInt(posiblesJugadasChallenger.size());
                    ArrayList<ArrayList<Integer>> lineasDisponibles = posiblesJugadasChallenger.get(lineaRandom);
                    int espacioRandom = random.nextInt(lineasDisponibles.size());
                    int row = posiblesJugadasChallenger.get(lineaRandom).get(espacioRandom).get(0);
                    int column = posiblesJugadasChallenger.get(lineaRandom).get(espacioRandom).get(1);

                    if (tablero[row][column] == null) { // Verifica si el espacio está vacío.
                        tablero[row][column] = challenger; // Coloca la marca en el espacio.
                        break;
                    }
                }

            }

        } else {
            System.out.println("\n\n!!WINNER -> " + challenger + " <- WINNER!!\n\n");// Si el challenger gana, muestra el punto ganado.
            salir = true;// Termina el juego.
        }
    }
    // Función que verifica si el siguiente movimiento es una jugada ganadora o defensiva.
    public static boolean siguienteMoviento(String[][] tablero, ArrayList<ArrayList<ArrayList<Integer>>> posiblesJugadas, String gamerOne, String gamerTwo) {
        for (int i = 0; i < posiblesJugadas.size(); i++) {
            ArrayList<ArrayList<Integer>> movimiento = new ArrayList<>();
            ArrayList<ArrayList<Integer>> pendiente = new ArrayList<>();
            int picks = 0;

            for (int j = 0; j < posiblesJugadas.get(i).size(); j++) {
                ArrayList<Integer> linea = new ArrayList<>();
                ArrayList<Integer> campoLibre = new ArrayList<>();

                int row = posiblesJugadas.get(i).get(j).get(0);
                int column = posiblesJugadas.get(i).get(j).get(1);

                if (tablero[row][column] == null) {
                    campoLibre.add(row);
                    campoLibre.add(column);
                    pendiente.add(campoLibre);// Marca los espacios libres.
                }

                if (tablero[row][column] != null && tablero[row][column].contains(gamerOne)) {
                    picks++;// Cuenta las marcas el jugador.
                    linea.add(row);
                    linea.add(column);
                    movimiento.add(linea);
                }
            }

            switch (picks) {

                case 2 -> {
                    System.out.println("\nAtencion!!");
                    System.out.println("Espacios marcados con " + gamerOne + ": " + movimiento); // Muestra las jugadas actuales del jugador.
                    System.out.println("Espacio vacio: " + pendiente); // Muestra las jugadas pendientes.
                    jugadaFinal(tablero, pendiente.get(0), gamerTwo); // Realiza la jugada en el espacio vacío.
                    return true;

                }

            }

        }
        return false;// Si no se puede hacer una jugada ganadora, retorna falso.
    }

    public static void jugadaFinal(String[][] tablero, ArrayList<Integer> ubicacionPendiente, String mark) {
        tablero[ubicacionPendiente.get(0)][ubicacionPendiente.get(1)] = mark; // Marca el espacio vacío con el símbolo del jugador.
    }
    // Función recursiva para calcular las posibles jugadas del jugador.

    public static void posiblesJugadasRecursivo(String[][] tablero, ArrayList<ArrayList<Integer>> marcas, ArrayList<ArrayList<ArrayList<Integer>>> posiblesJugadas, int i, String gamer) {

        if (!posiblesJugadas.isEmpty() && i == 0) {
            posiblesJugadas.clear();// Limpiar las jugadas anteriores.
        }

        if (i >= marcas.size()) {
            System.out.println("Posibles jugadas para la victoria " + gamer + ": " + posiblesJugadas);// Muestra las jugadas posibles para el jugador.
            return;
        }

        ArrayList<ArrayList<Integer>> posiblesJugadasH = new ArrayList<>(); // Jugadas horizontales.
        ArrayList<ArrayList<Integer>> posiblesJugadasV = new ArrayList<>(); // Jugadas verticales.
        int horizontal = marcas.get(i).get(0); // Obtiene la posición horizontal.
        int vertical = marcas.get(i).get(1); // Obtiene la posición vertical.

        // Calcula las jugadas horizontales y verticales para el jugador.
        posiblesJugadasRecursivoAuxH(tablero, horizontal, posiblesJugadasH, 0, gamer);
        posiblesJugadasRecursivoAuxV(tablero, vertical, posiblesJugadasV, 0, gamer);
        // Si las jugadas son completas, se agregan a las posibles jugadas.
        if (posiblesJugadasH.size() == 3 && !posiblesJugadas.contains(posiblesJugadasH)) {
            posiblesJugadas.add(posiblesJugadasH);
        }
        if (posiblesJugadasV.size() == 3 && !posiblesJugadas.contains(posiblesJugadasV)) {
            posiblesJugadas.add(posiblesJugadasV);
        }

        posiblesJugadasRecursivo(tablero, marcas, posiblesJugadas, i + 1, gamer);// Llamada recursiva para la siguiente posición.

    }
    // Función auxiliar para calcular las jugadas horizontales.
    private static void posiblesJugadasRecursivoAuxH(String[][] tablero, int pos, ArrayList<ArrayList<Integer>> jugadas, int j, String gamer) {

        if (j >= tablero.length) {
            return; //Si ya se han revisado todas las columnas, retorna.
        }

        if (tablero[pos][j] == null || tablero[pos][j].equals(gamer)) {
            ArrayList<Integer> jugadaH = new ArrayList<>();
            jugadaH.add(pos);
            jugadaH.add(j);

            jugadas.add(jugadaH);// Agrega la jugada a la lista si es válida.
        }

        posiblesJugadasRecursivoAuxH(tablero, pos, jugadas, j + 1, gamer);// Llamada recursiva para la siguiente columna.
    }
    // Función auxiliar para calcular las jugadas verticales.

    private static void posiblesJugadasRecursivoAuxV(String[][] tablero, int pos, ArrayList<ArrayList<Integer>> jugadas, int j, String gamer) {
        if (j >= tablero.length) {
            return;// Si ya se han revisado todas las filas, retorna.
        }

        if (tablero[j][pos] == null || tablero[j][pos].equals(gamer)) {
            ArrayList<Integer> jugadaV = new ArrayList<>();
            jugadaV.add(j);
            jugadaV.add(pos);

            jugadas.add(jugadaV); // Agrega la jugada a la lista si es válida.
        }

        posiblesJugadasRecursivoAuxV(tablero, pos, jugadas, j + 1, gamer);// Llamada recursiva para la siguiente fila.

    }
    // Función recursiva para revisar las marcas en el tablero y almacenar las ubicaciones.
    public static void revisionMarcasRecursivo(String[][] tablero, ArrayList<ArrayList<Integer>> marcas, int i, int j, String gamer) {

        if (!marcas.isEmpty() && i == 0 && j == 0) {
            marcas.clear();// Limpiar las marcas anteriores.
        }

        if (i >= tablero.length) {
            System.out.println("Marcas " + gamer + ": " + marcas);// Muestra las marcas del jugador.

            return;
        }
        if (j >= tablero[i].length) {
            revisionMarcasRecursivo(tablero, marcas, i + 1, 0, gamer);// Llamada recursiva para la siguiente fila.
            return;
        }

        if (gamer.equals(tablero[i][j])) {
            ArrayList<Integer> marca = new ArrayList<>();
            marca.add(i);
            marca.add(j);
            marcas.add(marca);// Si la marca es del jugador, se agrega a la lista de marcas.

        }

        revisionMarcasRecursivo(tablero, marcas, i, j + 1, gamer);// Llamada recursiva para la siguiente columna.
    }

    // Función recursiva que visualiza el estado del tablero.
    public static void visualizacionRecursiva(String[][] tablero, ArrayList<ArrayList<Integer>> espaciosVacios, int i, int j) {
    // limpia la lista de los espacios vacios 
        if (!espaciosVacios.isEmpty() && i == 0 && j == 0) {
            espaciosVacios.clear();
        }
        // Si hemos recorrido todo el tablero, imprimir los espacios vacíos y salir
        if (i >= tablero.length) {
            System.out.println("Espacios vacios: " + espaciosVacios);
            return;
        }
        // Si hemos llegado al final de una fila, movernos a la siguiente fil
        if (j >= tablero[i].length) {
            System.err.println("\n");
            visualizacionRecursiva(tablero, espaciosVacios, i + 1, 0);// Llamada recursiva para la siguiente fila
            return;
        }
        // Imprimir el valor de la celda, si está vacío, imprimir un guion
        System.out.print((tablero[i][j] == null ? "-" : tablero[i][j]) + "    ");
        // Si la celda está vacía, agregarla a la lista de espacios vacíos
        if (tablero[i][j] == null) {
            ArrayList<Integer> empthySpots = new ArrayList<>();
            empthySpots.add(i); // Agregar la fila
            empthySpots.add(j); // Agregar la columna
            // Si el espacio no está ya en la lista de espacios vacíos, agregarlo
            if (!espaciosVacios.contains(empthySpots)) {
                espaciosVacios.add(empthySpots);
            }
        }
        // Llamada recursiva para la siguiente celda en la misma fila
        visualizacionRecursiva(tablero, espaciosVacios, i, j + 1);
    }

    // Función para realizar un movimiento aleatorio
    public static void randomMove(String[][] tablero, ArrayList<ArrayList<Integer>> espaciosVacios, String rival) {
        Random random = new Random(); // Crear un objeto Random para generar números aleatorios
        // Seleccionar un espacio aleatorio de la lista de espacios vacíos
        int ram = random.nextInt(espaciosVacios.size());

        ArrayList<Integer> pick = espaciosVacios.get(ram); // Obtener las coordenadas del espacio aleatorio
        tablero[pick.get(0)][pick.get(1)] = rival; // Marcar el espacio con la marca del rival
        espaciosVacios.remove(pick); // Eliminar el espacio de la lista de espacios vacíos

    }

    // Función para realizar un movimiento "inteligente" (selección de un espacio preferido)
    public static void smartMove(String[][] tablero, String challenger) {
        Random random = new Random(); // Crear un objeto Random para generar números aleatorios

        // Elegir un movimiento basado en un número aleatorio entre 0 y 4
        switch (random.nextInt(5)) {
            case 0 -> {
                if (tablero[0][0] == null) { // Si el espacio (0,0) está vacío
                    tablero[0][0] = challenger; // Marcar el espacio con la marca del jugador
                } else {
                    smartMove(tablero, challenger); // Si el espacio está ocupado, intentar de nuevo
                }
            }
            case 1 -> {
                if (tablero[2][0] == null) { // Si el espacio (2,0) está vacío
                    tablero[2][0] = challenger; // Marcar el espacio con la marca del jugador
                } else {
                    smartMove(tablero, challenger); // Si el espacio está ocupado, intentar de nuevo
                }
            }
            case 2 -> {
                if (tablero[0][2] == null) { // Si el espacio (0,2) está vacío
                    tablero[0][2] = challenger; // Marcar el espacio con la marca del jugador
                } else {
                    smartMove(tablero, challenger); // Si el espacio está ocupado, intentar de nuevo
                }
            }
            case 3 -> {
                if (tablero[2][2] == null) { // Si el espacio (2,2) está vacío
                    tablero[2][2] = challenger; // Marcar el espacio con la marca del jugador
                } else {
                    smartMove(tablero, challenger); // Si el espacio está ocupado, intentar de nuevo
                }
            }
            case 4 -> {
                if (tablero[1][1] == null) { // Si el espacio (1,1) está vacío
                    tablero[1][1] = challenger; // Marcar el espacio con la marca del jugador
                } else {
                    smartMove(tablero, challenger); // Si el espacio está ocupado, intentar de nuevo
                }
            }
        }
    }
}
