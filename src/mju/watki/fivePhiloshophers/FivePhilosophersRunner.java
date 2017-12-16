package mju.watki.fivePhiloshophers;

public class FivePhilosophersRunner {

    static TableForPhilosophers table = new TableForPhilosophers();

    public static void main(String[] args) {

        Philosopher philosopher1 = new Philosopher(1);
        Philosopher philosopher2 = new Philosopher(2);
        Philosopher philosopher3 = new Philosopher(3);
        Philosopher philosopher4 = new Philosopher(4);
        Philosopher philosopher5 = new Philosopher(5);

        Fork fork1 = new Fork(1);
        Fork fork2 = new Fork(2);
        Fork fork3 = new Fork(3);
        Fork fork4 = new Fork(4);
        Fork fork5 = new Fork(5);

        System.out.println(fork1);
        System.out.println(fork2);
        System.out.println(fork3);
        philosopher1 = philosopher1.eat(fork1,fork2);
        philosopher2 = philosopher2.eat(fork1,fork3);
        System.out.println(fork1);
        System.out.println(fork2);
        System.out.println(fork3);
    }

}
