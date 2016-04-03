import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class UserThread extends Thread {

    private Socket userSocket;
    public static final int MESSAGE_BUFFER_SIZE = 4096;

    public UserThread(Socket userSocket) {
        this.userSocket = userSocket;
    }

    @Override
    public void run() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(userSocket.getInputStream()));
            int bytesRead;
            char[] messageBuffer = new char[MESSAGE_BUFFER_SIZE];
            while ((bytesRead = reader.read(messageBuffer)) >= 0) {
                if (bytesRead > 0) {
                    ServerData.addMessage(String.valueOf(messageBuffer, 0, bytesRead));
                }
            }
        } catch (Exception e) {
            try {
                userSocket.close();
            } catch (Exception e1) {

            }
        }
    }
}
