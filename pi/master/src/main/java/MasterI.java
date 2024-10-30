import com.zeroc.Ice.*;
import java.util.ArrayList;
import java.util.List;

public class MasterI implements Demo.Master {
    private final List<Demo.WorkerPrx> workers = new ArrayList<>();

    public void registerWorker(Demo.WorkerPrx worker, com.zeroc.Ice.Current current) {
        workers.add(worker);
        System.out.println("Worker registrado.");
    }

    public String sendNumber(int N, com.zeroc.Ice.Current current) {
        System.out.println("Número recibido en Master: " + N);
        for (Demo.WorkerPrx worker : workers) {
            worker.receiveNumber(N); // Enviar N a cada Worker
        }
        return "Número " + N + " distribuido a Workers";
    }
}
