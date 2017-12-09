package mju.watki;

public class RunnableImpl implements Runnable {
    @Override
    public void run() {
        System.out.println("Jestem nową klasą implementującą po Thread i mam ID = " +
        Thread.currentThread().getId());
    }


}
