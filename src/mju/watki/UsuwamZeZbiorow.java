package watki;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 Napisz program który równocześnie wypisuje na ekran losowy element z następujących zbiorów
 Q, W, E, R, T, Y
 1, 2, 3, 4, 5, 6
 ^, &, *, (, #
 Wypisanie elementu usuwa go ze zbioru
 Program kończy działanie gdy wszystkie elementy zostaną wypisane

W jaki inny sposób mozna przekazac referencje zbioru do watku?
W jaki inny sposob mozna zainicjalizowac zbiory?
Jak wyeliminowac zmienne i bloki statyczne?
 * @author mj
 *
 */
public class UsuwamZeZbiorow {
	//Instancja zbioru 1
	private static Set<String> zbiorString = new HashSet<>();
	//Wypelnienie zbioru danymi
	static {
		//Q, W, E, R, T, Y,
		zbiorString.add("Q");
		zbiorString.add("W");
		zbiorString.add("E");
		zbiorString.add("R");
		zbiorString.add("T");
		zbiorString.add("Y");
	}


	private static Set<Integer> zbiorInteger = new HashSet<>();
	static {
		//1, 2, 3, 4, 5, 6
		zbiorInteger.add(1);
		zbiorInteger.add(2);
		zbiorInteger.add(3);
		zbiorInteger.add(4);
		zbiorInteger.add(5);
		zbiorInteger.add(6);
	}

	private static Set<Character> zbiorCharacter = new HashSet<>();
	static {
		//^, &, *, (, #
		zbiorCharacter.add('^');
		zbiorCharacter.add('&');
		zbiorCharacter.add('*');
		zbiorCharacter.add('(');
		zbiorCharacter.add('#');
	}


	public static void main(String[] args) {
		//Watek dla zbioru
		Thread watekZbioruString = new Thread(new Runnable(){
			@Override
			public void run() {
				//Pobieramy iterator
				Iterator<String> iterator1 = zbiorString.iterator();
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
		watekZbioruString.start();

		Thread watekZbioruInteger = new Thread (() -> {

			//Pobieramy iterator
			Iterator<Integer> iterator2 = zbiorInteger.iterator();
			//Dopoki iterator moze zwrocic element
			while(iterator2.hasNext()){
				//Pobierz i wypisz element
				System.out.println(iterator2.next());
				//Usun element
				iterator2.remove();
			}
		});
		watekZbioruInteger.start();

		Thread watekZbioruCharacter = new Thread (() -> {

			//Pobieramy iterator
			Iterator<Character> iterator3 = zbiorCharacter.iterator();
			//Dopoki iterator moze zwrocic element
			while(iterator3.hasNext()){
				//Pobierz i wypisz element
				System.out.println(iterator3.next());
				//Usun element
				iterator3.remove();
			}
		});

		watekZbioruCharacter.start();

		//wersja z usypaniem watku main
//		try {
//			System.out.println("start of the main thread, and sleep for 1 s");
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		System.out.println("zbiorString isEmpty = "+ zbiorString.isEmpty());
//		System.out.println("zbiorInteger isEmpty = "+ zbiorInteger.isEmpty());
//		System.out.println("zbiorCharacter isEmpty = "+ zbiorCharacter.isEmpty());

		//wersja z join watkow
		try {
			watekZbioruString.join();
			watekZbioruCharacter.join();
			watekZbioruInteger.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("zbiorString isEmpty = "+ zbiorString.isEmpty());
		System.out.println("zbiorInteger isEmpty = "+ zbiorInteger.isEmpty());
		System.out.println("zbiorCharacter isEmpty = "+ zbiorCharacter.isEmpty());

	}
}