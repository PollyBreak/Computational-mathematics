package org.example.methods;

public class JacobIterationMethod {
    public static double[] computeRoots(double[][] coefficients, double[] constants, double[] initialGuess,
                                        int maxIterations, double precision) {
        int n = coefficients.length;
        double[] nextSolution = new double[n];

        for (int iteration = 1; iteration <= maxIterations; iteration++) {
            for (int i = 0; i < n; i++) {
                double sum = constants[i];
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        sum -= coefficients[i][j] * initialGuess[j];
                    }
                }
                nextSolution[i] = sum / coefficients[i][i];
            }

            if (isConverged(initialGuess, nextSolution, precision)) {
                return nextSolution;
            }

            System.out.print("Iteration " + iteration + ": ");
            for (int i = 0; i < nextSolution.length; i++) {
                System.out.printf("x%d = %.6f  ", i + 1, nextSolution[i]);
            }
            System.out.println();

            initialGuess = nextSolution.clone();

        }

        System.out.println("The method wasn't converged");
        return initialGuess;
    }

    private static boolean isConverged(double[] x1, double[] x2, double tolerance) {
        for (int i = 0; i < x1.length; i++) {
            if (Math.abs(x1[i] - x2[i]) >= tolerance) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
                double[][] coefficients = {
                {10, -2, -1, -1},
                {-2, 10, -1, -1},
                {-1, -1, 10, -2},
                {-1, -1, -2, 10}
        };
        double[] constants = {3, 15, 27, -9};

        double[] solution = computeRoots(coefficients, constants,
                new double[]{0,0,0,0}, 1000, 0.0001);
    }
}
