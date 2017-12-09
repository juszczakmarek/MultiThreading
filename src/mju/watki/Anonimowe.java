package watki;

/**
 * Ilustracja tworzenia instancji klas anonimowych
 * @author mj
 *
 */
public class Anonimowe {
	
	public static void main(String[] args) {
		Anonimowe instancjaDziedziczaca = new Anonimowe(){
			//A gdyby tak nadpisaæ metodê z bazowej?
		};
		instancjaDziedziczaca.metodaWKlasie();
		
		Anonimowy instancjaImplementujaca = new Anonimowy() {
			@Override
			public void metodaWInterfejsie() {
				System.out.println("Metoda w Interfejsie");
			}
		};
		instancjaImplementujaca.metodaWInterfejsie();
	}
	
	void metodaWKlasie(){
		System.out.println("Metoda w klasie");
	}

}


interface Anonimowy{
	void metodaWInterfejsie();
}