import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ServerData {
    public static final LinkedList<Socket> clientsList = new LinkedList<>();
    public static final BlockingQueue<String> messagesList = new LinkedBlockingQueue<>();

    public static void addClient(Socket newClient) {
        synchronized (clientsList) {
            clientsList.add(newClient);
        }
    }

    public static void addMessage(String message) {
        messagesList.add(message);

    }

    public static String getMessage() throws InterruptedException {
        return messagesList.take();
    }

    public static void sendMessage(String message) throws IOException {
        synchronized (clientsList) {
            for (Socket clientSocket : clientsList) {
                if (!clientSocket.isClosed()) {
                    PrintWriter messageWriter = new PrintWriter(clientSocket.getOutputStream());
                    messageWriter.print(message);
                    messageWriter.flush();
                }
            }
        }
    }

}
