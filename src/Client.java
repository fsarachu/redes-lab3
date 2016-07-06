import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {

        // Chequeo que haya pasado dos parámetros
        if (args.length != 2) {
            System.out.println("ERROR: pasar el host y puerto del servidor como argumentos. \n" +
                    "Ejemplo: java Client localhost 1234");
            System.exit(0);
        }

        System.out.println("Conectando a " + args[0] + ":" + args[1] + " ...");
        Socket socket = new Socket(args[0], Integer.parseInt(args[1]));

        try {
            // Conexión lograda
            System.out.println("Conexión establecida con " + socket.getRemoteSocketAddress() + "\n");

            // Obtengo streams de entrada y salida
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                System.out.println("> SERVIDOR: " + out.toString());
                in.readLine();
            }

        } finally {
            socket.close();
        }
    }
}
