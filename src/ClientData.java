import java.net.Socket;

public class ClientData {

    private Socket clientSocket;
    private String clientNickname;

    public ClientData(Socket clientSocket, String clientNickname) {
        this.clientNickname = clientNickname;
        this.clientSocket = clientSocket;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public String getClientNickname() {
        return clientNickname;
    }

    public void setClientNickname(String clientNickname) {
        this.clientNickname = clientNickname;
    }


}
