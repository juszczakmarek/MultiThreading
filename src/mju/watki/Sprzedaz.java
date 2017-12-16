package mju.watki;

import java.util.Random;

public class Sprzedaz {

    public static void main(String[] args) {
        KanalSprzedazy kanal = new KanalSprzedazy();
        (new Thread(new Producent(kanal))).start();
        (new Thread(new Konsument(kanal))).start();
    }
}

class Producent implements Runnable {

    private KanalSprzedazy kanal;

    Producent(KanalSprzedazy kanal) {
        this.kanal = kanal;
    }

    @Override
    public void run() {
        String produkty[] = { "Kredki", "Piorniki", "Dlugopisy", "Flamastry" };
        Random random = new Random();

        for (int i = 0; i < produkty.length; i++) {
            kanal.wystaw(produkty[i]);
        }
        kanal.wystaw("KONIEC");
    }
}

class Konsument implements Runnable {

    private KanalSprzedazy kanal;

    Konsument(KanalSprzedazy kanal) {
        this.kanal = kanal;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (String nazwa = kanal.kup(); !nazwa.equals("KONIEC"); nazwa = kanal.kup()) {
        }
    }
}

class KanalSprzedazy {

    private String produkt;

    public synchronized void wystaw(String nazwa) {
        while (produkt != null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        produkt = nazwa;
        System.out.format("Wystawiam produkt: %s%n", nazwa);
        notifyAll();
    }

    public String kup() {
        synchronized (this) {
            while (produkt == null) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String doSprzedania = produkt;
            produkt = null;
            System.out.format("Kupiono produkt: %s%n", doSprzedania);
            notifyAll();
            return doSprzedania;
        }
    }
}
