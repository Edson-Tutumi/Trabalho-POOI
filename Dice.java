import java.util.Random;

/**
 * Classe que simula a rolagem de um dado para testes de habilidade no jogo.
 */

public class Dice {

    private int numFaces;
    private Random random;
    /**
     * Construtor do Dado.
     *
     * @param numFaces A quantidade de faces que o dado terá.
     */
    public Dice(int numFaces){
        this.numFaces = numFaces;
        this.random = new Random();
    }

    /**
     * Rola o dado e retorna um valor aleatório.
     *
     * @return Um número inteiro entre 1 e o número de faces do dado.
     */

    public int roll(){
        return this.random.nextInt(numFaces) + 1;
    }
}