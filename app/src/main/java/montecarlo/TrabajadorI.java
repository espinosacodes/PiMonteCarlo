package montecarlo;

import MonteCarlo.Trabajador;
import java.util.Random;

public class TrabajadorI extends _TrabajadorDisp {
    @Override
    public int simular(int puntos, Ice.Current current) {
        Random rnd = new Random();
        int inside = 0;
        for (int i = 0; i < puntos; i++) {
            double x = rnd.nextDouble();
            double y = rnd.nextDouble();
            if (Math.sqrt(x * x + y * y) <= 1) {
                inside++;
            }
        }
        return inside;
    }
}
