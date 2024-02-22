package org.example.methods;

public class NewtonInterpolation {
    private static final double EPSILON = 0.0001;
    public static double round(double x){
        return Math.round(x * (1/EPSILON)) / (1/EPSILON);
    }
    public static void printDifferenceTable(double[] xValues, double[] yValues) {
        System.out.println("Forward Differences Table:");
        System.out.println("x\t\tf(x)\t\tForward Differences");

        double[][] forwardDifferences = calculateFullForwardDifferences(xValues, yValues);
        printTable(xValues, yValues, forwardDifferences);
    }

    public static void printTable(double[] xValues, double[] yValues, double[][] differences) {
        int n = yValues.length;
        for (int i = 0; i < n; i++) {
            System.out.print(xValues[i] + "\t\t");
            for (int j = 0; j < n - i; j++) {
                System.out.print(round(differences[i][j]) + "\t\t\t\t");
            }
            System.out.println();
        }
    }

    public static double[][] calculateFullForwardDifferences(double[] xValues, double[] yValues) {
        int n = yValues.length;
        double[][] forwardDifferences = new double[n][n];
        for (int i = 0; i < n; i++) {
            forwardDifferences[i][0] = yValues[i];
        }
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < n - j; i++) {
                forwardDifferences[i][j] = forwardDifferences[i + 1][j - 1] - forwardDifferences[i][j - 1];
            }
        }
        return forwardDifferences;
    }

    public static double[] calculateForwardDifferences(double[] y) {
        int n = y.length;
        double[] forwardDifferences = new double[n];
        System.arraycopy(y, 0, forwardDifferences, 0, n);

        for (int i = 1; i < n; i++) {
            for (int j = n - 1; j >= i; j--) {
                forwardDifferences[j] = forwardDifferences[j] - forwardDifferences[j - 1];
            }
        }
        return forwardDifferences;
    }

    public static double[] calculateBackwardDifferences(double[] y) {
        int n = y.length;
        double[] backwardDifferences = new double[n];
        for (int i = 0; i < n; i++) {
            backwardDifferences[i] = y[n - i - 1];
        }

        for (int i = 1; i < n; i++) {
            for (int j = n - 1; j >= i; j--) {
                backwardDifferences[j] = backwardDifferences[j-1] - backwardDifferences[j];
            }
        }
        return backwardDifferences;
    }

    public static double newtonForwardInterpolation(double x, double[] xValues, double[] yValues) {
        int n = xValues.length;
        double h = xValues[1] - xValues[0];
        System.out.println("hf = "+h);
        double[] forwardDifferences = calculateForwardDifferences(yValues);
        double result = yValues[0];
        double u = (x - xValues[0]) / h;
        System.out.println("pf = "+ u);


        for (int i = 1; i < n; i++) {
            double term = forwardDifferences[i] / factorial(i);
            for (int j = 0; j < i; j++) {
                term *= (u - j);
            }
            result += term;
        }
        return result;
    }

    public static double newtonBackwardInterpolation(double x, double[] xValues, double[] yValues) {
        int n = xValues.length;
        double h = xValues[1] - xValues[0];
        System.out.println("hb = "+h);
        double[] backwardDifferences = calculateBackwardDifferences(yValues);
        double result = yValues[n - 1];
        double u = (x - xValues[n-1]) / h;
        System.out.println("pb = "+ u);

        for (int i = 1; i < n; i++) {
            double term = backwardDifferences[i] / factorial(i);
            for (int j = 0; j < i; j++) {
                term *= (u + j);
            }
            result += term;
        }
        return result;
    }
    public static int factorial(int n) {
        if (n == 0 || n == 1) return 1;
        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        double[] xValues = {-1, 0,1,2,3,4,5};
        double[] yValues = {-13,-7,-1,11,35,77,143};

        double x = 6;

        printDifferenceTable(xValues,yValues);
        double forwardInterpolatedValue = newtonForwardInterpolation(x, xValues, yValues);
        double backwardInterpolatedValue = newtonBackwardInterpolation(x, xValues, yValues);
        System.out.println("Interpolated value using Newton's Forward Interpolation: " + forwardInterpolatedValue);
        System.out.println("Interpolated value using Newton's Backward Interpolation: " + backwardInterpolatedValue);
    }
}