import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;


public class Server {
    public static void main(String[] args) throws IOException {

        // Chequeo que haya pasado un parámetro
        if (args.length != 1) {
            System.out.println("ERROR: pasar el puerto como argumento. \n" +
                    "Ejemplo: java Server 1234");
            System.exit(0);
        }

        // Creo socket del servidor
        ServerSocket listener = new ServerSocket(Integer.parseInt(args[0]));

        try {
            // Espero a un cliente y establezco conexión
            System.out.println("Servidor corriendo en puerto " + listener.getLocalPort() + " ...");
            Socket socket = listener.accept();

            try {
                // Obtengo streams de entrada y salida
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                // Entro a loop principal
                boolean stay = true;

                while (stay) {
                    // Mando mensaje al cliente
                    out.print("Ingrese un comando: ");

                    // Obtener mensaje del cliente
                    String input = in.readLine();

                    // Respondo
                    switch (input) {
                        case "GET_TIME":
                            out.println(input.toUpperCase());
                            break;
                        case "GET_DATE":
                            out.println(input.toUpperCase());
                            break;
                        case "GET_TIMESTAMP":
                            out.println(input.toUpperCase());
                            break;
                        case "HELLO":
                            out.println(input.toUpperCase());
                            break;
                        case "GET_VERSION":
                            out.println(input.toUpperCase());
                            break;
                        case "BYE":
                            out.println("Bye bye...");
                            stay = false;
                            break;
                        default:
                            out.println("Opción desconocida.");
                            break;
                    }
                }
            } finally {
                socket.close();
            }
        } finally {
            listener.close();
        }

    }
}
