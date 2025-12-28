package javacode;

public class Singletonalternative {

    private static volatile Singletonalternative instance;

    private Singletonalternative() {}

    public static Singletonalternative getInstance() {

        if (instance == null) {
            synchronized (Singletonalternative.class) {
                if (instance == null) {
                    instance = new Singletonalternative();
                }
            }
        }
        return instance;
    }
}
