import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;


public class Server {
    public static void main(String[] args) {
        System.out.println("This is the server!");
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
