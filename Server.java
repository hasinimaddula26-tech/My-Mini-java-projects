import java.io.*;
import java.net.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Scanner;

public class Server {

    private static final int PORT = 12346;
    private static CopyOnWriteArrayList<ClientHandler> clients = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("✅ Server running on port " + PORT);

            // Server admin messages
            new Thread(() -> {
                Scanner sc = new Scanner(System.in);
                while (true) {
                    String msg = sc.nextLine();
                    broadcast("[Server]: " + msg, null);
                }
            }).start();

            // Accept clients
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                ClientHandler handler = new ClientHandler(socket);
                clients.add(handler);
                new Thread(handler).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Broadcast message
    public static void broadcast(String msg, ClientHandler sender) {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(msg);
            }
        }
    }

    // Handle each client
    static class ClientHandler implements Runnable {

        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private String username;

        public ClientHandler(Socket socket) {
            this.socket = socket;

            try {
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            try {
                out.println("Enter your username:");
                username = in.readLine();

                System.out.println(username + " joined the chat");
                broadcast(username + " joined!", this);

                String msg;
                while ((msg = in.readLine()) != null) {
                    System.out.println("[" + username + "]: " + msg);
                    broadcast("[" + username + "]: " + msg, this);
                }

            } catch (IOException e) {
                System.out.println(username + " disconnected.");
            } finally {
                clients.remove(this);
                try {
                    socket.close();
                } catch (IOException e) {}
            }
        }

        public void sendMessage(String msg) {
            out.println(msg);
        }
    }
}