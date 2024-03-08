package org.example.methods.integrals;

import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.License;

import java.util.Scanner;
import java.util.function.Function;

public class WeddlesRule {
    public static double integrate(Function<Double, Double> f, double a, double b, int n) {
//        if (n % 6 != 0) {
//            throw new IllegalArgumentException("n must be a multiple of 6 for Weddle's Rule");
//        }

        double h = (b - a) / n;
        double sum = f.apply(a) + f.apply(b);

        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            if (i % 6 == 0) {
                sum += 2 * f.apply(x);
            } else if (i % 3 == 0) {
                sum += 6 * f.apply(x);
            } else {
                sum += 5 * f.apply(x);
            }
        }

        return 3 * h / 10 * sum;
    }

    public static void main(String[] args) {
        double a = 0; //(lower limit of integration
        double b = 6; //upper limit
        int n = 7;  //number of subintervals or steps, including both the lower and upper limits

        License.iConfirmNonCommercialUse("true1");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Equation: ");
        String equation = scanner.nextLine();
        Function<Double, Double> function = x -> eval(equation, x);

        double result = integrate(function, a, b, n);
        System.out.println("Weddle's Rule result: " + result);
    }

    private static double eval(String equation, double x) {
        Expression expression = new Expression(equation.replace("x", Double.toString(x)));
        return expression.calculate();
    }
}
