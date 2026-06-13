/**
 * Exceção customizada disparada quando o jogador seleciona um caminho que não existe no menu da fase atual.
 */

public class EscolhaInvalidaExcecao extends Exception {
    /** O número da opção inválida que foi digitada pelo jogador. */
    private int escolhaFeita;

    /**
     * Construtor da exceção.
     *
     * @param escolhaFeita O número da opção inválida que foi digitada.
     */
    public EscolhaInvalidaExcecao(int escolhaFeita) {
        super("Opcao invalida: " + escolhaFeita + ". Tente novamente.");
        this.escolhaFeita = escolhaFeita;
    }

    /**
     * Retorna a escolha inválida que foi digitada pelo jogador.
     *
     * @return O número da opção inválida.
     */
    public int getEscolhaFeita() {
        return escolhaFeita;
    }
}