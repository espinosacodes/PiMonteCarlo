public class PiexampleI{

    public String sendNumber(int N, com.zeroc.Ice.Current current) {
        System.out.println("Número recibido: " + N);
        return "Número " + N + " recibido";
    }
}
