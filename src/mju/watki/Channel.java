/**
 * Zamodeluj nastepujacy system
 * Producent produkuje produkty
 * Konsument kupuje produkty
 * Producent i konsument sa polaczeni przez kanal sprzedazy
 * Producent sygnalizuje chec sprzedazy produktu poprzez umieszczenie go w kanale sprzedazy
 * Konsument sygnalizje chec  kupna poprzez pobranie produktu z kanalu.
 * <p>
 * W kanale moze znajdowac sie tylko jeden produkt
 * Produkt mozna umiescic w kanale tylko wtedy gdy kanal jest pusty
 * Produkt mozna kupic tyko wtedy gdy znajduje sie w kanale
 */

package mju.watki;

import java.util.ArrayList;
import java.util.List;

public class Channel {
    private Product product;

    public void add(Product product) {
        System.out.println("Add product: " + product);
        this.product = product;
    }

    public Product get() {
        System.out.println("Get product: " + product);
        Product localProduct = product;
        product = null;
        return localProduct;
    }

    public boolean isBusy() {
        return product != null;
    }

    public static void main(String[] args) throws InterruptedException {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Vodka"));
        products.add(new Product("Beer"));
        products.add(new Product("Harring"));
        products.add(new Product("Pickle"));

        Channel channel = new Channel();
        Producer producer = new Producer(channel, products);
        Consumer consumer = new Consumer(channel);

        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread consumerThread = new InterruptedThread(consumer);
//        consumerThread.setDaemon(true);
        consumerThread.start();

        while (shouldContinue(products.size(), consumer.getAmountOfBought())) {
            Thread.sleep(1000);
        }
        System.out.println("------------------");
        consumerThread.interrupt();
        //System.exit(0);
    }

    private static boolean shouldContinue(int size, int amountOfBought) {
        if (size == amountOfBought) {
            return false;
        }
        return true;
    }
}

class Producer implements Runnable {
    private Channel channel;
    private List<Product> products;
    private int amountOfSold;

    public Producer(Channel channel, List<Product> products) {
        this.channel = channel;
        this.products = products;
    }

    void putInChannel(Product product) {
        while (true) {
            synchronized (this) {
                if (!channel.isBusy()) {
                    channel.add(product);
                    amountOfSold++;
                    break;
                }
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        for (Product product : products) {
            this.putInChannel(product);
        }
    }

    @Deprecated
    int getAmountOfSold() {
        return amountOfSold;
    }
}

class Consumer implements Runnable {
    private Channel channel;
    private boolean interrupted;

    public int getAmountOfBought() {
        return amountOfBought;
    }

    private int amountOfBought;

    public Consumer(Channel channel) {
        this.channel = channel;
    }

    void buy() {
        while (true) {
            synchronized (this){
                if (channel.isBusy()) {
                    channel.get();
                    amountOfBought++;
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (interrupted) {
                break;
            }
        }
    }

    @Override
    public void run() {
        buy();
    }

    public void interrupt() {
        interrupted = true;
    }
}

class Product {
    private String name;

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                '}';
    }
}

class InterruptedThread extends Thread {
    private Consumer consumer;
    public InterruptedThread(Consumer consumer){
        super(consumer);
        this.consumer = consumer;
    }
    @Override
    public void interrupt(){
        consumer.interrupt();
        System.out.println("Interrupt");
    }
}