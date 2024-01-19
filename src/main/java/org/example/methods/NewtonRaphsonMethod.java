package org.example.methods;

import java.util.function.Function;

public class NewtonRaphsonMethod {
    public double computeRoot(Function<Double, Double> f, Function<Double, Double> derivF,
                              double x, double precision) {
        int iteration =1;
        double fX=f.apply(x);
        double h;

        while (Math.abs(fX) >= precision)
        {
            h = f.apply(x) / derivF.apply(x);
            x = x - h;
            fX = f.apply(x);
            System.out.printf("Iteration %d: x = %.3f, f(x) = %.3f%n", iteration, x, fX);
            iteration++;
        }

        return x;
    }
}
