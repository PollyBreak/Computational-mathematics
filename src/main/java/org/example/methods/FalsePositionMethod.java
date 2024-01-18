package org.example.methods;

import java.util.function.Function;

public class FalsePositionMethod {
    public  double computeRoot(Function<Double, Double> f, double a, double b, double epsilon) {
        double c;

        int iteration = 1;

        do {
            c = (a * f.apply(b) - b * f.apply(a)) / (f.apply(b) - f.apply(a));

            double fa = f.apply(a);
            double fb = f.apply(b);
            double fc = f.apply(c);

            System.out.printf("Iteration %d: X = %.4f, f(X) = %.4f%n", iteration, c, fc);

            if (fa * fc < 0) {
                b = c;
            } else if (fc * fb < 0) {
                a = c;
            }

            iteration++;

        } while (Math.abs(f.apply(c)) > epsilon);

        return c;
    }
}
