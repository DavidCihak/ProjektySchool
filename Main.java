import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * T��da obsahuj�c� metodu Main.
 * Ve t��d� je vytvo�eno pole a vlo�eny do n�j objekty/prvky.
 * Test metod ze t��dy MetodyVlakna
 * @author David �ih�k
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		ArrayList<Tenisky> Boty = new ArrayList<Tenisky>();
	
		
		try{		
			BufferedReader Reader = new BufferedReader(new FileReader("C:\\Users\\dwdci\\Desktop\\Parametry.txt")); 

			Kategorie o = null;
			String row;
			Reader.readLine(); //skip header
			while((row = Reader.readLine()) != null) {
				String[] data = row.split(",");
				switch(data[2]) {
					case "PANSKE":
						o = Kategorie.PANSKE;
						break;
					case "DAMSKE":
						o = Kategorie.DAMSKE;
						break;
					case "DETSKE":
						o = Kategorie.DETSKE;
						break;
					default:
						o = Kategorie.DETSKE;
						break;
				}
					
				Tenisky a = new Tenisky(Integer.parseInt(data[0]), data[1], o);
				Boty.add(a);
				//System.out.println(a);

			}
		for (int i = 0; i <= 4; i++) {										// 5*2 = 10 z�znam�
			Boty.add(new Tenisky(15000, "Air Jordan 1 High, Black&Red", Kategorie.PANSKE));
			Boty.add(new Tenisky(8000, "Air Jordan 1 High, Gray", Kategorie.DAMSKE));
		}

		for (int i = 0; i <= 9; i++) {										// 3*10 = 30 zaznam�.
			Boty.add(new Tenisky(3500, "Air Force 1, White", Kategorie.DAMSKE));
			Boty.add(new Tenisky(3500, "Air Force 1, Black", Kategorie.PANSKE));
			Boty.add(new Tenisky(3500, "Superstar, White", Kategorie.PANSKE));
		}
		for (int i = 0; i <= 2; i++) {										// 3*3 = 9 zaznam�.
			Boty.add(new Tenisky(1200, "Slip-On, Chess", Kategorie.PANSKE));
			Boty.add(new Tenisky(1200, "Slip-On, Black", Kategorie.DAMSKE));
			Boty.add(new Tenisky(2200, "Slip-On, Pink", Kategorie.DETSKE));
		}

		Boty.add(new Tenisky(3600, "Forum Exhibit, Mesh", Kategorie.DETSKE));
		Boty.add(new Tenisky(3600, "Forum Exhibit, Matt", Kategorie.PANSKE));
		Boty.add(new Tenisky(3000, "Blazer, Blue", Kategorie.DAMSKE));
		Boty.add(new Tenisky(3000, "Blazer, Brown", Kategorie.PANSKE));
		Boty.add(new Tenisky(2200, "Old Skool B&W, High", Kategorie.DETSKE));
		Boty.add(new Tenisky(2200, "Old Skool B&W, Low", Kategorie.DAMSKE));// celkem 55 z�znam�.
		}
		catch(Exception e) {
			System.out.println("N�co se pokazilo!");
		}
		// Test metody, kter� hled� v�echny z�znamy podle zadan�ch parametr�.
		try {
		ArrayList<Tenisky> vysledek = MetodyVlakna.Find(Boty,
				(bota) -> bota.getPohlavi() == Kategorie.DETSKE, 4);
		for (Tenisky t : vysledek) {
			System.out.println(t);
		}
		}
		catch(Exception e) {
			System.out.println("Metoda Find nejsp�e nefunguje!");
		}

		// Test metody, kter� hled� jeden z�znam podle zadan�ho parametru(ID p�ru bot).
		try {
		Tenisky vysledekFirst = MetodyVlakna.FindFirst(Boty, (bota) -> bota.getId() == 1, 4);
		System.out.println(vysledekFirst);
		}
		catch(Exception e) {
			System.out.println("Metoda FindFirst nejsp�e nefunguje!");
		}
		// Test metody, kter� vyp�e po�et prvk� dan� kategorie(P�nsk�).
		try {
		System.out.print("Po�et bot v zadan� kategorii je: ");
		System.out.println(MetodyVlakna.Count(Boty, (bota) -> bota.getPohlavi() == Kategorie.PANSKE && bota.getCena() == 3500, 4));
		}
		catch(Exception e) {
			System.out.println("Metoda Count nejsp�e nefunguje!");
		}
	}
	}
