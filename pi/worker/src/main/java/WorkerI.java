public class WorkerI implements Demo.Worker {

    public void receiveNumber(int N, com.zeroc.Ice.Current current) {
        System.out.println("Número recibido en Worker: " + N);
    }
}