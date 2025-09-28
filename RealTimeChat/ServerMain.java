public class ServerMain {
    public static void main(String[] args) {
        ChatAdapter adapter = new SocketChatAdapter(12345);
        adapter.start();
    }
}
