package org.example.methods;

import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.License;

import java.util.Scanner;
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

        System.out.println("Secant method \n");
        double solution3 = computeRoot(function, a, b, precision, 300);
        System.out.printf("Approximate value of the root: %.3f%n", solution3);
        System.out.println();
    }

    private static double eval(String equation, double x) {
        Expression expression = new Expression(equation.replace("x", Double.toString(x)));
        return expression.calculate();
    }
}


