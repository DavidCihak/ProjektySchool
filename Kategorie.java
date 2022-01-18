/**
 * T��da, ve ktr� vytv��� speci�ln� datov� typ (enum) -> Kategorie.
 * @author David �ih�k
 *
 */
public enum Kategorie {
	PANSKE("P�nsk�"), DAMSKE("D�msk�"), DETSKE("D�tsk�");
//Zalo�en� t�� typ� prom�nn�ch.

	public final String label;//
/**
 * Konstruktor pro zalo�en� koment��e k pro�nn�m typu enum.
 * @param label Text k dan� kategorii.
 */
	private Kategorie(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return label;
	}
}

