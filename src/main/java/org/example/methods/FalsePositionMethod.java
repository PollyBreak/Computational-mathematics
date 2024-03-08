package org.example.methods;

import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.License;

import java.util.Scanner;
import java.util.function.Function;

public class FalsePositionMethod {
    public  static  double computeRoot(Function<Double, Double> f, double a, double b, double epsilon) {
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

        System.out.println("False Position method \n");
        double solution2 = computeRoot(function, a, b, precision);
        System.out.printf("Approximate value of the root: %.3f%n", solution2);
        System.out.println();
    }

    private static double eval(String equation, double x) {
        Expression expression = new Expression(equation.replace("x", Double.toString(x)));
        return expression.calculate();
    }
}
