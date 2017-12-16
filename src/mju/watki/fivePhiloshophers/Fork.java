package mju.watki.fivePhiloshophers;

public class Fork {

    private int forkNumber;
    private boolean currentlyInUse;
    private Philosopher philosopherUsingFork;

    public Fork(int forkNumber) {
        this.forkNumber = forkNumber;
    }

    public Fork useFork(Philosopher philosopher) {
        if (this.currentlyInUse==true) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Philosopher (");
            stringBuilder.append(philosopher.getPhilosopherNumber());
            stringBuilder.append(")tries to use Fork (");
            stringBuilder.append(this.forkNumber);
            stringBuilder.append(") but it's already in use by Philosopher(");
            stringBuilder.append(this.philosopherUsingFork.getPhilosopherNumber());
            stringBuilder.append(")");
            throw new IllegalStateException(stringBuilder.toString());
        }

        this.philosopherUsingFork = philosopher;
        this.currentlyInUse = true;
        return this;
    }

    @Override
    public String toString() {
        if (philosopherUsingFork !=null || currentlyInUse!=false) {
            return "Fork(" + forkNumber +") in use by " + philosopherUsingFork.toString();
        } else {
            return "Fork(" + forkNumber + ") is not used now";
        }
    }
}
