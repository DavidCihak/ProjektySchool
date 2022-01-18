import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;

/**
 * Tøída s metody, které si rozdìlí pole podle poètu vláken.
 * @author David Èihák
 *
 */
public class Metody {
	/**
	 * Metoda pøiøadí vláknu rozsah z pole, který bude procházet.
	 * @param list Pole
	 * @param predicate Vrací true/false, pokud projde lambda podmínka na vstupu.
	 * @param from Index prvku od kterého vlákno žaène procházet(vèetnì).
	 * @param to Index prvku kde vlákno skonèí(kromì).
	 * @param vysledky 
	 */
	public static void Find(List<Tenisky> list, Predicate<Tenisky> predicate, int from, int to, ArrayList<Tenisky> vysledky) { 
		for (Tenisky t : list.subList(from, to)) {	//Dokud je objekt v listu na pozici z daného rozsahu.
			if (predicate.test(t)) {				//Testuje jeden objekt tenisky podle predikátu, ve kterém je zadaná lambda.
				synchronized (vysledky) {			//Ohlídá, aby s vysledky mohla pracovat v danou chvíli pouze daná vlákna.
					vysledky.add(t);				//pøidá do pole vysledek ten daný nalezený prvek.
				}
			}
		}
	}
	/**
	 * 
	 * @param list Pole
	 * @param predicate Vrací true/false, pokud projde lambda podmínka na vstupu.
	 * @param from Index prvku od kterého vlákno žaène procházet(vèetnì).
	 * @param to Index prvku kde vlákno skonèí(kromì).
	 * @param atomic
	 */
	public static void FindFirst(List<Tenisky> list, Predicate<Tenisky> predicate, int from, int to, AtomicReference<Tenisky> atomic) {												
		for (Tenisky t : list.subList(from, to)) {	//Dokud je objekt v listu na pozici z daného rozsahu.
			if(atomic.get() != null) {				//Pokud má promìnná atomic už nìjakou hodnotu, tak ji vrátí a ukonèí metodu.
				return;
			}
			if (predicate.test(t)) {				//Testuje jeden objekt tenisky podle predikátu, ve kterém je zadaná lambda.
				atomic.set(t);						//Pokud projde lambda test tak nastaví promìnnou atomic na tu danou tenisku.
				return;								//Atomic slouží v tomto pøípadì k tomu, aby dvì vlákna nepøidala najednou tenisku.
			}
		}
	}
	/**
	 * Metoda pøiøadí vláknu rozsah z pole, který bude procházet.
	 * @param list Pole
	 * @param predicate Vrací true/false, pokud projde lambda podmínka na vstupu.
	 * @param from Index prvku od kterého vlákno žaène procházet(vèetnì).
	 * @param to Index prvku kde vlákno skonèí(kromì).
	 * @param vysledky 
	 */
	public static void Count(List<Tenisky> list, Predicate<Tenisky> predicate, int from, int to, AtomicReference<Integer> atomic) {
		int pocet = 0;								//Založení promìnné pro poèet nalezených prvkù.
		for (Tenisky t : list.subList(from, to)) {	//Dokud je objekt v listu na pozici z daného rozsahu.
			if (predicate.test(t)) {				//Testuje jeden objekt tenisky podle predikátu, ve kterém je zadaná lambda.
				pocet++;							//Pokud test projde pøiète se k poètu nalezených prvkù jedna.
			}
		}
		synchronized(atomic) {						//Ohlídá, aby s vysledky mohla pracovat v danou chvíli pouze daná vlákna.
		atomic.set(atomic.get()+pocet);				//K poètu už nalezených tenisek se pøiète poèet, který našla zbylá vlákna.
		}
	}
}
