/**
 * T��da, pro vytvo�en� objektu Tenisek.
 * Obsahuje konstruktor, toString a metody Set+Get pro prom�nn� id, cena, nazev a pohlavi.
 * @author David �ih�k
 *
 */
public class Tenisky {
	//zalo�en� prom�nn�ch
static int serioveCislo = 0;
private int id = 0;
private int cena;
private String nazev;
private Kategorie pohlavi;

/**
 * Metoda, kter� vrac� pohlav� tenisek.
 * @return Prom�nnou typu Kategorie.
 */
public Kategorie getPohlavi() {
	return pohlavi;
}
/**
 * Metoda, kter� vrac� nastav� tenisek.
 * 
 */
public void setPohlavi(Kategorie pohlavi) {
	this.pohlavi = pohlavi;
}
/**
 * Metoda, kter� vrac� pohlav� tenisek.
 * @return Prom�nnou typu String.
 */
public String getNazev() {
	return nazev;
}
/**
 * Metoda, kter� nastav� n�zev tenisek.
 */
public void setNazev(String nazev) {
	this.nazev = nazev;
}
/**
 * Metoda, kter� vrac� cenu tenisek.
 * @return Prom�nnou typu int.
 */
public int getCena() {
	return cena;
}
/**
 * Metoda, kter� nastav� cenu tenisek.
 * @param cena
 */
public void setCena(int cena) {
	this.cena = cena;
}
/**
 * Metoda, kter� vrac� id tenisek.
 * @return Prom�nnou typu int.
 */
public int getId() {
	return id;
}
/**
 * Metoda, kter� nastav� id tenisek.
 * @param id
 */
public void setId(int id) {
	this.id = id;
}
/**
 * Konstruktor, kter� vytv��� objekt pomoc� prom�nn�ch.
 * Vytv�r� unik�tn� ID pro ka�d� jeden vytvo�en� objekt.
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
