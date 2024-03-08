package org.example.methods;

import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.License;

import java.util.Scanner;
import java.util.function.Function;

public class NewtonRaphsonMethod {
    public static double computeRoot(Function<Double, Double> f, Function<Double, Double> derivF,
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

    public static void main(String[] args) {
        License.iConfirmNonCommercialUse("true1");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Equation: ");
        String equation = scanner.nextLine();
        Function<Double, Double> function = x -> eval(equation, x);

        System.out.print("Derivative: ");
        String equation2 = scanner.nextLine();
        Function<Double, Double> derFunc = x -> eval(equation2, x);

        System.out.print("a: ");
        double a = scanner.nextDouble();

        System.out.print("Precision: ");
        double precision = scanner.nextDouble();
        System.out.println();

        System.out.println("Newton Raphson method \n");
        double solution4 = computeRoot(function, derFunc, a, precision);
        System.out.printf("Approximate value of the root: %.3f%n", solution4);
    }

    private static double eval(String equation, double x) {
        Expression expression = new Expression(equation.replace("x", Double.toString(x)));
        return expression.calculate();
    }
}
