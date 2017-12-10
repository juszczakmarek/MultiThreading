package mju.watki;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorCollable {
    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Future<String> wynikCallable = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return "zwracam wynik z call";
            }
        });
        System.out.println("Gotowy wynik? " + wynikCallable.isDone());
        while (!wynikCallable.isDone()) {
            Thread.sleep(1000);
        }
        System.out.println("Gotowy wynik? " + wynikCallable.isDone());
        System.out.println("Wynik " + wynikCallable.get());

        wynikCallable = executorService.submit(() -> {
            Thread.sleep(1000);
            return "zwracam wynik z drugiego call";
        });
        String wynik = wynikCallable.get();
        System.out.println("Gotowy wynik? " + wynikCallable.isDone());
        System.out.println("Wynik " + wynik);

        wynikCallable = executorService.submit(() -> {
            Thread.sleep(1000);
            return "zwracam wynik z trzeciego call";
        });
        //a nie interesuje mnie ten wynik juz...
        //wyslij interrupt jesli flaga ustawiona na true
        wynikCallable.cancel(true);
        try{
            System.out.println("Wynik po cancel" + wynikCallable.get());
        }catch(Exception e){
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
