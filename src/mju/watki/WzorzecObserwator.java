package mju.watki;

import java.util.ArrayList;
import java.util.List;

/**
 * Przykladowa realizacja wzorca obserwator
 * Obserwowany - klasa ktora zarzadza obserwatorami, jest zrodlem zdazen
 * Obserwator reaguje na zdazenie
 * Zdarzenie obiekt przenoszacy informacje o zdazeniu
 * @author mj
 *
 */
public class WzorzecObserwator implements Obserwowany {

	private List<Obserwator> obserwatorzy = new ArrayList<>();

	public static void main(String[] args) {
		WzorzecObserwator obserwowany = new WzorzecObserwator();
		obserwowany.dodajObserwatora(new Obserwator());
		obserwowany.powiadamiajObserwatorow(new Zdarzenie(10));
	}

	@Override
	public void dodajObserwatora(Obserwator o) {
		obserwatorzy.add(o);
	}

	@Override
	public void usunObserwatora(Obserwator o) {
		obserwatorzy.remove(o);
	}

	@Override
	public void powiadamiajObserwatorow(Zdarzenie z) {
		obserwatorzy.stream().forEach(o -> o.obserwuj(z));
	}

}

interface Obserwowany {
	public void dodajObserwatora(Obserwator o);
	public void usunObserwatora(Obserwator o);
	public void powiadamiajObserwatorow(Zdarzenie z);
}

class Obserwator {
	public void obserwuj(Zdarzenie zdarzenie){
		System.out.println("Obserwuje zdarzenie " + zdarzenie);
	}
}

class Zdarzenie {
	
	private int wynik;

	public Zdarzenie(int i) {
		wynik = i;
	}
	
	int getWynik(){
		return wynik;
	}
}