package mju.watki;

//6. Napisz program ktory zsumuje liczby wygenerowane przez trzy watki. Kazdy z watkow spi losowa ilosc sekund.
// Nastepnie generuje liczbę. Wypisz na ekran wylosowana liczbe. A na koniec dzialania programu wypisz sume liczb.
//Wzorzec obserwable

import java.util.Random;

public class SumaTrzechWatkow {

    private static Random random = new Random();
    private static int liczba1 = 0;
    private static int liczba2 = 0;
    private static int liczba3 = 0;


    public static void main(String[] args) {

        Random random = new Random();

        new Thread ( () -> {
                SumaTrzechWatkow.liczba1=random.nextInt();
                try {
                    Thread.sleep(random.nextInt(5000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        ).start();

        new Thread ( () -> {
                SumaTrzechWatkow.liczba2=random.nextInt();
                try {
                    Thread.sleep(random.nextInt(5000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        ).start();

        new Thread ( () -> {
                SumaTrzechWatkow.liczba3=random.nextInt();
                try {
                    Thread.sleep(random.nextInt(5000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        ).start();

        System.out.println("Suma wygenerowanych liczb = " + SumaTrzechWatkow.liczba1 + SumaTrzechWatkow.liczba2 + SumaTrzechWatkow.liczba3);
    }
}
