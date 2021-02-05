package server;

import server.Matrix;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler1 extends Thread {
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private Matrix matrix;

    ClientHandler1(Socket socket, ObjectInputStream inputStream, ObjectOutputStream outputStream, Matrix matrix) {
        this.socket = socket;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.matrix = matrix;
    }

    @Override
    public void run() {
        try {
            int row = inputStream.readInt();
            int column = inputStream.readInt();
            matrix.setRow(row);
            matrix.setColumn(column);
            System.out.println("Received row from Client1" + row);
            System.out.println("Received column from Client1" + column);
            while (true) {
                System.out.println("Waiting for matrix to be full");
                if (matrix.isFull) {
                    System.out.println("Matrix is full now: ");
                    break;
                }
            }
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < row; j++)
                    System.out.print("     " + matrix.getMatrix()[i][j] + " ");
                System.out.println();
            }
            outputStream.writeObject(matrix.getMatrix());
            outputStream.flush();
            System.out.println("The matrix has been sent to client1");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
