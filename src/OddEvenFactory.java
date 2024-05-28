public class OddEvenFactory {
    public static OddEven get(int upto, Class<? extends OddEven> clazz) {
        if (clazz.equals(OddEvenWithLock.class)) {
            return new OddEvenWithLock(upto);
        } else if (clazz.equals(OddEvenWithSemaphore.class)) {
            return new OddEvenWithSemaphore(upto);
        }

        throw new IllegalArgumentException("No member of given type");
    }
}