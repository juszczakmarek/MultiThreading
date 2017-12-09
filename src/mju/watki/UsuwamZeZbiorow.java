package watki;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
Napisz program który równoczeœnie wypisuje na ekran losowy element z nastêpuj¹cych zbiorów
Q, W, E, R, T, Y
1, 2, 3, 4, 5, 6
^, &, *, (, #
Wypisanie elementu usuwa go ze zbioru
Program koñczy dzia³anie gdy wszystkie elementy zostan¹ wypisane

W jaki inny sposób mozna przekazac referencje zbioru do watku?
W jaki inny sposob mozna zainicjalizowac zbiory?
Jak wyeliminowac zmienne i bloki statyczne?
 * @author mj
 *
 */
public class UsuwamZeZbiorow {
	//Instancja zbioru 1
	private static Set<String> zbior1 = new HashSet<>();
	//Wypelnienie zbioru danymi
	static {
		zbior1.add("Q");
		zbior1.add("W");
		zbior1.add("E");
		zbior1.add("R");
		zbior1.add("T");
		zbior1.add("Y");
	}

	public static void main(String[] args) {
		//Watek dla zbioru
		Thread watekZbioru1 = new Thread(new Runnable(){
			@Override
			public void run() {
				//Pobieramy iterator
				Iterator<String> iterator1 = zbior1.iterator();
				//Dopoki iterator moze zwrocic element
				while(iterator1.hasNext()){
					//Pobierz i wypisz element
					System.out.println(iterator1.next());
					//Usun element
					iterator1.remove();
				}
			}
		});
		//Wystartuj watek
		watekZbioru1.start();
	}
}