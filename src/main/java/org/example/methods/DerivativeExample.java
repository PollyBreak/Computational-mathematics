package org.example.methods;

public class DerivativeExample {
    public static void main(String[] args) {
        double[] xValues = {1, 1.1, 1.2, 1.3, 1.4};
        double[] yValues = {43.1, 47.7, 52.1, 56.4, 60.8};

        double x = 1.1;

        printDifferenceTable(xValues, yValues);
        double forwardDerivative = calculateForwardDifference(xValues, yValues, x);
        double backwardDerivative = calculateBackwardDifference(xValues, yValues, x);

        System.out.println("Newton's Forward Difference Formula:");
        System.out.println("Derivative at x = " + x + ": " + forwardDerivative);

        System.out.println("\nNewton's Backward Difference Formula:");
        System.out.println("Derivative at x = " + x + ": " + backwardDerivative);
    }

    private static double calculateForwardDifference(double[] xValues, double[] yValues, double x) {
        int n = xValues.length;
        double h = xValues[1] - xValues[0];

        int index = 0;
        for (int i = 0; i < n - 1; i++) {
            if (x >= xValues[i] && x <= xValues[i + 1]) {
                index = i;
                break;
            }
        }

        return (yValues[index + 1] - yValues[index]) / h;
    }

    private static double calculateBackwardDifference(double[] xValues, double[] yValues, double x) {
        int n = xValues.length;
        double h = xValues[1] - xValues[0];

        int index = 0;
        for (int i = 1; i < n; i++) {
            if (x >= xValues[i - 1] && x <= xValues[i]) {
                index = i;
                break;
            }
        }

        return (yValues[index] - yValues[index - 1]) / h;
    }

    private static void printDifferenceTable(double[] xValues, double[] yValues) {
        int n = xValues.length;
        double[][] table = new double[n][n];

        for (int i = 0; i < n; i++) {
            table[i][0] = yValues[i];
        }

        for (int j = 1; j < n; j++) {
            for (int i = 0; i < n - j; i++) {
                table[i][j] = table[i + 1][j - 1] - table[i][j - 1];
            }
        }

        System.out.println("Difference Table:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                System.out.printf("%.4f\t", table[i][j]);
            }
            System.out.println();
        }
    }
}
