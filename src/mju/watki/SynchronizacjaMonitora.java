package mju.watki;

//Stworz klase SynchronizacjaMonitora.
//W klasie utwórz pole statyczne typu String i nazwij je monitor
//W metodzie main tej klasy umieść pętlę for która wykona dziesiec iteracji.
//Dla kazdej iteracji utwórz nowy wątek.
//W metodzie run zsynchronizuj się na monitorze, nastepnie uspij watek na dziesiec sekund, na koniec wypisz na ekran:
//a) identyfikator wątku
//b) numer iteracji w której wątek został utworzony
//Wystartuj utworzony wątek
//Jak zachowa się program gdy w metodzie run zakomentujesz sekcję synchronizowana?

public class SynchronizacjaMonitora {

    private static String monitor = "";

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            final int iterator = i;
            new Thread(() -> {
                synchronized (monitor) {
                    try {
                        Thread.sleep(1000);
                        System.out.println("i = " + iterator + " ID = " + Thread.currentThread().getId());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

}
