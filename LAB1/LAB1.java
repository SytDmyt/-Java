package LAB1;

import java.util.Scanner;

/**
 * LAB1 class performs basic matrix tasks, which are described in Lab work #1
 *
 * <p>Firstly, it takes two integer value for rows and columns and one
 * float constant either by main args or via console input</p>
 * <p>Secondly, after validation, it creates and prints
 * a matrix using the {@link Matrix} class </p>
 * <p>Thirdly, it multiplies the matrix by a constant and prints the result</p>
 * <p>And finally, it finds average values for each column and prints the result</p>
 */
public class LAB1 {

    /**
     * Entry point of the program.
     *
     * @param args two positive integers and a float (optional)
     */
    public static void main(String[] args) {
        int rows, cols;
        float constant;

        // Attempt to get shape and constant via command-line arguments
        // If failed, another attempt to get them through console input
        if (args.length >= 3) {
            Object[] parsed = parseArgs(args);
            if (parsed == null) return;
            rows = (int) parsed[0];
            cols = (int) parsed[1];
            constant = (float) parsed[2];
        } else {
            Object[] fromConsole = readFromConsole();
            if (fromConsole == null) return;
            rows = (int) fromConsole[0];
            cols = (int) fromConsole[1];
            constant = (float) fromConsole[2];
        }

        if (!isValid(rows, cols)) {
            System.out.println("Rows and columns must be integers greater than 0");
            return;
        }

        Matrix a = new Matrix(rows, cols);
        System.out.println("Початкова матриця:");
        System.out.println(a);
        Matrix b = a.multiplyByConstant(constant);
        System.out.println("Початкова матриця, помножена на константу " + constant + ": ");
        System.out.println(b);
        Matrix c = b.getColumnAverages();
        System.out.println("Середні значення колонок матриці, помноженої " +
                "на константу " + constant + ": ");
        System.out.println(c);
    }

    /**
     * Attempts to parse two integers (rows and columns)
     * and a float (constant) from command-line arguments.
     *
     * @param args array of command-line arguments
     * @return an array [rows, cols] if parsing succeeds, or {@code null} if invalid
     */
    private static Object[] parseArgs(String[] args) {
        try {
            int rows = Integer.parseInt(args[0]);
            int cols = Integer.parseInt(args[1]);
            float constant = Float.parseFloat(args[2]);
            return new Object[]{rows, cols, constant};
        } catch (NumberFormatException e) {
            System.out.println("Arguments must be: int int float");
            return null;
        }
    }

    /**
     * Gets matrix shape(rows, columns) and multiplying constant.
     *
     * @return an array [rows, cols, constant] if valid input is provided,
     * or null if the input is invalid
     */
    private static Object[] readFromConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of rows: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Must be an integer.");
            return null;
        }
        int rows = scanner.nextInt();

        System.out.print("Enter number of columns: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Must be an integer.");
            return null;
        }
        int cols = scanner.nextInt();

        System.out.print("Enter constant (float): ");
        if (!scanner.hasNextFloat()) {
            System.out.println("Invalid input. Must be a float.");
            return null;
        }
        float constant = scanner.nextFloat();

        return new Object[]{rows, cols, constant};
    }

    /**
     * Validates that both rows and columns are positive numbers.
     *
     * @param rows number of rows
     * @param cols number of columns
     * @return true if both values are greater than 0,
     * otherwise false
     */
    private static boolean isValid(int rows, int cols) {
        return rows > 0 && cols > 0;
    }
}