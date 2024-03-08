package org.example.methods.lecture10;

import java.math.BigDecimal;
import java.util.function.BiFunction;

public class EulerMethod {

    public static void main(String[] args) {
        // Example: Solve dy/dx = x + y, y(0) = 1, at x = 1
        BiFunction<BigDecimal, BigDecimal, BigDecimal> derivativeFunction = (x, y) -> x.add(y);
        BigDecimal x0 = BigDecimal.ZERO;
        BigDecimal y0 = BigDecimal.ONE;
        BigDecimal xTarget = BigDecimal.ONE;
        BigDecimal h = BigDecimal.valueOf(0.1);

        BigDecimal result = eulerMethod(derivativeFunction, x0, y0, xTarget, h);
        System.out.println("Approximate value of y at x = " + xTarget + ": " + result);

        // Example: Solve dy/dx = x + y, y(0) = 1, at x = 1

    }

    private static BigDecimal eulerMethod(BiFunction<BigDecimal, BigDecimal, BigDecimal> derivativeFunction,
                                          BigDecimal x0, BigDecimal y0, BigDecimal xTarget, BigDecimal h) {
        BigDecimal x = x0;
        BigDecimal y = y0;

        while (x.compareTo(xTarget) < 0) {
            BigDecimal derivative = derivativeFunction.apply(x, y);
            y = y.add(h.multiply(derivative));
            x = x.add(h);
        }

        return y;
    }


    private static BigDecimal eulerMethodWithSteps(BiFunction<BigDecimal, BigDecimal, BigDecimal> derivativeFunction,
                                                   BigDecimal x0, BigDecimal y0, BigDecimal xTarget, int numberOfSteps) {
        BigDecimal h = (xTarget.subtract(x0)).divide(BigDecimal.valueOf(numberOfSteps), BigDecimal.ROUND_HALF_UP);
        BigDecimal x = x0;
        BigDecimal y = y0;

        for (int i = 0; i < numberOfSteps; i++) {
            BigDecimal derivative = derivativeFunction.apply(x, y);
            y = y.add(h.multiply(derivative));
            x = x.add(h);
        }

        return y;
    }




//    public static void main(String[] args) {
//        // Given ODE: dy/dx = x + y, initial condition y(0) = 1
//        double x0 = 0.0;
//        double y0 = 1.0;
//        double xTarget = 1.0;
//        double h = 0.1; // Step size
//
//        // Find approximate value of y at x = 1 using Euler's method
//        double result = eulersMethod(x0, y0, xTarget, h);
//        System.out.println("Approximate value of y at x = " + xTarget + ": " + result);
//    }
//
//    private static double eulersMethod(double x0, double y0, double xTarget, double h) {
//        double x = x0;
//        double y = y0;
//
//        while (x < xTarget) {
//            double derivative = x + y;
//            y = y + h * derivative;
//            x = x + h;
//        }
//
//        return y;
//    }
}


//Using Euler’s method, find an approximate value of y corresponding to
//x = 1, given that dy/dx = x + y and y = 1 when x = 0.  // answer : 3.18


//(y-x)/(y+x)   y=1 at x=0 y(0,1)-?   /////  y(0,1) =  1.0928.