package org.example.methods.integrals;

import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.License;

import java.util.Scanner;
import java.util.function.Function;

public class SimpsonsOneThirdRule {
    public static double integrate(Function<Double, Double> f, double a, double b, int n) {
        double h = (b - a) / n;
        double sum = f.apply(a) + f.apply(b);

        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            sum += 4 * f.apply(x);
            i++;
            if (i < n) {
                x = a + i * h;
                sum += 2 * f.apply(x);
            }
        }

        return h / 3 * sum;
    }

    public static double integrateWithoutFunction(double[] values, double h) {
        int n = values.length;
        double sum = values[0] + values[n - 1];

        for (int i = 1; i < n - 1; i++) {
            sum += (i % 2 == 0) ? 2 * values[i] : 4 * values[i];
        }

        return h / 3 * sum;
    }

    public static void main(String[] args) {
        double a = 0; //(lower limit of integration
        double b = 1; //upper limit
        int n = 5;  //number of subintervals or steps, including both the lower and upper limits

        License.iConfirmNonCommercialUse("true1");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Equation: ");
        String equation = scanner.nextLine();
        Function<Double, Double> function = x -> eval(equation, x);

        double result = integrate(function, a, b, n);
        System.out.println("Simpson's One-Third Rule result: " + result);

             ///// WITHOUT FUNCTION

//        double[] values = { 0, 0.25, 1, 2.25, 4};
//        double h = 0.5;
//
//        double result = integrateWithoutFunction(values, h);
//        System.out.println("Simpson's One-Third Rule result: " + result);
    }

    private static double eval(String equation, double x) {
        Expression expression = new Expression(equation.replace("x", Double.toString(x)));
        return expression.calculate();
    }
}
