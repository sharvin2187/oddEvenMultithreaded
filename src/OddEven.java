public abstract class OddEven {
    public abstract void start();
    public void print(int x) {
        System.out.println("From thread: " + Thread.currentThread().getName() + " : " + x);
    }
}
