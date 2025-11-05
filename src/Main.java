import java.util.ArrayList;
import java.util.Random;

public class Main {

    static final int FILAS = 6;
    static final int COLUMNAS = 6;
    static Celula[][] tablero = new Celula[FILAS][COLUMNAS];

    public static void main(String[] args) throws InterruptedException {

        boolean primerCiclo = true;
        boolean jugando = true;

        while (jugando) {

            cargar(primerCiclo);
            primerCiclo = false;

            Thread.sleep(500); // medio segundo entre ciclos

        }

    }

    public static void cargar(boolean primerCiclo) {

        if (primerCiclo) {
            inicializarTablero();
        } else {
            evolucionar();
        }

        dibujarTablero();
    }

    private static void inicializarTablero() {
        Random rand = new Random();

        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                boolean viva = rand.nextInt(2) == 1;
                tablero[i][j] = new Celula(viva);
            }
        }
    }

    private static void dibujarTablero() {
        System.out.println("=========================");

        int saltoLinea = 0;
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                saltoLinea = dibujar(tablero[i][j], saltoLinea);
            }
        }

        System.out.println("=========================");
    }

    public static int dibujar(Celula celula, int saltoLinea) {

        //  salida con 1 y 0
        System.out.print(celula);



        saltoLinea++;

        if (saltoLinea == COLUMNAS) {
            System.out.println();
            saltoLinea = 0;
        }

        return saltoLinea;
    }

    private static void evolucionar() {

        int[][] vecinas = new int[FILAS][COLUMNAS];

        // Contar vecinas antes de modificar nada
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                vecinas[i][j] = contarVecinas(i, j);
            }
        }

        // Aplicar reglas
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                tablero[i][j].cicloVida(vecinas[i][j]);
            }
        }
    }

    private static int contarVecinas(int fila, int col) {

        int total = 0;

        for (int df = -1; df <= 1; df++) {
            for (int dc = -1; dc <= 1; dc++) {

                if (df == 0 && dc == 0)
                    continue;

                int filaVecina = (fila + df + FILAS) % FILAS;
                int colVecina = (col + dc + COLUMNAS) % COLUMNAS;

                if (tablero[filaVecina][colVecina].getVida())
                    total++;
            }
        }

        return total;
    }
}
