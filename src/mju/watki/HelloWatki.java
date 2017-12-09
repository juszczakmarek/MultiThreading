package mju.watki;


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

 * @author mju
 *
 */
public class HelloWatki {
	
	public static void main(String[] args) {
		//tworzenie watku przez uzycie klasy dziedziczacej po Thread
		Thread poThread = new DziedziczePoThread();
		poThread.start();

		//tworzenie watku przez uzycie klasy implementujacej interface Runnable
		Thread runnableImpl = new Thread(new RunnableImpl());
		runnableImpl.start();

		//tworzenie watku przez uzycie klasy anonimowej dziedziczacej po Thread
		Thread newAnonymousExtThread = new Thread() {
			@Override
			public void run() {
				System.out.println("Jestem watkiem dziedziczacym po Thread i mam ID = " + Thread.currentThread().getId());
			}
		};
		newAnonymousExtThread.start();

		//tworzenie watku przez uzycie klasy anonimowej implementujacej interface Runnable
		Runnable runnableAnonymousImpl = new Runnable() {
			@Override
			public void run() {
				System.out.println("Jestem watkiem dziedziczacym po Thread i mam ID = " + Thread.currentThread().getId());
			}
		};
		runnableAnonymousImpl.run();
	}
}

//Klasa NIE ANONIMOWA dziedziczaca po Thread
class DziedziczePoThread extends Thread{
	public void run(){
		System.out.println("Jestem watkiem dziedziczacym po Thread i mam ID = " + Thread.currentThread().getId());
	}
}

//Klasa NIE ANONIMA implementująca interface Runnable
class RunnableImpl implements Runnable {
	@Override
	public void run() {
		System.out.println("Jestem nową klasą implementującą po Thread i mam ID = " +
				Thread.currentThread().getId());
	}
}