/**
 * Classe abstrata que representa a base de todos os personagens do jogo.
 * Contém os atributos básicos de RPG (sorte, força, inteligência, resistência) e os métodos de interação.
 */
public abstract class Character {
    private String name;
    private int statLuck;
    private int statStrenght;
    private int statIntelligence;
    private int statResistance;
    /**
     * Construtor padrão do personagem.
     *
     * @param name nome do personagem.
     * @param statLuck nível de sorte.
     * @param statStrenght nível de força.
     * @param statIntelligence nível de inteligência.
     * @param statResistance nível de resistência.
     */

    public Character(String name, int statLuck, int statStrenght, int statIntelligence, int statResistance){
        this.name = name;
        this.statLuck = statLuck;
        this.statStrenght = statStrenght;
        this.statIntelligence = statIntelligence;
        this.statResistance = statResistance;
    }
    /**
     * Retorna uma frase de apresentação do personagem.
     *
     * @return Uma {@code String} com a saudação inicial do personagem.
     */

    public String sayName(){
        return "Eae eu sou " + name + " me ajude a percorrer o caminho";
    }
    /**
     * Exibe no console os status atuais do personagem.
     */


    public void showStats(){
        System.out.println("Luck: " + statLuck + " | Strenght: " + statStrenght + " | Intelligence: " + statIntelligence + " | Resistance: " + statResistance);
    }

    /**
     * Exibe no console a história de fundo (background) do personagem.
     * Deve ser implementada pelas subclasses específicas.
     */

    public abstract void showBackgroundHistory();

    /**
     * Retorna o valor do status de sorte do personagem.
     *
     * @return O valor do status de sorte do personagem.
     */

    public int getStatLuck(){
        return statLuck;
    }

    /**
     * Retorna o valor do status de força do personagem.
     *
     * @return O valor do status de força do personagem.
     */

    public int getStatStrenght(){
        return statStrenght;
    }

    /**
     * Retorna o valor do status de inteligência do personagem.
     *
     * @return O valor do status de inteligencia do personagem.
     */
    public int getStatIntelligence(){
        return statIntelligence;
    }

    /**
     * Retorna o valor do status de resistência do personagem.
     *
     * @return O valor do status de resistencia do personagem.
     */
    public int getStatResistence(){
        return statResistance;
    }

    /**
     * Retorna o nome do personagem.
     *
     * @return O valor do status de nome do personagem.
     */
    public String getName() {
        return name;
    }
}