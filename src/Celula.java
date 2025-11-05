import java.util.Random;

public class Celula {

    private boolean vida;
    private int tiempo;

    public Celula(boolean viva) {
        this.vida = viva;
        this.tiempo = 0;
    }

    public void setVida(boolean vida) {
        this.vida = vida;
    }

    public boolean getVida() {
        return this.vida;
    }

    public String toString() {
        return this.vida ? "1" : "0";
    }

    public void cicloVida(int numeroVecinas) {

        boolean resetearTiempo = true;

        switch (numeroVecinas) {

            case 0, 1, 5, 6, 7, 8:
                this.vida = false;
                resetearTiempo = false;
                break;

            default: // 2, 3, 4 → sobrevive
                this.vida = true;
                break;
        }

        Random rand = new Random();

        // 10% probabilidad de muerte espontánea si está viva
        if (rand.nextInt(10) == 1 && this.vida) {
            this.vida = false;
            resetearTiempo = false;
        }

        // 10% probabilidad de nacimiento espontáneo si está muerta
        else if (rand.nextInt(10) == 1 && !this.vida) {
            this.vida = true;
        }

        if (resetearTiempo) {
            this.tiempo++;
        } else {
            this.tiempo = 0;
        }
    }
}
