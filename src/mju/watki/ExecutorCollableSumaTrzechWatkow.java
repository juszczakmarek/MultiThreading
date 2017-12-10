package mju.watki;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorCollableSumaTrzechWatkow {

    static Random random = new Random();

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        int result = 0;
        boolean shouldContinue = true;

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Future<Integer> executor1 = executorService.submit(() -> {
            return generateNumber();
        });

        Future<Integer> executor2 = executorService.submit(() -> {
            return generateNumber();
        });

        Future<Integer> executor3 = executorService.submit(() -> {
            return generateNumber();
        });

        result = executor1.get()+executor2.get()+executor3.get();
        System.out.println("Wynik = " + result);

        executorService.shutdown();

    }

    private static int generateNumber() throws InterruptedException {
        int tempRandom = random.nextInt(100);
        System.out.println("Random = " + tempRandom);
        Thread.sleep(1000);
        return tempRandom;
    }

}
