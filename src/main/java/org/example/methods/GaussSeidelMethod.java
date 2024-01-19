package org.example.methods;

public class GaussSeidelMethod {
    public double[] computeRoot(double[][] coefficients, double[] constants, double[] initialGuess,
                              int maxIterations, double precision) {
        int n = coefficients.length;
        double[] solution = new double[n];
        double[] oldSolution = new double[n];

        for (int iteration = 0; iteration < maxIterations; iteration++) {
            System.arraycopy(solution, 0, oldSolution, 0, n);

            for (int i = 0; i < n; i++) {
                double sigma = 0.0;
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        sigma += coefficients[i][j] * solution[j];
                    }
                }
                solution[i] = (constants[i] - sigma) / coefficients[i][i];
            }
            System.out.printf("Iteration %d: x1 = %.6f, x2 = %.6f, x3 = %.6f%n",
                    iteration + 1, solution[0], solution[1], solution[2]);

            // Проверка на сходимость по норме
            double norm = calculateNorm(solution, oldSolution);
            if (norm < precision) {
                System.out.println("Converged in " + (iteration + 1) + " iterations.");
                return solution;
            }
        }

        System.out.println("Did not converge within the specified tolerance.");
        return solution;
    }

    private static double calculateNorm(double[] vector1, double[] vector2) {
        double sum = 0.0;
        for (int i = 0; i < vector1.length; i++) {
            sum += Math.pow(vector1[i] - vector2[i], 2);
        }
        return Math.sqrt(sum);
    }
}
