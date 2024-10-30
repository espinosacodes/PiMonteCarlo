
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import Demo.WorkerPrx;

public class MasterI implements Demo.Master {
    int startWorkerPort = 10001;
    int portWorker = 10000;
    private int totalPointsInCircle = 0;
    private int totalPoints;
    
    public String distributeWork(int N, com.zeroc.Ice.Current current) {
        totalPointsInCircle = 0;
        System.out.println("------------------------------------------------------");
        System.out.println("Número recibido en Master: " + N);
        totalPoints = N;

        // Contar el número de Workers conectados
        int connectedWorkers = 0;
        for (int port = startWorkerPort; port <= portWorker; port++) {
            String workerProxyStr = "Worker:default -p " + port;
            try {
                com.zeroc.Ice.ObjectPrx workerProxy = current.adapter.getCommunicator().stringToProxy(workerProxyStr);
                Demo.WorkerPrx worker = Demo.WorkerPrx.checkedCast(workerProxy);

                if (worker != null) {
                    connectedWorkers++;
                }
            } catch (Exception e) {
                System.out.println("Error conectando al Worker en el puerto " + port + ": " + e.getMessage());
            }
        }

        // Verificar si hay Workers conectados
        if (connectedWorkers == 0) {
            return "No hay Workers conectados para procesar la tarea.";
        }

        // Calcular puntos por Worker
        int pointsPerWorker = N / connectedWorkers;
        System.out.println("Puntos asignados a cada Worker: " + pointsPerWorker);

        // Crear un ExecutorService para manejar los hilos
        ExecutorService executor = Executors.newFixedThreadPool(connectedWorkers);
        Future<Integer>[] futures = new Future[connectedWorkers];
        int index = 0;

        for (int port = startWorkerPort; port <= portWorker; port++) {
            String workerProxyStr = "Worker:default -p " + port;
            try {
                com.zeroc.Ice.ObjectPrx workerProxy = current.adapter.getCommunicator().stringToProxy(workerProxyStr);
                Demo.WorkerPrx worker = Demo.WorkerPrx.checkedCast(workerProxy);

                if (worker != null) {
                    // Enviar la tarea al ExecutorService
                    futures[index] = executor.submit(() -> {
                        return worker.calculatePointsInCircle(pointsPerWorker);
                    });
                    index++;
                } else {
                    System.out.println("No se pudo conectar con el Worker en el puerto: " + port);
                }
            } catch (Exception e) {
                System.out.println("Error enviando el número al Worker en el puerto " + port + ": " + e.getMessage());
            }
        }

        // Recoger los resultados de cada Worker
        for (Future<Integer> future : futures) {
            try {
                totalPointsInCircle += future.get();  // Esperar y obtener el resultado
            } catch (Exception e) {
                System.out.println("Error al obtener el resultado del Worker: " + e.getMessage());
            }
        }

        executor.shutdown();  // Apagar el ExecutorService

        return "Número " + N + " distribuido entre " + connectedWorkers + " Workers activos.";
    }


    public double getResult(com.zeroc.Ice.Current current) {
        return 4.0 * totalPointsInCircle / totalPoints;
    }

    public int receiveWorker(int i, com.zeroc.Ice.Current current){
        portWorker += i;
        System.out.println("Worker conectado en el puerto "+portWorker);
        return portWorker;
    }
    
}
