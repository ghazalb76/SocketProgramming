package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class Client2 {


    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 3000);
            System.out.println("Connected to the server");
            Random random = new Random();

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Waiting for server to send row and column ...");
            int row = inputStream.readInt();
            int column = inputStream.readInt();
            System.out.println("Row and column have been received");
            System.out.println("row: "+ row);
            System.out.println("column: "+ column);
            byte[][] matrix1 = new byte[row][column];
            byte[][] matrix2 = new byte[column][row];
            byte[][] multiplyMatrix = new byte[row][row];

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    matrix1[i][j] = (byte) random.nextInt(2);
                }
            }
            for (int i = 0; i < column; i++) {
                for (int j = 0; j < row; j++) {
                    matrix2[i][j] = (byte) random.nextInt(2);
                }
            }
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < row; j++) {
                    for (int k = 0; k < column; k++)
                        multiplyMatrix[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
            System.out.println("Matrix1: ");
            printMatrix(matrix1, row, column);
            System.out.println("Matrix2: ");
            printMatrix(matrix2, column, row);
            System.out.println("Matrix3: ");
            printMatrix(multiplyMatrix, row, row);

            outputStream.writeObject(multiplyMatrix);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void printMatrix(byte matrix[][], int row, int column) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++)
                System.out.print("     " + matrix[i][j] + " ");

            System.out.println();
        }
        System.out.println("-----------------------------------------");
    }
}
