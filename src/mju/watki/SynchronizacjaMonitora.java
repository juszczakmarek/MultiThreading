package mju.watki;

//Na podstawie zadanie 3 (branch github: Ex03_Synchronizacja01)
//Utwórz w klasie SynchronizacjaMonitora pole statyczne typu int o nazwie wartoscWspoldzielona
//Zmodyfikuj metode run w ten sposob ze zakomentujesz blok synchronized.
//Kolejno, wypiszesz na ekran id watku oraz wartosc pola wartoscWspoldzielona.
//Nastepnie uspisz watek na 3 sekundy.
//Wygenerujesz losowa liczbe z przedzialu od 0 do 100
//Wypiszez na ekran id watku oraz wygenerowana liczbe.
//Nastepnie zwiekszysz wartoscWspoldzielona o wygenerowana liczbe.
//Wypiszesz na ekran id watku oraz wartosc zmiennej wspoldzielonej.
//Czy wyniki zmienia sie jezeli przywrocisz synchronizacje?

import java.util.Random;

public class SynchronizacjaMonitora {

    private static String monitor = "";
    private static int wartoscWspoldzielona = 0;

    public static void main(String[] args) {
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
//            final int iterator = i;
            new Thread(() -> {
                synchronized (monitor) {
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
            }).start();
        }
    }

}
