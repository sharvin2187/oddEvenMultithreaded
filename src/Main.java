public class Main {
    public static void main(String[] args) {
        OddEven tester = null;
        try {
            tester = OddEvenFactory.get(40, OddEvenWithSemaphore.class);
        } catch (Exception e ) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        tester.start();
    }
}