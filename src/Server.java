import java.io.IOException;
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

        // Comienza a atender
        try {

            boolean stay = true;

            while (stay) {

                System.out.println("Escuchando en puerto " + listener.getLocalPort() + " ...");
                Socket socket = listener.accept();
                System.out.println("Cliente Aceptado!\n");

                try {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println("Cjjj Cjjj, Check Check");

                    String command = "bye";
                    Options option = Options.valueOf(command.toUpperCase());

                    switch (option) {
                        case GET_TIME:
                            break;
                        case GET_DATE:
                            break;
                        case GET_TIMESTAMP:
                            break;
                        case HELLO:
                            break;
                        case GET_VERSION:
                            break;
                        case BYE:
                            System.out.println("Bye bye...");
                            stay = false;
                            break;
                        default:
                            System.out.println("Opción desconocida.");
                            break;
                    }

                } finally {
                    socket.close();
                }
            }

        } finally {
            listener.close();
        }

    }

    public enum Options {
        GET_TIME,
        GET_DATE,
        GET_TIMESTAMP,
        HELLO,
        GET_VERSION,
        BYE
    }
}
