import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ServerData {
    public static final LinkedList<ClientData> clientsList = new LinkedList<>();
    public static final BlockingQueue<String> messagesList = new LinkedBlockingQueue<>();

    public static void addClient(Socket clientSocket, String clientNickname) {
        synchronized (clientsList) {
            clientsList.add(new ClientData(clientSocket, clientNickname));
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
            for (ClientData clientData : clientsList) {
                if (!clientData.getClientSocket().isClosed()) {
                    PrintWriter messageWriter = new PrintWriter(clientData.getClientSocket().getOutputStream());
                    messageWriter.print(message);
                    messageWriter.flush();
                }
            }
        }
    }

    public static boolean nicknameExists(String nickname) {
        synchronized (clientsList) {
            for (ClientData clientData : clientsList) {
                if (!clientData.getClientSocket().isClosed() && nickname.equals(clientData.getClientNickname())) {
                    return true;
                }
            }
            return false;
        }
    }
}
