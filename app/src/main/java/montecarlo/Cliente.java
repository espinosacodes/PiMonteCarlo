package montecarlo;

import MonteCarlo.MaestroPrx;
import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Util;

public class Cliente {
    public static void main(String[] args) {
        int N = 1000000;  // Total de puntos aleatorios

        try (Communicator communicator = Util.initialize(args)) {
            // Proxy del maestro
            MaestroPrx maestro = MaestroPrx.checkedCast(
                    communicator.stringToProxy("Maestro:default -p 10000")
            );

            if (maestro == null) {
                throw new Error("No se pudo conectar al maestro.");
            }

            // Solicita la estimación de π
            double piEstimation = maestro.estimarPi(N);
            System.out.println("Estimación de π: " + piEstimation);
        }
    }
}
