
public class ServerThread extends Thread {

    @Override
    public void run() {
        try {
            while (true) {
                String message = ServerData.getMessage();
                ServerData.sendMessage(message);
            }
        } catch (Exception e) {

        }
    }

}
