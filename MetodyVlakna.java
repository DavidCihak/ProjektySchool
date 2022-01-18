import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;
/**
 * Tøída s metody na procházení a vyhledávání prvkù v poli, podle zadaných parametrù.
 * @author David Èihák
 *
 */
public class MetodyVlakna {
	/**
	 *  Metoda na vyhledání všech hledaných prvkù.
	 * @param list Pole 
	 * @param predicate Vrací true/false, pokud projde lambda podmínka na vstupu.
	 * @param pocetVlaken Poèet vláken, na kterých bude metoda bìžet.
	 * @return Všechny nalezené prvky
	 * @throws InterruptedException
	 */
	public static ArrayList<Tenisky> Find(List<Tenisky> list, Predicate<Tenisky> predicate, int pocetVlaken) throws InterruptedException {
		Thread[] poleVlaken = new Thread[pocetVlaken];
		ArrayList <Tenisky> vysledky = new ArrayList<Tenisky>();
		for (int i = 0; i < pocetVlaken; i++) {				//Pøídává vlákna do pole dokud jich není požadovaný poèet.
			int from = (list.size() * i) / pocetVlaken; 	//Výpoèet toho, kde má dané vlákno zaèít.
			int to = (list.size() * (i + 1)) / pocetVlaken; //Výpoèet toho, kde má dané vlákno skonèit.
			Thread t = new Thread(() -> Metody.Find(list, predicate, from, to, vysledky));
			poleVlaken[i] = t;								//do pole vláken se na index i uloží vlákno.
			t.start();										//spuštìní vlákna.
		}
		for (Thread vlakno : poleVlaken) {
			vlakno.join();
		}
		return vysledky;
	}/**
	 * Metoda, která nalezne první z hledaných prvkù v poli.
	 * @param list Pole
	 * @param predicate Vrací true/false, pokud projde lambda podmínka na vstupu.
	 * @param pocetVlaken Poèet vláken, na kterých bude metoda bìžet.
	 * @return První nalezený prvek
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
	 * Metoda, která vrátí poèet hledaných prvkù v poli.
	 * @param list Pole
	 * @param predicate Vrací true/false, pokud projde lambda podmínka na vstupu.
	 * @param pocetVlaken Poèet vláken, na kterých bude metoda bìžet.
	 * @return Poèet prvkù z pole, které hledáme.
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
