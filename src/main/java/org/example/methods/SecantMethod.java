package org.example.methods;

import java.util.function.Function;

public class SecantMethod {
    public static double computeRoot(Function<Double, Double> f, double x0, double x1, double epsilon, int maxIterations) {
        double x2;
        int iteration = 1;

        do {
            double f0 = f.apply(x0);
            double f1 = f.apply(x1);

            x2 = x1 - f1 * (x1 - x0) / (f1 - f0);

            double f2=f.apply(x2);
            System.out.printf("Iteration %d: X = %.6f, f(X) = %.6f%n", iteration, x2, f2);
            x0 = x1;
            x1 = x2;

            iteration++;

        } while (Math.abs(f.apply(x2)) > epsilon && iteration < maxIterations);
        return x2;
    }
}


