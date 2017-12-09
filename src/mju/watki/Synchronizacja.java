package watki;

/**
 * Synchronizacja z uzyciem bloku synchronized.
 * Zaobserwuj co jest semaforem/obiektem lokowanym
 * Co siê stanie jezeli blok synchronized zostanie zakomentowany?
 * Co siê stanie jezeli blok synchronized zostanie zakomentowany 
 *  a metoda wypiszWPetli zostanie oznaczona jako synchronized?
 * @author mj
 *
 */
public class Synchronizacja {

	public static void main(String[] args) throws Exception {
		Boolean semafor = Boolean.TRUE;
		new Thread(new WatekZRunnable("A", semafor)).start();
		new Thread(new WatekZRunnable("B", semafor)).start();
		new Thread(new WatekZRunnable("C", semafor)).start();
		new Thread(new WatekZRunnable("D", semafor)).start();
	}
}

class WatekZRunnable implements Runnable {
	String nazwa;
	Object semafor;
	public WatekZRunnable(String nazwa, Object semafor) {
		this.nazwa = nazwa;
		this.semafor = semafor;
	}
	public void run() {
		synchronized (semafor) {
			wypiszWPetli();
		}
	}
	private void wypiszWPetli() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Watek: " + nazwa + " " + i);
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}
}
