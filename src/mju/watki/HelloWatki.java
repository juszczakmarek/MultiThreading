package watki;

import mju.watki.RunnableImpl;

/**
 * 
Hello watki.
Napisz program ktory tworzy nastepujace watki.
Dziedziczacy po Thread.
Klase implementujaca Runnable
Anonimowa klase dziedziczaca po Thread
Anonimowa klase implementujaca runnable
Lambda
Kazdy watek w metodzie run wypisuje stringa opisujacego sposob utworzenia

 * @author mj
 *
 */
public class HelloWatki {
	
	public static void main(String[] args) {
		Thread poThread = new DziedziczePoThread();
		poThread.start();

		//moje

		Thread runnableImpl = new Thread(new RunnableImpl());
		runnableImpl.start();

		Thread newAnonymousExtThread = new Thread() {
			@Override
			public void run() {
				System.out.println("Jestem watkiem dziedziczacym po Thread i mam ID = " + Thread.currentThread().getId());
			}
		};
		newAnonymousExtThread.start();

		Runnable runnableAnonymousImpl = new Runnable() {
			@Override
			public void run() {
				System.out.println("Jestem watkiem dziedziczacym po Thread i mam ID = " + Thread.currentThread().getId());
			}
		};
		runnableAnonymousImpl.run();
	}
}

//Nie anonimowa klasa. Ma nazwe ktorej mozna uzyc z operatorem new
class DziedziczePoThread extends Thread{
	public void run(){
		System.out.println("Jestem watkiem dziedziczacym po Thread i mam ID = " + Thread.currentThread().getId());
	}
}