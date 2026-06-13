import java.util.HashMap;
import java.util.Map;

/**
 * Representa um cenário ou fase ("Stage") dentro do jogo.
 * Gerencia a descrição do local, itens encontrados e as opções de caminhos disponíveis para o jogador.
 */

public class Stage {
    private String mensagem;
    private String mensagemLocutor;
    private String item;

    //map pra juntar as ideias de escolha por numero, 1 2 3... com os nomes dos caminhos ou locais sla
    private Map<Integer, String> caminhos;
    private Map<Integer, String> caminhosPerder;
    private Map<Integer, Integer> dados;
    //guarda os textos, fala do locutor e descricao
    private Map<Integer, String> textosDasOpcoes;
    private int contadorOpcoes = 1;//o contador serve pra numerar as escolhas de 1 pra frente de cada stage

    /**
     * Construtor principal da fase.
     *
     * @param mensagem Nome ou título do cenário.
     * @param mensagemLocutor Descrição narrativa do local.
     * @param item Item disponível neste cenário (ou "Nenhum" se não houver).
     */
    public Stage(String mensagem, String mensagemLocutor, String item) {
        this.mensagem = mensagem;
        this.mensagemLocutor = mensagemLocutor;
        this.item = item;
        this.caminhos = new HashMap<>();
        this.caminhosPerder = new HashMap<>();
        this.dados = new HashMap<>();
        this.textosDasOpcoes = new HashMap<>();
    }

    /**
     * Adiciona uma nova opção de caminho/escolha para o jogador neste cenário.
     *
     * @param textoOpcao O texto descritivo da escolha (ex: "Ir em direcao a floresta").
     * @param idDestino A chave (ID) do próximo Stage em caso de sucesso no dado.
     * @param idDestinoPerder A chave (ID) do Stage para onde o jogador vai em caso de falha.
     * @param valorDado O número de faces do dado exigido para o teste desta escolha.
     */
    public void adicionaCaminho(String textoOpcao, String idDestino, String idDestinoPerder, int valorDado) {
        textosDasOpcoes.put(contadorOpcoes, textoOpcao);
        caminhos.put(contadorOpcoes, idDestino);
        caminhosPerder.put(contadorOpcoes, idDestinoPerder);
        dados.put(contadorOpcoes, valorDado);
        contadorOpcoes++;
    }

    /**
     * Retorna a mensagem/título principal do cenário.
     *
     * @return Uma {@code String} com o título do cenário.
     */
    public String showMessage() {
        return mensagem;
    }

    /**
     * Retorna a mensagem narrativa (fala do locutor) do cenário.
     *
     * @return Uma {@code String} com a descrição narrativa do local.
     */
    public String showMessageLocutor() {
        return mensagemLocutor;
    }

    //mostra as opcoes de locomocao, caminho 1 2 3 etc
    /**
     * Exibe no console as opções de caminhos mapeadas para esta fase.
     */
    public void mostraOpcoes() {
        for (Map.Entry<Integer, String> opcao : textosDasOpcoes.entrySet()) {
            System.out.println(opcao.getKey() + " - " + opcao.getValue());
        }
    }

    // Verifica se o número digitado corresponde a uma opção válida
    /**
     * Verifica se uma determinada escolha (número digitado) existe neste cenário.
     *
     * @param escolha O número da opção escolhida pelo jogador.
     * @return {@code true} se o caminho existir, {@code false} caso contrário.
     */
    public boolean temOpcao(int escolha) {
        return caminhos.containsKey(escolha);
    }

    /**
     * Retorna o número de faces do dado exigido para o teste de uma determinada escolha.
     *
     * @param escolha O número da opção escolhida pelo jogador.
     * @return O número de faces do dado (ex: 20 para D20, 100 para D100).
     */
    public int getDado(int escolha){
        return dados.get(escolha);
    }

    // Retorna o ID do próximo cenário para onde o jogador vai
    /**
     * Retorna o ID do próximo Stage em caso de sucesso no teste do dado.
     *
     * @param escolha O número da opção escolhida pelo jogador.
     * @return Uma {@code String} com o ID do Stage de destino em caso de vitória.
     */
    public String getDestino(int escolha) {
        return caminhos.get(escolha);
    }

    /**
     * Retorna o ID do próximo Stage em caso de falha no teste do dado.
     *
     * @param escolha O número da opção escolhida pelo jogador.
     * @return Uma {@code String} com o ID do Stage de destino em caso de derrota.
     */
    public String getDestinoPerder(int escolha) {
        return caminhosPerder.get(escolha);
    }

    /**
     * Retorna o item disponível neste cenário.
     *
     * @return Uma {@code String} com o nome do item, ou "Nenhum" caso não haja item.
     */
    public String getItem() {
        return this.item;
    }
}