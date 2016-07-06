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

    }

    public enum Options {
        GET_TIME,
        GET_DATE,
        GET_TIMESTAMP,
        HELLO,
        GET_VERSION,
        BYE;
    }
}
