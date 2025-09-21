package LAB1;

import java.util.Random;

/** The Matrix class contains functions required in {@link LAB1}*/
public class Matrix {
    /** The max exclusive value possible in the matrix */
    private final static float BOUND = 100;
    /** The amount of rows in matrix */
    private final int rows;
    /** The amount of columns in matrix */
    private final int cols;
    /** 2d array, which contains all float elements of the matrix */
    private final float[][] matrix;

    /** Constructor creates a Matrix class object out of rows and columns values
     *
     * @param rows the amount of rows in the matrix
     * @param cols the amount of columns in the matrix */
    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = generateRandomMatrix();
    }

    /** Constructor creates a Matrix class object out float 2d-array
     *
     * @param matrix the float 2d-array */
    public Matrix(float[][] matrix) {
        this.rows = matrix.length;
        this.cols = matrix[0].length;
        this.matrix = matrix;
    }

    /** Function creates a blank 2d-array and fills it with random float values
     * <p>The values inside matrix are between 0 (inclusive) and {@value BOUND} (exclusive)</p>
     *
     * @return Float matrix with shape rows*cols, filled with random float values */
    private float[][] generateRandomMatrix() {
        float[][] randomMatrix = new float[rows][cols];
        Random rand = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                randomMatrix[i][j] = rand.nextFloat(BOUND);
            }
        }

        return randomMatrix;
    }

    /** Function multiplies matrix by a constant.
     * <p>It means multiplying each element of the matrix by the constant</p>
     *
     * @param constant each element of the matrix will be multiplied by this value
     * @return Float matrix with shape rows*cols, filled with values, multiplied by constant*/
    public Matrix multiplyByConstant(float constant) {
        float[][] result = new float[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix[i][j] * constant;
            }
        }
        return new Matrix(result);
    }

    /** Function firstly get the average values of each column of the Matrix
     * <p>Then, it outputs an array with those values</p>
     *
     * @return Float matrix with shape 1*cols, filled with average values of each column*/
    public Matrix getColumnAverages() {
        float[][] result = new float[1][cols];
        for (int j = 0; j < cols; j++) {
            float value = 0;
            for (int i = 0; i < rows; i++) {
                value += matrix[i][j];
            }
            result[0][j] = value / rows;
        }
        return new Matrix(result);
    }

    /**
     * The method transforms the matrix into String form
     * @return the String form of the matrix
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < rows; i++) {
            result.append("[");
            for (int j = 0; j < cols; j++) {
                result.append(matrix[i][j]);
                if (j < cols - 1) {
                    result.append(", ");
                }
            }
            result.append("]");
            if (i < rows - 1) {
                result.append(",\n");
            }
        }
        result.append("]");
        return result.toString();
    }
}