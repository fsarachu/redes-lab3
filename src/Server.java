import java.net.*;
import java.io.*;
import java.util.*;
import java.text.*;

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
                Socket client = serverSocket.accept();
                System.out.println("Conexión establecida con " + client.getRemoteSocketAddress());

                // Obtengo streams
                DataInputStream in = new DataInputStream(client.getInputStream());
                DataOutputStream out = new DataOutputStream(client.getOutputStream());

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
                        String[] splitedMsg = msgFromClient.split(" ");
                        String command = splitedMsg[0];
                        Options option = Options.valueOf(command.toUpperCase());

                        switch (option) {
                            case GET_TIME:
                                out.writeUTF((new SimpleDateFormat("hh:mm:ss")).format(new Date()));
                                break;
                            case GET_DATE:
                                out.writeUTF((new SimpleDateFormat("yyyy-MM-dd")).format(new Date()));
                                break;
                            case GET_TIMESTAMP:
                                out.writeUTF((new SimpleDateFormat("yyyyMMddhhmmss")).format(new Date()));
                                break;
                            case HELLO:
                                out.writeUTF("Hola " + splitedMsg[1]);
                                break;
                            case GET_VERSION:
                                out.writeUTF("0.0.1");
                                break;
                            case BYE:
                                stay = false;
                                break;
                            default:
                                out.writeUTF("Comando desconcido");
                                break;
                        }
                    }
                }

                // Cierro conexión
                System.out.println("Cerrando conexión con " + client.getRemoteSocketAddress());
                client.close();

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
        BYE
    }
}