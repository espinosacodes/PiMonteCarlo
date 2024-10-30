public class Worker {
    public static void main(String[] args) {
        try (com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args)) {

            com.zeroc.Ice.ObjectPrx base = communicator.stringToProxy("Master:default -p 10000");
            Demo.MasterPrx master = Demo.MasterPrx.checkedCast(base);
            if (master == null) {
                throw new Error("Invalid proxy");
            }

            // Registrar el Worker en el Master
            //ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("WorkerAdapter", "default");
            //Demo.Worker worker = new WorkerI();
            //Demo.WorkerPrx workerPrx = Demo.WorkerPrx.checkedCast(adapter.add(worker, Util.stringToIdentity("Worker" + System.currentTimeMillis())));
            //adapter.activate();

            // Registrar Worker en el Master
            //master.registerWorker(workerPrx);
            String response = master.sendNumber(1);
            System.out.println(response);
            // Esperar hasta que se cierre
            communicator.waitForShutdown();
        }
    }
}