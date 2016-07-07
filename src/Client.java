import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) {

        String serverName = args[0];
        int port = Integer.parseInt(args[1]);

        try {
            // Creo conexión con servidor
            System.out.println("Conectando a " + serverName + " en puerto " + port);
            Socket server = new Socket(serverName, port);
            System.out.println("Conexion establecida con " + server.getRemoteSocketAddress());

            // Obtengo streams
            BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
            OutputStream outToServer = server.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            InputStream inFromServer = server.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);

            // Mantengo conexión hasta mandar "BYE"
            boolean stay = true;

            while (stay) {
                // Mando mensaje
                System.out.print("> ");
                String msgToServer = cin.readLine();
                out.writeUTF(msgToServer);

                if (msgToServer.equalsIgnoreCase("BYE")) {
                    // Si dije BYE me voy
                    stay = false;
                } else {
                    // Sino leo respuesta
                    String msgFromServer = "> Servidor: " + in.readUTF();
                    System.out.println(msgFromServer);
                }
            }

            // Cierro conexión
            server.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}