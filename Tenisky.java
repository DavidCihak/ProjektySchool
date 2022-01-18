/**
 * Tøída, pro vytvoøení objektu Tenisek.
 * Obsahuje konstruktor, toString a metody Set+Get pro promìnné id, cena, nazev a pohlavi.
 * @author David Èihák
 *
 */
public class Tenisky {
	//založení promìnných
static int serioveCislo = 0;
private int id = 0;
private int cena;
private String nazev;
private Kategorie pohlavi;

/**
 * Metoda, která vrací pohlaví tenisek.
 * @return Promìnnou typu Kategorie.
 */
public Kategorie getPohlavi() {
	return pohlavi;
}
/**
 * Metoda, která vrací nastaví tenisek.
 * 
 */
public void setPohlavi(Kategorie pohlavi) {
	this.pohlavi = pohlavi;
}
/**
 * Metoda, která vrací pohlaví tenisek.
 * @return Promìnnou typu String.
 */
public String getNazev() {
	return nazev;
}
/**
 * Metoda, která nastaví název tenisek.
 */
public void setNazev(String nazev) {
	this.nazev = nazev;
}
/**
 * Metoda, která vrací cenu tenisek.
 * @return Promìnnou typu int.
 */
public int getCena() {
	return cena;
}
/**
 * Metoda, která nastaví cenu tenisek.
 * @param cena
 */
public void setCena(int cena) {
	this.cena = cena;
}
/**
 * Metoda, která vrací id tenisek.
 * @return Promìnnou typu int.
 */
public int getId() {
	return id;
}
/**
 * Metoda, která nastaví id tenisek.
 * @param id
 */
public void setId(int id) {
	this.id = id;
}
/**
 * Konstruktor, který vytváøí objekt pomocí promìnných.
 * Vytvárí unikátní ID pro každý jeden vytvoøený objekt.
 * @param cena
 * @param nazev
 * @param pohlavi
 */
public Tenisky(int cena, String nazev, Kategorie pohlavi) {
	this.id = serioveCislo;
	this.cena = cena;
	this.nazev = nazev;
	this.pohlavi = pohlavi;
	serioveCislo++;
}

@Override
public String toString() {
	return "Tenisky [SN = "+ id + ", cena = " + cena + ", nazev = " + nazev + ", pohlavi = " + pohlavi + "]";
}





}
