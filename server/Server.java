package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(3000);
        System.out.println("Server started Listening on port 3000");

        while (true) {
            try {
                Matrix matrix = new Matrix();
                System.out.println("Waiting for client1 ...");
                Socket socket1 = serverSocket.accept();
                System.out.println("Client1 accepted!");
                ObjectInputStream inputStream1 = new ObjectInputStream(socket1.getInputStream());
                ObjectOutputStream outputStream1 = new ObjectOutputStream(socket1.getOutputStream());
                new ClientHandler1(socket1, inputStream1, outputStream1, matrix).start();

                System.out.println("Waiting for client2 ...");
                Socket socket2 = serverSocket.accept();
                System.out.println("Client2 accepted!");
                ObjectOutputStream outputStream2 = new ObjectOutputStream(socket2.getOutputStream());
                ObjectInputStream inputStream2 = new ObjectInputStream(socket2.getInputStream());
                new ClientHandler2(socket2, inputStream2, outputStream2, matrix).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
