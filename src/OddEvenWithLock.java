import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenWithLock extends OddEven {
    int upto, turn = 1, curr = 0;
    ReentrantLock turnLock = new ReentrantLock();
    Condition turnCond = turnLock.newCondition();

    OddEvenWithLock(int upto) {
        this.upto = upto;
    }

    private void printOdd() throws InterruptedException {
        boolean setBreak = false;
        while(true) {
            turnLock.lock();
            try {
                while (turn != 1) {
                    turnCond.await();
                }
                if(++curr<=upto) {
                    print(curr);
                } else {
                    setBreak = true;
                }
            } finally {
                turn = 2;
                turnCond.signal();
                turnLock.unlock();
            }
            if(setBreak) {
                break;
            }
        }
    }

    private void printEven() throws InterruptedException {
        boolean setBreak = false;
        while(true) {
            turnLock.lock();
            try {
                while (turn != 2) {
                    turnCond.await();
                }
                if(++curr<=upto) {
                    print(curr);
                } else {
                    setBreak = true;
                }
            } finally {
                turn = 1;
                turnCond.signal();
                turnLock.unlock();
            }
            if(setBreak) {
                break;
            }
        }
    }

    @Override
    public void start() {
        (new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    printOdd();
                } catch (InterruptedException e) {}
            }
        })).start();
        (new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    printEven();
                } catch (InterruptedException e) {}
            }
        })).start();
    }
}
