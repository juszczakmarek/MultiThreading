package mju.watki;

public class FivePhilosophers {

}

class Philosopher {
    private Fork leftFork = null;
    private Fork rightFork = null;
    private boolean thinks = false;
    private boolean eats = false;

    public boolean isThinkin() {
        return thinks;
    }

    public void setThinks(boolean thinks) {
        this.thinks = thinks;
    }

    public boolean isEating() {
        return eats;
    }

    public void setEats(boolean eats) {
        this.eats = eats;
    }

    public Fork getLeftFork() {
        return leftFork;
    }

    public void setLeftFork(Fork leftFork) {
        this.leftFork = leftFork;
    }

    public Fork getRightFork() {
        return rightFork;
    }

    public void setRightFork(Fork rightFork) {
        this.rightFork = rightFork;
    }
}

class Fork {

}
