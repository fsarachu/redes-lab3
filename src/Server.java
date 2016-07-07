import java.net.*;
import java.io.*;

public class Server extends Thread {

    private ServerSocket serverSocket;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void run() {
        while (true) {
            try {
                // Espero clientes y creo conexión con el que llegue
                System.out.println("\nEscuchando en puerto " + serverSocket.getLocalPort() + "...\n");
                Socket server = serverSocket.accept();
                System.out.println("Conexión establecida con " + server.getRemoteSocketAddress());

                // Obtengo streams
                DataInputStream in = new DataInputStream(server.getInputStream());
                DataOutputStream out = new DataOutputStream(server.getOutputStream());

                // Mantengo conexión hasta recibir "BYE"
                boolean stay = true;

                while (stay) {

                    // Leo mensaje
                    String msgFromClient = in.readUTF();
                    System.out.println("> Cliente: " + msgFromClient);

                    if (msgFromClient.equalsIgnoreCase("BYE")) {
                        // Si dijo BYE me voy
                        stay = false;
                    } else {
                        // Sino Respondo
                        String msgToClient = msgFromClient.toUpperCase();
                        out.writeUTF(msgToClient);
                    }
                }

                // Cierro conexión
                System.out.println("Cerrando conexión con " + server.getRemoteSocketAddress());
                server.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        int port = Integer.parseInt(args[0]);

        try {
            Thread t = new Server(port);
            t.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private enum Options {
        GET_TIME,
        GET_DATE,
        GET_TIMESTAMP,
        HELLO,
        GET_VERSION,
        BYE;
    }
}