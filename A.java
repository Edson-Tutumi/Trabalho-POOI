/**
 * Representa a personagem "Han Jisoo", uma colegial do futuro.
 * Implementa a interface {@link Descritivel}.
 */

public class A extends Character implements Descritivel{
    /**
     * Exibe no console a história de fundo da personagem Han Jisoo.
     */
	@Override
	public void showBackgroundHistory(){
		System.out.println("Uma colegial do futuro :0\n");
	}

    /**
     * Retorna a descrição da personagem.
     * * @return Uma {@code String} descrevendo as características de Han Jisoo.
     */
	public String getDescricao(){
		return "estressada, tem um baseball bat e muita sorte\n";
	}

    /**
     * Construtor da personagem Han Jisoo.
     *
     * @param name Nome do personagem.
     * @param statLuck Nível de sorte.
     * @param statStrenght Nível de força.
     * @param statIntelligence Nível de inteligência.
     * @param statResistance Nível de resistência.
     */
	public A(String name, int statLuck, int statStrenght, int statIntelligence, int statResistance){
		super(name, statLuck, statStrenght, statIntelligence, statResistance);
	}
}