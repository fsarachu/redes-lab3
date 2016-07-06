import java.net.*;
import java.io.*;

public class Server extends Thread {

    private ServerSocket serverSocket;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10000);
    }

    public void run() {
        while (true) {
            try {
                // Espero clientes
                System.out.println("Escuchando en puerto " + serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();
                System.out.println("Conexión establecida con " + server.getRemoteSocketAddress());

                // Obtengo streams
                DataInputStream in = new DataInputStream(server.getInputStream());
                DataOutputStream out = new DataOutputStream(server.getOutputStream());

                // Leo mensaje y respondo
                System.out.println(in.readUTF());
                out.writeUTF("Gracias por conectarse a " + server.getLocalSocketAddress() + "\nBye!");

                // Cierro conexión
                server.close();

            } catch (SocketTimeoutException s) {
                System.out.println("Socket timed out!");
                break;

            } catch (IOException e) {
                e.printStackTrace();
                break;

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
}