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
            String nickname = SocketUtils.readMessage(socket);
            if (ServerData.nicknameExists(nickname)) {
                SocketUtils.sendMessage(socket, SocketUtils.NICKNAME_EXISTS);
            } else {
                SocketUtils.sendMessage(socket, SocketUtils.OK);
                ServerData.addClient(socket, nickname);
                new UserThread(socket).start();
            }
        }
    }
}

