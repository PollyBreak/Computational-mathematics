package org.example.methods.lecture10;

import java.math.BigDecimal;
import java.util.function.BiFunction;

public class ModifiedEulerMethod {
    public static void main(String[] args) {
        // Given ODE: dy/dx = x + y, initial condition y(0) = 1
        BiFunction<BigDecimal, BigDecimal, BigDecimal> derivativeFunction = (x, y) ->
                x.add(y);
        BigDecimal x0 = BigDecimal.ZERO;
        BigDecimal y0 = BigDecimal.ONE;
        BigDecimal xTarget = BigDecimal.valueOf(0.3);
        int numberOfSteps = 100;

        // Find approximate value of y at x = 0.3 using modified Euler's method
        BigDecimal result = modifiedEulersMethod(derivativeFunction, x0, y0, xTarget, numberOfSteps);
        System.out.println("Approximate value of y at x = " + xTarget + ": " + result);
    }

    private static BigDecimal modifiedEulersMethod(BiFunction<BigDecimal, BigDecimal, BigDecimal> derivativeFunction,
                                                   BigDecimal x0, BigDecimal y0, BigDecimal xTarget, int numberOfSteps) {
        BigDecimal h = (xTarget.subtract(x0)).divide(BigDecimal.valueOf(numberOfSteps), BigDecimal.ROUND_HALF_UP);
        BigDecimal x = x0;
        BigDecimal y = y0;

        for (int i = 0; i < numberOfSteps; i++) {
            BigDecimal k1 = h.multiply(derivativeFunction.apply(x, y));
            BigDecimal k2 = h.multiply(derivativeFunction.apply(x.add(h), y.add(k1)));
            y = y.add(k1.add(k2).divide(BigDecimal.valueOf(2)));
            x = x.add(h);
        }

        return y;
    }
}


//Using modified Euler’s method, find an approximate value of y when
//x = 0.3, given that dy/dx = x + y and y = 1 when x = 0 //  y(0.1) = 1.110 //y(0.2) = 1.2432 //y(0.3) = 1.4004.


//Using the modified Euler’s method, find y(0.2) and y(0.4) given y`=y+e^x, y(0)=0 //// y (0.2) = 0.2468   and  y(0.4) = 0.6031