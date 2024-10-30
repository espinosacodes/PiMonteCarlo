import java.util.Scanner;

public class Client
{
    public static void main(String[] args) {      
        try(com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args))
        {
            com.zeroc.Ice.ObjectPrx base = communicator.stringToProxy("Master:default -p 10000");
            Demo.MasterPrx master = Demo.MasterPrx.checkedCast(base);
            if(master == null) {
                throw new Error("Invalid proxy");
            }

            // Pedir al usuario que ingrese el valor de N
            Scanner scanner = new Scanner(System.in);
            System.out.print("Introduce el numero N: ");
            int N = scanner.nextInt();

            // Enviar el número N al maestro y recibir la confirmación
            String response = master.sendNumber(N);
            System.out.println("Respuesta del maestro: " + response);
            scanner.close();
        }
    }
}
