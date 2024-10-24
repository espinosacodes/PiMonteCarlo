package montecarlo;

import Ice.Communicator;
import Ice.ObjectAdapter;

public class MaestroServer {
    public static void main(String[] args) {
        Communicator ic = null;
        try {
            ic = Ice.Util.initialize(args);
            ObjectAdapter adapter = ic.createObjectAdapterWithEndpoints("MaestroAdapter", "default -p 10000");
            adapter.add(new MaestroI(), Ice.Util.stringToIdentity("Maestro"));
            adapter.activate();
            System.out.println("Maestro is running...");
            ic.waitForShutdown();
        } finally {
            if (ic != null) {
                ic.destroy();
            }
        }
    }
}
