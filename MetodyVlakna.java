import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;
/**
 * T��da s metody na proch�zen� a vyhled�v�n� prvk� v poli, podle zadan�ch parametr�.
 * @author David �ih�k
 *
 */
public class MetodyVlakna {
	/**
	 *  Metoda na vyhled�n� v�ech hledan�ch prvk�.
	 * @param list Pole 
	 * @param predicate Vrac� true/false, pokud projde lambda podm�nka na vstupu.
	 * @param pocetVlaken Po�et vl�ken, na kter�ch bude metoda b�et.
	 * @return V�echny nalezen� prvky
	 * @throws InterruptedException
	 */
	public static ArrayList<Tenisky> Find(List<Tenisky> list, Predicate<Tenisky> predicate, int pocetVlaken) throws InterruptedException {
		Thread[] poleVlaken = new Thread[pocetVlaken];
		ArrayList <Tenisky> vysledky = new ArrayList<Tenisky>();
		for (int i = 0; i < pocetVlaken; i++) {				//P��d�v� vl�kna do pole dokud jich nen� po�adovan� po�et.
			int from = (list.size() * i) / pocetVlaken; 	//V�po�et toho, kde m� dan� vl�kno za��t.
			int to = (list.size() * (i + 1)) / pocetVlaken; //V�po�et toho, kde m� dan� vl�kno skon�it.
			Thread t = new Thread(() -> Metody.Find(list, predicate, from, to, vysledky));
			poleVlaken[i] = t;								//do pole vl�ken se na index i ulo�� vl�kno.
			t.start();										//spu�t�n� vl�kna.
		}
		for (Thread vlakno : poleVlaken) {
			vlakno.join();
		}
		return vysledky;
	}/**
	 * Metoda, kter� nalezne prvn� z hledan�ch prvk� v poli.
	 * @param list Pole
	 * @param predicate Vrac� true/false, pokud projde lambda podm�nka na vstupu.
	 * @param pocetVlaken Po�et vl�ken, na kter�ch bude metoda b�et.
	 * @return Prvn� nalezen� prvek
	 * @throws InterruptedException
	 */
	public static Tenisky FindFirst(List<Tenisky> list, Predicate<Tenisky> predicate, int pocetVlaken) throws InterruptedException {
		Thread[] poleVlaken = new Thread[pocetVlaken];
		AtomicReference <Tenisky> vysledek = new AtomicReference<Tenisky>(null);
		for (int i = 0; i < pocetVlaken; i++) {
			int from = (list.size() * i) / pocetVlaken;
			int to = (list.size() * (i + 1)) / pocetVlaken;
			Thread t = new Thread(() -> Metody.FindFirst(list, predicate, from, to, vysledek));
			poleVlaken[i] = t;
			t.start();
		}
		for (Thread vlakno : poleVlaken) {
			vlakno.join();
		}
		return vysledek.get();

	}
	/**
	 * Metoda, kter� vr�t� po�et hledan�ch prvk� v poli.
	 * @param list Pole
	 * @param predicate Vrac� true/false, pokud projde lambda podm�nka na vstupu.
	 * @param pocetVlaken Po�et vl�ken, na kter�ch bude metoda b�et.
	 * @return Po�et prvk� z pole, kter� hled�me.
	 * @throws InterruptedException
	 */
	public static int Count(List<Tenisky> list, Predicate<Tenisky> predicate, int pocetVlaken) throws InterruptedException {
		Thread[] poleVlaken = new Thread[pocetVlaken];
		AtomicReference <Integer> vysledek = new AtomicReference<Integer>(0);
		for (int i = 0; i < pocetVlaken; i++) {
			int from = (list.size() * i) / pocetVlaken;
			int to = (list.size() * (i + 1)) / pocetVlaken;
			Thread t = new Thread(() -> Metody.Count(list, predicate, from, to, vysledek));
			poleVlaken[i] = t;
			t.start();
		}
		for (Thread vlakno : poleVlaken) {
			vlakno.join();
		}
		return vysledek.get();


	}
}
