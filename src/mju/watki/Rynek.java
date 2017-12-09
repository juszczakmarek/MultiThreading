package watki;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Rynek {

	private Map<String, BigDecimal> notowania = new HashMap<>();
	
	public void aktualizujNotowania(){
		
	}

	public static void main(String[] args) {
	}

}

class Aktualizator{
	private BigDecimal pobierzAktualnaWartosc(){
		Random random = new Random();
		try{
			Thread.sleep(random.nextInt(5) * 1000);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		return new BigDecimal(random.nextInt(10) * random.nextInt(10));
	}
}