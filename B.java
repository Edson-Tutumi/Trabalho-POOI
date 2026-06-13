/**
 * Representa a entidade "Deus" como um personagem jogável (bastante apelão).
 * Implementa a interface {@link Descritivel}.
 */

public class B extends Character implements Descritivel{

    /**
     * Exibe no console a história de fundo do personagem "Deus".
     */
    @Override
    public void showBackgroundHistory(){
        System.out.println("Deus\n");
    }

    /**
     * Retorna uma frase de apresentação especial do personagem "Deus".
     * * @return Uma {@code String} com a saudação inicial do personagem.
     */
    @Override
    public String sayName(){
        return "sons angelicais meow meow meow sons angelicais";
    }

    /**
     * Retorna a descrição do personagem.
     * * @return Uma {@code String} descrevendo as características de "Deus".
     */
    public String getDescricao(){
        return "Ow\n";
    }

    /**
     * Construtor do personagem "Deus".
     *
     * @param name Nome do personagem.
     * @param statLuck Nível de sorte.
     * @param statStrenght Nível de força.
     * @param statIntelligence Nível de inteligência.
     * @param statResistance Nível de resistência.
     */
    public B(String name, int statLuck, int statStrenght, int statIntelligence, int statResistance){
        super(name, statLuck, statStrenght, statIntelligence, statResistance);
    }
}