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

        System.out.println("Connectando a " + args[0] + ":" + args[1] + " ...");
        Socket client = new Socket(args[0], Integer.parseInt(args[1]));

        try
        {
            System.out.println("Conección establecida con " + client.getRemoteSocketAddress() + "\n");
//            OutputStream outToServer = client.getOutputStream();
//            DataOutputStream out = new DataOutputStream(outToServer);
//            out.writeUTF("Hello from "
//                    + client.getLocalSocketAddress());
//            InputStream inFromServer = client.getInputStream();
//            DataInputStream in =
//                    new DataInputStream(inFromServer);
//            System.out.println("Server says " + in.readUTF());
        } finally {
            client.close();
        }
    }
}
