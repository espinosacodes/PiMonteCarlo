package montecarlo;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Current;
import com.zeroc.Ice.Object;
import com.zeroc.Ice.Util;

import java.util.Random;

public class TrabajadorServer extends TrabajadorI {
    @Override
    public int simular(int puntos, Current current) {
        Random rand = new Random();
        int dentroDelCirculo = 0;

        for (int i = 0; i < puntos; i++) {
            double x = rand.nextDouble() * 2 - 1;
            double y = rand.nextDouble() * 2 - 1;
            if (x * x + y * y <= 1) {
                dentroDelCirculo++;
            }
        }

        return dentroDelCirculo;
    }

    public static void main(String[] args) {
        try (Communicator communicator = Util.initialize(args)) {
            TrabajadorI servant = new TrabajadorServer();
            communicator.createObjectAdapter("Trabajador").add((Object) servant, communicator.stringToIdentity("Trabajador1"));

            communicator.waitForShutdown();
        }
    }
}
