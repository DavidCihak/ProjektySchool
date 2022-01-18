import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;

/**
 * T��da s metody, kter� si rozd�l� pole podle po�tu vl�ken.
 * @author David �ih�k
 *
 */
public class Metody {
	/**
	 * Metoda p�i�ad� vl�knu rozsah z pole, kter� bude proch�zet.
	 * @param list Pole
	 * @param predicate Vrac� true/false, pokud projde lambda podm�nka na vstupu.
	 * @param from Index prvku od kter�ho vl�kno �a�ne proch�zet(v�etn�).
	 * @param to Index prvku kde vl�kno skon��(krom�).
	 * @param vysledky 
	 */
	public static void Find(List<Tenisky> list, Predicate<Tenisky> predicate, int from, int to, ArrayList<Tenisky> vysledky) { 
		for (Tenisky t : list.subList(from, to)) {	//Dokud je objekt v listu na pozici z dan�ho rozsahu.
			if (predicate.test(t)) {				//Testuje jeden objekt tenisky podle predik�tu, ve kter�m je zadan� lambda.
				synchronized (vysledky) {			//Ohl�d�, aby s vysledky mohla pracovat v danou chv�li pouze dan� vl�kna.
					vysledky.add(t);				//p�id� do pole vysledek ten dan� nalezen� prvek.
				}
			}
		}
	}
	/**
	 * 
	 * @param list Pole
	 * @param predicate Vrac� true/false, pokud projde lambda podm�nka na vstupu.
	 * @param from Index prvku od kter�ho vl�kno �a�ne proch�zet(v�etn�).
	 * @param to Index prvku kde vl�kno skon��(krom�).
	 * @param atomic
	 */
	public static void FindFirst(List<Tenisky> list, Predicate<Tenisky> predicate, int from, int to, AtomicReference<Tenisky> atomic) {												
		for (Tenisky t : list.subList(from, to)) {	//Dokud je objekt v listu na pozici z dan�ho rozsahu.
			if(atomic.get() != null) {				//Pokud m� prom�nn� atomic u� n�jakou hodnotu, tak ji vr�t� a ukon�� metodu.
				return;
			}
			if (predicate.test(t)) {				//Testuje jeden objekt tenisky podle predik�tu, ve kter�m je zadan� lambda.
				atomic.set(t);						//Pokud projde lambda test tak nastav� prom�nnou atomic na tu danou tenisku.
				return;								//Atomic slou�� v tomto p��pad� k tomu, aby dv� vl�kna nep�idala najednou tenisku.
			}
		}
	}
	/**
	 * Metoda p�i�ad� vl�knu rozsah z pole, kter� bude proch�zet.
	 * @param list Pole
	 * @param predicate Vrac� true/false, pokud projde lambda podm�nka na vstupu.
	 * @param from Index prvku od kter�ho vl�kno �a�ne proch�zet(v�etn�).
	 * @param to Index prvku kde vl�kno skon��(krom�).
	 * @param vysledky 
	 */
	public static void Count(List<Tenisky> list, Predicate<Tenisky> predicate, int from, int to, AtomicReference<Integer> atomic) {
		int pocet = 0;								//Zalo�en� prom�nn� pro po�et nalezen�ch prvk�.
		for (Tenisky t : list.subList(from, to)) {	//Dokud je objekt v listu na pozici z dan�ho rozsahu.
			if (predicate.test(t)) {				//Testuje jeden objekt tenisky podle predik�tu, ve kter�m je zadan� lambda.
				pocet++;							//Pokud test projde p�i�te se k po�tu nalezen�ch prvk� jedna.
			}
		}
		synchronized(atomic) {						//Ohl�d�, aby s vysledky mohla pracovat v danou chv�li pouze dan� vl�kna.
		atomic.set(atomic.get()+pocet);				//K po�tu u� nalezen�ch tenisek se p�i�te po�et, kter� na�la zbyl� vl�kna.
		}
	}
}
