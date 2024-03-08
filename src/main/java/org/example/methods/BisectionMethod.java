package org.example.methods;

import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.License;

import java.util.Scanner;
import java.util.function.Function;

public class BisectionMethod {
    public static double computeRoot(Function<Double, Double> f, double a, double b, double precision){
        int iteration = 1;
        while (Math.abs(b - a) >= precision) {
            double c = (a + b) / 2;
            double fc = f.apply(c);
            double fa = f.apply(a);
            double fb = f.apply(b);

            System.out.printf("Iteration %d: X = %.3f, a = %.3f, b = %.3f, f(X) = %.3f%n", iteration, c, a, b, fc);
            if (fc == 0) {
                return c;
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

    public static void main(String[] args) {
        License.iConfirmNonCommercialUse("true1");
        Scanner scanner = new Scanner(System.in);


        System.out.print("Equation: ");
        String equation = scanner.nextLine();
        Function<Double, Double> function = x -> eval(equation, x);
                System.out.print("a: ");
        double a = scanner.nextDouble();
        System.out.print("b: ");
        double b = scanner.nextDouble();

        System.out.print("Precision: ");
        double precision = scanner.nextDouble();
        System.out.println();

        System.out.println("Bisection method \n");
        double solution1 = computeRoot(function, a, b, precision);
    }

    private static double eval(String equation, double x) {
        Expression expression = new Expression(equation.replace("x", Double.toString(x)));
        return expression.calculate();
    }

}
