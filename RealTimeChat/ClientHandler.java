import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String clientName;
    private String roomId;

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        out.println("Enter your name:");
        this.clientName = in.readLine();
    }

    @Override
    public void run() {
        try {
            out.println("Welcome " + clientName + "! Join a room by typing: join <roomId>");
            String line;
            while ((line = in.readLine()) != null) {
                processInput(line.trim());
            }
        } catch (IOException e) {
            System.err.println("Connection with " + clientName + " lost.");
        } finally {
            cleanup();
        }
    }

    private void processInput(String input) {
        if (input.startsWith("join ")) {
            String newRoom = input.substring(5).trim();
            if (roomId != null) {
                ChatRoom.getInstance().leaveRoom(roomId, this);
            }
            ChatRoom.getInstance().joinRoom(newRoom, this);
            out.println("Joined room: " + newRoom);
            sendActiveUsers();
        } else if (input.equalsIgnoreCase("users")) {
            sendActiveUsers();
        } else if (roomId != null && !input.isEmpty()) {
            ChatRoom.getInstance().broadcastMessage(roomId, clientName, input);
        } else {
            out.println("Please join a chat room first using 'join <roomId>'");
        }
    }

    public void receiveMessage(String sender, String message) {
        out.println(sender + ": " + message);
    }

    public String getClientName() {
        return clientName;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    private void sendActiveUsers() {
        if (roomId != null) {
            out.println("Active users: " + String.join(", ", ChatRoom.getInstance().getActiveUsers(roomId)));
        }
    }

    private void cleanup() {
        if (roomId != null) {
            ChatRoom.getInstance().leaveRoom(roomId, this);
        }
        try {
            socket.close();
        } catch (IOException ignored) {}
    }
}
