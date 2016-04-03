import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatikServer {

    public static final int PORT_NUMBER = 6666;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
        new ServerThread().start();
        while (true) {
            Socket socket = serverSocket.accept();
            ServerData.addClient(socket);
            new UserThread(socket).start();
        }
    }
}

