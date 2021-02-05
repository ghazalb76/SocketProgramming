package server;

public class Matrix {
    private int row;
    private int column;
    private byte[][] matrix;
    public boolean isFull = false;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public byte[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(byte[][] matrix) {
        this.matrix = matrix;
    }

}
