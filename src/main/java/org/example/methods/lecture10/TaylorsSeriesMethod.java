package org.example.methods.lecture10;

import java.util.Map;
import java.util.function.BiFunction;

public class TaylorsSeriesMethod {
    public static void main(String[] args) {
        // Example: Solve dy/dx = x+y, y(0) = 0, at x = 0.2 //
        BiFunction<Double, Double, Double> differentialEquation = (x, y) -> 2 * y + 3 * Math.exp(x); //dy/dx = 2y + 3e^x ==== 2 * y + 3 * Math.exp(x)
        double x0 = 0.0;
        double y0 = 0;  //y(0) =
        double xTarget = 0.2; //x=
        double h = 0.1; // Step size

        double result = taylorsMethod(differentialEquation, x0, y0, xTarget, h);
        System.out.println("Approximate value of y at x = " + xTarget + ": " + result);
    }

    private static double taylorsMethod(BiFunction<Double, Double, Double> equation, double x0, double y0, double xTarget, double h) {
        double x = x0;
        double y = y0;

        while (x < xTarget) {
            double yPrime = equation.apply(x, y);
            y += h * yPrime;
            x += h;
        }

        return y;
    }
}




// 1. Solve y′ = x + y, y(0) = 1 by Taylor’s series method. Hence find y(0.1) and y(0.2).  //y(0.1) =1.1103  and y(0.2) =1.2427


// Employ Taylor’s method to obtain approximate value of y at x = 0.2
//for the differential equation dy/dx = 2y + 3e^x, y(0) = 0.  Hence y(0.2) =  0.8110


//Solve by Taylor series method of third order the equation (x^3 +xy^2)/(e^x) y(0) = 1 for y at x = 0.1, x = 0.2 and x= 0.3
//0.1 = 1.005, 0.2 = 1.017 and 0.3 = 1.036


//log(xy), y(1)=2, y(1.1)-? y(2.2) - ?  //2.036 and 2.081