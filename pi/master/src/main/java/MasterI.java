
import Demo.WorkerPrx;

public class MasterI implements Demo.Master {
    int startWorkerPort = 10001;
    int portWorker = 10000;
    
    public String sendNumber(int N, com.zeroc.Ice.Current current) {
        System.out.println("Número recibido en Master: " + N);

        for (int port = startWorkerPort; port <= portWorker; port++) {
            String workerProxyStr = "Worker:default -p " + port;
            try {
                // Crear un proxy para el Worker en el puerto actual
                com.zeroc.Ice.ObjectPrx workerProxy = current.adapter.getCommunicator().stringToProxy(workerProxyStr);
                Demo.WorkerPrx worker = Demo.WorkerPrx.checkedCast(workerProxy);
                
                if (worker != null) {
                    worker.receiveNumber(N); // Enviar N al Worker en el puerto actual
                    System.out.println("Número " + N + " enviado al Worker en el puerto: " + port);
                } else {
                    System.out.println("No se pudo conectar con el Worker en el puerto: " + port);
                }
            } catch (Exception e) {
                System.out.println("Error enviando el número al Worker en el puerto " + port + ": " + e.getMessage());
            }
        }
        return "Número " + N + " enviado a todos los Workers activos en el rango de puertos.";
    }

    public int receiveWorker(int i, com.zeroc.Ice.Current current){
        portWorker += i;
        return portWorker;
    }
    
}
