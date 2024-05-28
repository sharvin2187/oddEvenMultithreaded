import java.util.concurrent.Semaphore;

public class OddEvenWithSemaphore extends OddEven {
    int upto, turn = 1, curr = 0;
    Semaphore odd  = new Semaphore(0), even = new Semaphore(1);

    OddEvenWithSemaphore(int upto) {
        this.upto = upto;
    }

    private void printOdd() throws InterruptedException {
        boolean setBreak = false;
        while (true) {
            odd.acquire();
        if (++curr <= upto) {
            print(curr);
        } else {
            setBreak = true;
        }
            even.release();
            if (setBreak) {
                break;
            }
        }
    }

    private void printEven() throws InterruptedException {
        boolean setBreak = false;
        while (true) {
            even.acquire();
            if (++curr <= upto) {
                print(curr);
            } else {
                setBreak = true;
            }
            odd.release();
            if (setBreak) {
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
                } catch (InterruptedException e) {
                }
            }
        })).start();
        (new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    printEven();
                } catch (InterruptedException e) {
                }
            }
        })).start();
    }
}
