import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketChatAdapter implements ChatAdapter {
    private int port;

    public SocketChatAdapter(int port) {
        this.port = port;
    }

    @Override
    public void start() {
        System.out.println("Socket Chat Server started on port " + port);
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }
}
