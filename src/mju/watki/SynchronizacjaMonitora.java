package mju.watki;

//5. Zmodyfikuj tak kod w zadaniu 4.
//Utworz w klasie metodę statyczną synchronizowaną o nazwie metodaSynchronizowana. Zrefaktoruj tak kod by
//w run nastapilo wywolanie tej metody. A metodaSynchronizowana wykonala wszystkie operacji z wczesniejszej
// wersji run. Usun blok synchronized z ciala metody.

import java.util.Random;

public class SynchronizacjaMonitora {

    private static String monitor = "";
    private static int wartoscWspoldzielona = 0;
    private static Random random = new Random();

    public static void main(String[] args) {


        for (int i = 0; i < 10; i++) {
//            final int iterator = i;
            new Thread(() -> {
                doOperation();
            }).start();
        }
    }


    public synchronized static void doOperation() {
        try {
            System.out.println("(ID) = " + Thread.currentThread().getId() + ", wartoscWspoldzielona = " + wartoscWspoldzielona);
            Thread.sleep(3000);
            int randomInt = random.nextInt(100);
            System.out.println("(ID) = " + Thread.currentThread().getId() + ", wartoscLosowa = " + randomInt);
            System.out.print("(ID) = " + Thread.currentThread().getId()+ ", " + wartoscWspoldzielona + " + " + randomInt + " = ");
            wartoscWspoldzielona+=randomInt;
            System.out.print(wartoscWspoldzielona);
            System.out.println("\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
