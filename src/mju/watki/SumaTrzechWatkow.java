package mju.watki;

//6. Napisz program ktory zsumuje liczby wygenerowane przez trzy watki. Kazdy z watkow spi losowa ilosc sekund.
// Nastepnie generuje liczbę. Wypisz na ekran wylosowana liczbe. A na koniec dzialania programu wypisz sume liczb.
//Wzorzec obserwable

import java.util.Random;

public class SumaTrzechWatkow {

    private static Random random = new Random();
    private static int result = 0;

    public static void main(String[] args) {


        for (int i=0;i<3;i++) {
            new Thread( () -> {
                int randomMS = random.nextInt(5000);
                try {
                    Thread.sleep(randomMS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int randomInt = random.nextInt(100);
                result+=randomInt;
                System.out.println("Random = " + randomInt + ", current result = " + result);
            }).start();

        }

    }

}
