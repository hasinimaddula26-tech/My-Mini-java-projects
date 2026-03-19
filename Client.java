import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    private static final String SERVER = "localhost";
    private static final int PORT = 12346;

    public static void main(String[] args) {

        try {
            Socket socket = new Socket(SERVER, PORT);
            System.out.println("✅ Connected to server");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Receive messages
            new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        System.out.println(msg);
                    }
                } catch (IOException e) {}
            }).start();

            // Send messages
            Scanner sc = new Scanner(System.in);
            while (true) {
                String msg = sc.nextLine();
                out.println(msg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}