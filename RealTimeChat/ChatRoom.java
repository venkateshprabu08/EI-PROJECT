import java.util.*;

public class ChatRoom {
    private static ChatRoom instance;
    private Map<String, Set<ClientHandler>> chatRooms;

    private ChatRoom() {
        chatRooms = new HashMap<>();
    }

    public static synchronized ChatRoom getInstance() {
        if (instance == null) {
            instance = new ChatRoom();
        }
        return instance;
    }

    public synchronized void joinRoom(String roomId, ClientHandler client) {
        chatRooms.computeIfAbsent(roomId, k -> new HashSet<>()).add(client);
        client.setRoomId(roomId);
        broadcastMessage(roomId, "System", client.getClientName() + " joined the room.");
    }

    public synchronized void leaveRoom(String roomId, ClientHandler client) {
        Set<ClientHandler> clients = chatRooms.get(roomId);
        if (clients != null) {
            clients.remove(client);
            broadcastMessage(roomId, "System", client.getClientName() + " left the room.");
            if (clients.isEmpty()) {
                chatRooms.remove(roomId);
            }
        }
    }

    public synchronized void broadcastMessage(String roomId, String sender, String message) {
        Set<ClientHandler> clients = chatRooms.get(roomId);
        if (clients != null) {
            for (ClientHandler client : clients) {
                client.receiveMessage(sender, message);
            }
        }
    }

    public synchronized List<String> getActiveUsers(String roomId) {
        Set<ClientHandler> clients = chatRooms.get(roomId);
        List<String> users = new ArrayList<>();
        if (clients != null) {
            for (ClientHandler client : clients) {
                users.add(client.getClientName());
            }
        }
        return users;
    }
}
