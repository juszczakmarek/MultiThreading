package mju.watki.fivePhiloshophers;

public class Philosopher {
    private boolean thinks = false;
    private boolean eats = false;
    private int philosopherNumber;

    public Philosopher(int philosopherNumber) {
        this.philosopherNumber = philosopherNumber;
    }

    public Philosopher eat(Fork leftFork, Fork rightFork) {
        leftFork = leftFork.useFork(this);
        rightFork = rightFork.useFork(this);
        this.eats = true;
        return this;
    }


    public boolean isThinkin() {
        return thinks;
    }

    public boolean isEating() {
        return eats;
    }

    public int getPhilosopherNumber() {
        return philosopherNumber;
    }

    @Override
    public String toString() {
        return "Philosopher(" + philosopherNumber + ") thinks = " + isThinkin() + ", eats = " + isEating();
    }
}
