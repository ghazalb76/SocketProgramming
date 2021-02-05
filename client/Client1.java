package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class Client1 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 3000);
            System.out.println("Connected to the server");
            Random random = new Random();
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            int row = random.nextInt(10) + 3;
            int column = random.nextInt(10) + 3;
            outputStream.writeInt(row);
            outputStream.writeInt(column);
            outputStream.flush();
            System.out.println("Data has been send to the server");
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            System.out.println("Waiting for the matrix ...");
            byte[][] matrix = (byte[][]) inputStream.readObject();
            System.out.println("Matrix is received from server");
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < row; j++)
                    System.out.print("     "+ matrix[i][j] + " ");
                System.out.println();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
