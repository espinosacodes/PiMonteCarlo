package montecarlo;

import MonteCarlo.Maestro;
import MonteCarlo.TrabajadorPrx;
import java.util.List;

public class MaestroI extends _MaestroDisp {
    @Override
    public double estimarPi(int N, Ice.Current current) {
        List<TrabajadorPrx> workers = getWorkers();
        int numWorkers = workers.size();
        int pointsPerWorker = N / numWorkers;

        int totalInside = 0;
        for (TrabajadorPrx worker : workers) {
            totalInside += worker.simular(pointsPerWorker);
        }

        return 4.0 * totalInside / N;
    }

    private List<TrabajadorPrx> getWorkers() {
        // Assuming worker proxies are pre-configured or retrieved via ICE registry
        // Add logic to retrieve the list of workers (Trabajador proxies)
    }
}
