import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) {

        String serverName = args[0];
        int port = Integer.parseInt(args[1]);

        try {
            // Creo conexión con servidor
            System.out.println("Conectando a " + serverName + " en puerto " + port);
            Socket client = new Socket(serverName, port);
            System.out.println("Conexion establecida con " + client.getRemoteSocketAddress());

            // Obtengo streams
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);

            // Mando mensaje
            out.writeUTF("Hola desde " + client.getLocalSocketAddress());
            System.out.println("> Servidor: " + in.readUTF());

            // Cierro conexión
            client.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}