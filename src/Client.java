import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {

        // Chequeo que haya pasado dos parámetros
        if (args.length != 2) {
            System.out.println("ERROR: pasar el host y puerto del servidor como argumentos. \n" +
                    "Ejemplo: java Client localhost 1234");
            System.exit(0);
        }
        

    }
}
