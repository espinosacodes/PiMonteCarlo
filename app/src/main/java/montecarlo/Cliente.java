package montecarlo;

import Ice.Communicator;
import Ice.ObjectPrx;

public class Cliente {
    public static void main(String[] args) {
        Communicator ic = null;
        try {
            ic = Ice.Util.initialize(args);
            ObjectPrx base = ic.stringToProxy("Maestro:default -p 10000");
            MaestroPrx maestro = MaestroPrxHelper.checkedCast(base);
            if (maestro == null) {
                throw new Error("Invalid proxy");
            }

            // Request estimation of Pi
            double piEstimate = maestro.estimarPi(1000000); // Example: 1 million points
            System.out.println("Estimated Pi: " + piEstimate);
        } finally {
            if (ic != null) {
                ic.destroy();
            }
        }
    }
}
