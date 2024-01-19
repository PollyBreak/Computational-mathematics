package org.example.methods;

import java.util.function.Function;

public class BisectionMethod {
    public double computeRoot(Function<Double, Double> f, double a, double b, double precision){
        int iteration = 1;
        while (Math.abs(b - a) >= precision) {
            double c = (a + b) / 2;
            double fc = f.apply(c);
            double fa = f.apply(a);
            double fb = f.apply(b);

            System.out.printf("Iteration %d: X = %.3f, a = %.3f, b = %.3f, f(X) = %.3f%n", iteration, c, a, b, fc);

            if (fc == 0) {
                return c;  // Найдено точное решение
            } else if (fa * fc < 0) {
                b = c;
            } else {
                a = c;
            }
            iteration++;
        }

        double finalX = (a + b) / 2;
        System.out.printf("Iteration %d: X = %.3f, a = %.3f, b = %.3f%n", iteration, finalX, a, b);
        return finalX;
    }
}
