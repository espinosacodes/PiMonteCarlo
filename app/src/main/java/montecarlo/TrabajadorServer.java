package montecarlo;

import Ice.Communicator;
import Ice.ObjectAdapter;

public class TrabajadorServer {
    public static void main(String[] args) {
        Communicator ic = null;
        try {
            ic = Ice.Util.initialize(args);
            ObjectAdapter adapter = ic.createObjectAdapterWithEndpoints("TrabajadorAdapter", "default -p 10001");
            adapter.add(new TrabajadorI(), Ice.Util.stringToIdentity("Trabajador"));
            adapter.activate();
            System.out.println("Trabajador is running...");
            ic.waitForShutdown();
        } finally {
            if (ic != null) {
                ic.destroy();
            }
        }
    }
}
