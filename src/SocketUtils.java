import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketUtils {

    public static final int MESSAGE_BUFFER_SIZE = 4096;

    public static final String OK = "1";
    public static final String NICKNAME_EXISTS = "2";

    public static String readMessage(Socket socket) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            char[] messageBuffer = new char[MESSAGE_BUFFER_SIZE];
            int bytesRead;
            while ((bytesRead = reader.read(messageBuffer)) == 0);
            return String.valueOf(messageBuffer, 0, bytesRead);
        } catch (Exception e) {
        }
        return null;
    }

    public static void sendMessage(Socket socket, String message) {
        try {
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.print(message);
            writer.flush();
        } catch (Exception e) {

        }
    }
}
