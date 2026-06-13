/**
 * Exceção customizada disparada quando o jogador tenta selecionar um personagem fora das opções permitidas.
 */
public class PersonagemInvalidoExcecao extends Exception {
    /** O número de personagem inválido que foi digitado pelo jogador. */
    private int escolhaFeita;

    /**
     * Construtor da exceção.
     *
     * @param escolhaFeita O número de personagem inválido que foi digitado.
     */
    public PersonagemInvalidoExcecao(int escolhaFeita) {
        super("Personagem invalido: " + escolhaFeita + ". Escolha 1, 2 ou 3.");
        this.escolhaFeita = escolhaFeita;
    }

    /**
     * Retorna a escolha de personagem inválida que foi digitada pelo jogador.
     *
     * @return O número de personagem inválido.
     */
    public int getEscolhaFeita() {
        return escolhaFeita;
    }
}