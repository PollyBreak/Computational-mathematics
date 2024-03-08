package org.example.methods.derivatives;

public class NewtonBackwardDerivative {
    public static void main(String[] args) {
        // Predefined values for x and y
        double[] x = {1.0, 1.1, 1.2, 1.3, 1.4, 1.5, 1.6};
        double[] y = {7.989,  8.403, 8.781 , 9.129,  9.451, 9.750 , 10.031};

        // Calculate the difference table
        int n = x.length;
        double[][] differenceTable = calculateDifferenceTable(x, y, n);

        // Display the difference table
        System.out.println("Difference Table:");
        displayDifferenceTable(differenceTable, n);

        // Value for which derivative is to be calculated
        double xValue = 1.1;

        // Calculate the first derivative using Newton's forward difference formula
        double firstDerivative = calculateFirstDerivative(x, differenceTable, n, xValue);
        System.out.println("First Derivative at x = " + xValue + ": " + firstDerivative);

        // Calculate the second derivative using Newton's forward difference formula
        double secondDerivative = calculateSecondDerivative(x, differenceTable, n, xValue);
        System.out.println("Second Derivative at x = " + xValue + ": " + secondDerivative);
    }

    private static double[][] calculateDifferenceTable(double[] x, double[] y, int n) {
        double[][] table = new double[n][n];

        // Initialize the first column with y values
        for (int i = 0; i < n; i++) {
            table[i][0] = y[i];
        }

        // Calculate the difference table
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < n - j; i++) {
                table[i][j] = table[i + 1][j - 1] - table[i][j - 1];
            }
        }

        return table;
    }

    private static void displayDifferenceTable(double[][] table, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                System.out.printf("%.4f\t", table[i][j]);
            }
            System.out.println();
        }
    }

    private static double calculateFirstDerivative(double[] x, double[][] table, int n, double xValue) {
        double result = table[0][0];
        double term = 1.0;
        double h = x[1] - x[0]; // Assuming equally spaced x values

        for (int i = 1; i < n; i++) {
            term *= (xValue - x[i - 1]) / (i * 1.0);
            result += term * table[0][i] / (h * i);
        }

        return result;
    }

    private static double calculateSecondDerivative(double[] x, double[][] table, int n, double xValue) {
        double result = table[0][0];
        double term = 1.0;

        for (int i = 1; i < n - 1; i++) {
            term *= (xValue - x[i - 1]) / (i * 1.0);
            result += term * table[0][i];
        }

        return result;
    }

}

//dy/dx (xn) = 1/h * [ ∆yn + 1/2 ∆^2 yn + 1/3 ∆^3 yn + 1/4∆^4yn + 1/5 ∆^5 yn + 1/6 ∆^5 yn + ...]

//d^2y/dx^2 = 1/h^2 * [∆^2yn + ∆^3yn + 11/12 ∆^4yn + 5/6 ∆^5yn + 137/180 ∆^6yn + ... ]
