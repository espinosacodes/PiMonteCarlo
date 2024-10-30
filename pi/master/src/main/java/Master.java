

public class Master {
    public static void main(String[] args) {
        try(com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args)) {
            com.zeroc.Ice.ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("MasterAdapter", "default -p 10000");
            com.zeroc.Ice.Object masterObj = new MasterI();
            adapter.add(masterObj, com.zeroc.Ice.Util.stringToIdentity("Master"));
            adapter.activate();
            System.out.println("Master está listo y esperando numeros...");
            communicator.waitForShutdown();
            
        }
    }
}
