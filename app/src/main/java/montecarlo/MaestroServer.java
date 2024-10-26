package montecarlo;

import com.zeroc.Ice.Communicator;
import MonteCarlo.TrabajadorPrx;
import com.zeroc.Ice.Current;
import com.zeroc.Ice.Object;
import com.zeroc.Ice.Util;

public class MaestroServer extends MaestroI {
    private final TrabajadorPrx[] trabajadores;

    public MaestroServer(TrabajadorPrx[] trabajadores) {
        this.trabajadores = trabajadores;
    }

    @Override
    public double estimarPi(int N, Current current) {
        int n = trabajadores.length;
        int puntosPorTrabajador = N / n;
        int puntosDentroCirculo = 0;

        // Distribuir trabajo a los trabajadores
        for (TrabajadorPrx trabajador : trabajadores) {
            puntosDentroCirculo += trabajador.simular(puntosPorTrabajador);
        }

        return 4.0 * puntosDentroCirculo / N;  // Estimación de π
    }

    public static void main(String[] args) {
        try (Communicator communicator = Util.initialize(args)) {
            TrabajadorPrx[] trabajadores = {
                    TrabajadorPrx.checkedCast(communicator.stringToProxy("Trabajador1:default -p 10001")),
                    TrabajadorPrx.checkedCast(communicator.stringToProxy("Trabajador2:default -p 10002"))
            };

            MaestroI servant = new MaestroServer(trabajadores);
            communicator.createObjectAdapter("Maestro").add((Object) servant, communicator.stringToIdentity("Maestro"));

            communicator.waitForShutdown();
        }
    }
}
