package mju.watki.fivePhiloshophers;


import java.util.ArrayList;
import java.util.List;

public class TableForPhilosophers {

    List<Philosopher> philosopherList;
    List<Fork> forkList;

    public TableForPhilosophers() {
        this.philosopherList = new ArrayList<>();
        this.philosopherList.add(new Philosopher(1));
        this.philosopherList.add(new Philosopher(2));
        this.philosopherList.add(new Philosopher(3));
        this.philosopherList.add(new Philosopher(4));
        this.philosopherList.add(new Philosopher(5));

        this.forkList = new ArrayList<>();
        forkList.add(new Fork(1));
        forkList.add(new Fork(2));
        forkList.add(new Fork(3));
        forkList.add(new Fork(4));
        forkList.add(new Fork(5));
    }

    public Philosopher getPhilosopherByNumber(int philosopherNumebr) {
        return this.philosopherList.get(philosopherNumebr);
    }

    public Fork getForkByNumber(int forkNumber) {
        return this.forkList.get(forkNumber);
    }

    public List<Philosopher> getAllPhilosophers() {
        return this.philosopherList;
    }

    public List<Fork> getAllForks() {
        return this.forkList;
    }

}
