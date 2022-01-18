/**
 * Tøída, ve ktrá vytváøí speciální datový typ (enum) -> Kategorie.
 * @author David Èihák
 *
 */
public enum Kategorie {
	PANSKE("Pánské"), DAMSKE("Dámské"), DETSKE("Dìtské");
//Založení tøí typù promìnných.

	public final String label;//
/**
 * Konstruktor pro založení komentáøe k proìnným typu enum.
 * @param label Text k dané kategorii.
 */
	private Kategorie(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return label;
	}
}

