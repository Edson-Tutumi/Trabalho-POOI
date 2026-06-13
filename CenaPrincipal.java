import java.util.*;

/**
 * Classe principal responsável por inicializar e gerenciar o loop do jogo.
 * Instancia os mapas, controla o inventário, gerencia a criação de personagens e
 * processa o fluxo de navegação e testes de sorte pelos cenários (Stages).
 */
public class CenaPrincipal {

    /**
     * Construtor padrão (não utilizado, pois a classe só expõe o método {@code main}).
     */
    private CenaPrincipal() {
    }

    /**
     * Método de entrada da aplicação.
     * Realiza a configuração inicial dos cenários (Stages) e suas conexões,
     * solicita ao jogador a escolha de um personagem (tratando entradas inválidas
     * através de {@link PersonagemInvalidoExcecao} e {@link NumberFormatException}),
     * e executa o loop principal do jogo: exibe o cenário atual, coleta itens disponíveis,
     * apresenta as opções de caminho, realiza a rolagem de dados ({@link Dice}) para
     * determinar sucesso ou falha (tratando escolhas inválidas através de
     * {@link EscolhaInvalidaExcecao}), e avança para o próximo Stage de acordo com o resultado.
     * Ao final, exibe o histórico de cenários visitados e os itens coletados durante a jornada.
     *
     * @param args Argumentos de linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        HashMap<String, Boolean> itens = new HashMap<>();
        ArrayList<String> historicoStages = new ArrayList<>();
        ArrayList<String> inventario = new ArrayList<>();
        Dice dado = null;
        Scanner scanner = new Scanner(System.in);

        Stage vilarejo = new Stage(
                "Um vilarejo comum",
                "Abrindo seus olhos, voce se encontra em um vilarejo. A princesa desapareceu, e sua missão eh encontra-la",
                "Coroa cintilante"
        );
        vilarejo.adicionaCaminho("Ir em direcao a floresta", "floresta", "floresta", 20);
        vilarejo.adicionaCaminho("Ir em direcao ao templo", "templo", "floresta", 20);

        Stage templo = new Stage(
                "Um templo de alguma religiao desconhecida por voce",
                "Entao voce pensa, ou so quer fazer speedrun mesmo. De qualquer jeito, escolha seu caminho",
                "Nenhum"
        );
        templo.adicionaCaminho("Entrar no templo", "fimIdiota", "fimDevoto", 20);
        templo.adicionaCaminho("Ir em direcao ao castelo abandonado", "castelo", "fimDevoto", 20);

        Stage castelo = new Stage(
                "Um castelo abandonado a muito tempo",
                "Devo dizer, te acompanhar eh bem menos torturante que os jogadoress que escolheram a floresta. oops, talvez eu tenha falado demais :)",
                "Nenhum"
        );

        castelo.adicionaCaminho("Subir a torre", "castelo_torre", "castelo_torre", 20);

        Stage floresta = new Stage(
                "Uma floresta densa",
                "Saindo do vilarejo, voce chega em uma floresta, qual seu caminho?",
                "Nenhum"
        );

        floresta.adicionaCaminho("Esquerda", "floresta_sideb", "fimAnimal", 20);
        floresta.adicionaCaminho("Centro", "fimAnimal", "fimAnimal", 20);
        floresta.adicionaCaminho("Direita", "templo", "floresta_sideb", 20);

        Stage fimAnimal = new Stage("Game Over", "Voce foi atacado por um animal e nao resistiu", "Nenhum");

        Stage floresta_sideb = new Stage(
                "Uma floresta densa. A luz do sol quase nao passa pelas arvores",
                "Tem certeza que a princesa estaria em uma floresta? Voce nao esta perdido, certo?",
                "Nenhum"
        );

        floresta_sideb.adicionaCaminho("Ir mais fundo", "floresta_sideb", "fimAnimal", 20);
        floresta_sideb.adicionaCaminho("Entrar em uma caverna", "caverna", "fimCaverna", 20);

        Stage fimCaverna = new Stage("Game Over", "A caverna desabou em cima de voce. Isso deve doer", "Nenhum");

        Stage caverna = new Stage(
                "Uma caverna nao mapeada",
                "Parabens, voce com certeza esta perdido. Quem em sa consciencia entraria em uma caverna que nao foi mapeada?",
                "Lanterna"
        );

        caverna.adicionaCaminho("Sair", "floresta_sideb", "fimCaverna", 100);
        caverna.adicionaCaminho("Ir mais fundo", "caverna_sideb", "fimCaverna", 20);

        Stage caverna_sideb = new Stage(
                "Uma caverna nao mapeada",
                "E ai, genio? Alguma ideia brilhante?",
                "Lanterna quebrada"
        );

        caverna_sideb.adicionaCaminho("Ir mais fundo", "fimCaverna", "fimMonstro", 20);
        caverna_sideb.adicionaCaminho("Parede falsa?", "templo_interior", "fimCaverna", 20);

        Stage fimMonstro = new Stage("Game Over", "Uma criatura desconhecida rasgou voce ao meio", "Nenhum");

        Stage templo_interior = new Stage(
                "Um templo de alguma religiao desconhecida por voce",
                "Voce conseguiu sair da caverna, surpreendentemente, e agora?",
                "Nenhum"
        );

        templo_interior.adicionaCaminho("Sair do templo", "templo_sideb", "fimDevoto", 20);
        templo_interior.adicionaCaminho("Explorar o templo", "fimDevoto", "fimDevoto", 20);

        Stage fimDevoto = new Stage("Game Over", "Um seguidor devoto teve seu ritual interrompido por voce, acidentalmente, claro. Voce eh o sacrificio", "Nenhum");

        Stage templo_sideb = new Stage(
                "Lado exterior do templo",
                "Isso tudo poderia ter sido bem mais rapido se voce usasse o cerebro, sabia?",
                "Nenhum"
        );

        templo_sideb.adicionaCaminho("Voltar ao templo", "fimIdiota", "fimDevoto", 20);
        templo_sideb.adicionaCaminho("Seguir em direcao ao castelo abandonado", "castelo_sideb", "fimDevoto", 20);

        Stage fimIdiota = new Stage("Game Over", "Um seguidor devoto teve seu ritual interrompido por voce, nao acidentalmente. Voce eh o sacrificio. Oq vc achou que aconteceria?", "Nenhum");

        Stage castelo_sideb = new Stage(
                "Um castelo abandonado a muito tempo",
                "Isso tudo poderia ter sido bem mais rapido se voce usasse o cerebro, sabia? Enfim, e agora?",
                "Nenhum"
        );

        castelo_sideb.adicionaCaminho("Subir a torre", "castelo_torre", "castelo_torre", 20);
        castelo_sideb.adicionaCaminho("Entrar em um quarto", "castelo_quarto", "fimAranha", 20);

        Stage fimAranha = new Stage("Game Over", "Uma aranha te assustou e voce infartou. Sim, so isso mesmo", "Nenhum");

        Stage castelo_quarto = new Stage(
                "Um quarto de um castelo abandonado a muito tempo",
                "Sinceramente, o que voce esperava?",
                "Nenhum"
        );

        castelo_quarto.adicionaCaminho("Sair do quarto", "castelo_sideb", "fimAranha", 20);

        Stage castelo_torre = new Stage(
                "Uma torre em ruinas, a princesa se encontra sentada perto de uma janela",
                "...",
                "Nenhum"
        );

        castelo_torre.adicionaCaminho("Falar com ela", "fim", "fim", 20);
        Stage fim = new Stage("Espera, o que eh aquilo- DIOGO??", "Diogo derruba uma parede mostrando que era apenas um cenario fake. Decepcionante? Talvez, mas eh oq eh", "Nenhum");



        // ---------------------------------COLOCAR NO HASHMAP OS LOCAIS COM O DEVIDO NOME
        HashMap<String, Stage> stages = new HashMap<>();

        stages.put("inicio", vilarejo);
        stages.put("floresta", floresta);
        stages.put("templo", templo);
        stages.put("castelo", castelo);
        stages.put("floresta_sideb", floresta_sideb);
        stages.put("caverna", caverna);
        stages.put("caverna_sideb", caverna_sideb);
        stages.put("templo_interior", templo_interior);
        stages.put("templo_sideb", templo_sideb);
        stages.put("castelo_sideb", castelo_sideb);
        stages.put("castelo_quarto", castelo_quarto);
        stages.put("castelo_torre", castelo_torre);

        stages.put("fimAnimal", fimAnimal);
        stages.put("fimCaverna", fimCaverna);
        stages.put("fimMonstro", fimMonstro);
        stages.put("fimDevoto", fimDevoto);
        stages.put("fimIdiota", fimIdiota);
        stages.put("fimAranha", fimAranha);

        // ------------------------------SELECAO DE PERSONAGEM COM EXCECAO
        System.out.println("Escolha seu personagem: 1 Han Jisoo, 2 DEUS ou 3 lilbro:");
        Character personagem = null;
        while (personagem == null) {
            try {
                int escolhaPersonagem = Integer.parseInt(scanner.nextLine());
                if (escolhaPersonagem == 1)
                    personagem = new A("Han Jisoo", 7, 3, 7, 5);
                else if (escolhaPersonagem == 2)
                    personagem = new B("Deus", 99, 99, 99, 99);
                else if (escolhaPersonagem == 3)
                    personagem = new C("Generic Hero", 3, 9, 2, 7);
                else
                    throw new PersonagemInvalidoExcecao(escolhaPersonagem);
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero valido");
            } catch (PersonagemInvalidoExcecao e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Historia: ");
        personagem.showBackgroundHistory();

        if (personagem instanceof Descritivel) { // cast pra conseguir chamar o metodo
            System.out.println("Descricao: \n" + ((Descritivel) personagem).getDescricao());
        }

        personagem.showStats();

        System.out.println("Ola, esperamos por voce por muito tempo, nosso heroi " + personagem.getName() + " prossiga em sua aventura (:");

        System.out.println(personagem.sayName());

        System.out.println("------------------------------------------");

        // --------------------------------JOGO-------------------------------------------------------------------------------------
        String idStageAtual = "inicio";
        boolean funcionando = true;

        while (funcionando) {
            Stage stageAtual = stages.get(idStageAtual);

            //historicoStages
            historicoStages.add(idStageAtual);

            String itemDoCenario = stageAtual.getItem();

            if (itemDoCenario != null && !itemDoCenario.equals("Nenhum") && !inventario.contains(itemDoCenario)) {
                inventario.add(itemDoCenario);
                System.out.println("Você encontrou e guardou o item: [" + itemDoCenario + "]");
            }

            System.out.println("\n==========================================");
            System.out.println(stageAtual.showMessage());
            System.out.println(stageAtual.showMessageLocutor());
            System.out.println("==========================================");

            //se comecar com fim a chave o jogo acaba
            if (idStageAtual.startsWith("fim")) {
                funcionando = false;
                break;
            }

            System.out.println("Escolha seu caminho:");
            stageAtual.mostraOpcoes();

            // ------------------------------ESCOLHA DO CAMINHO COM EXCECAO
            try {
                int escolha = Integer.parseInt(scanner.nextLine());

                if (!stageAtual.temOpcao(escolha)) {
                    throw new EscolhaInvalidaExcecao(escolha);
                }

                dado = new Dice(stageAtual.getDado(escolha));
                int resultadoDado = dado.roll();

                System.out.println("voce rolou um D" + stageAtual.getDado(escolha) + " e tirou: " + resultadoDado);

                boolean vitoria = resultadoDado >= (stageAtual.getDado(escolha) / 2);

                if (vitoria) {
                    System.out.println("Sucesso");
                    idStageAtual = stageAtual.getDestino(escolha);
                } else {
                    System.out.println("Falha");
                    idStageAtual = stageAtual.getDestinoPerder(escolha);
                }

            } catch (NumberFormatException e) {
                System.out.println("Digite um numero valido");
            } catch (EscolhaInvalidaExcecao e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("pog acabo (:");

        System.out.println("Seu histórico de aventura foi:");

        for(String h: historicoStages){
            System.out.print(" -> " + h);
        }

        System.out.println("\nItens coletados durante a jornada:");
        if (inventario.isEmpty()) {
            System.out.println(" -> Nenhum item coletado. Triste.");
        } else {
            for (String item : inventario) {
                System.out.println(" -> [" + item + "]");
            }
        }
        scanner.close();
    }
}