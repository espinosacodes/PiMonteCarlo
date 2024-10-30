public class Worker {
    public static void main(String[] args) {
        try (com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args)) {

            com.zeroc.Ice.ObjectPrx base = communicator.stringToProxy("Master:default -p 10000");
            Demo.MasterPrx master = Demo.MasterPrx.checkedCast(base);
            if(master == null) {
                throw new Error("Invalid proxy");
            }
            int response = master.receiveWorker(1);
            System.out.println("Worker esperando en el puerto "+response);
            String endPoints = "default -p "+response;

            com.zeroc.Ice.ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("WorkerAdapter", endPoints);
            Demo.Worker workerObj = new WorkerI();
            adapter.add(workerObj, com.zeroc.Ice.Util.stringToIdentity("Worker"));
            adapter.activate();
            System.out.println("Worker iniciado y esperando números...");
            communicator.waitForShutdown();
        }
    }
}