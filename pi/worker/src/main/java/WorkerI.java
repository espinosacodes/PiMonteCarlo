import java.security.SecureRandom;

public class WorkerI implements Demo.Worker {

    public int calculatePointsInCircle(int pointsToGenerate, com.zeroc.Ice.Current current) {
        System.out.println("Numero de puntos a generar: " + pointsToGenerate);
        System.out.println("------------------------------------------------------");
        SecureRandom random = new SecureRandom();
        int pointsInCircle = 0;

        for (int i = 0; i < pointsToGenerate; i++) {
            double x = random.nextDouble() * 2 - 1;
            double y = random.nextDouble() * 2 - 1;
            if (x * x + y * y <= 1) {
                pointsInCircle++;
            }
        }

        return pointsInCircle;
    }
}