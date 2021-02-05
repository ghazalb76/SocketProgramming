package server;

import server.Matrix;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler2 extends Thread {
    Socket socket;
    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;
    Matrix matrix;

    ClientHandler2(Socket socket, ObjectInputStream inputStream, ObjectOutputStream outputStream, Matrix matrix) {
        this.socket = socket;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.matrix = matrix;
    }

    @Override
    public void run() {
        try {
            outputStream.writeInt(matrix.getRow());
            outputStream.writeInt(matrix.getColumn());
            outputStream.flush();
            System.out.println("waiting for matrix from client2");
            matrix.setMatrix((byte[][]) inputStream.readObject());
            matrix.isFull = true;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
