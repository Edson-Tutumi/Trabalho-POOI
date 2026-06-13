/**
 * Representa o personagem genérico (lilbro), simpático e forte, mas não muito inteligente.
 * Implementa a interface {@link Descritivel}.
 */

public class C extends Character implements Descritivel{

    /**
     * Exibe no console a história de fundo do personagem genérico.
     */
    @Override
    public void showBackgroundHistory(){
        System.out.println("exatamente o que esta escrito\n");
    }

    /**
     * Retorna a descrição do personagem.
     * * @return Uma {@code String} descrevendo as características do personagem.
     */
    public String getDescricao(){
        return "simpático, forte, porém não muito inteligente\n";
    }

    /**
     * Construtor do personagem genérico.
     *
     * @param name Nome do personagem.
     * @param statLuck Nível de sorte.
     * @param statStrenght Nível de força.
     * @param statIntelligence Nível de inteligência.
     * @param statResistance Nível de resistência.
     */
    public C(String name, int statLuck, int statStrenght, int statIntelligence, int statResistance){
        super(name, statLuck, statStrenght, statIntelligence, statResistance);
    }
}