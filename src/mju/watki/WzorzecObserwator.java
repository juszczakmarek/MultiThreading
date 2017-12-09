package watki;

import java.util.ArrayList;
import java.util.List;

/**
 * Przykladowa realizacja wzorca obserwator
 * Obserwowany - klasa ktora zarzadza obserwatorami, jest zrodlem zdazen
 * Obserwator reaguje na zdazenie
 * Zdazenie obiekt przenoszacy informacje o zdazeniu
 * @author mj
 *
 */
public class WzorzecObserwator implements Obserwowany {

	private List<Obserwator> obserwatorzy = new ArrayList<>();

	public static void main(String[] args) {
		WzorzecObserwator obserwowany = new WzorzecObserwator();
		obserwowany.dodajObserwatora(new Obserwator());
		obserwowany.powiadamiajObserwatorow(new Zdazenie(10));
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
	public void powiadamiajObserwatorow(Zdazenie z) {
		obserwatorzy.stream().forEach(o -> o.obserwuj(z));
	}

}

interface Obserwowany {
	public void dodajObserwatora(Obserwator o);

	public void usunObserwatora(Obserwator o);

	public void powiadamiajObserwatorow(Zdazenie z);
}

class Obserwator {
	public void obserwuj(Zdazenie zdazenie){
		System.out.println("Obserwuje zdazenie " + zdazenie);
	}
}

class Zdazenie {
	
	private int wynik;

	public Zdazenie(int i) {
		wynik = i;
	}
	
	int getWynik(){
		return wynik;
	}
}