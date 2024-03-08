package org.example.methods.integrals;

import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.License;

import java.util.Scanner;
import java.util.function.Function;

public class SimpsonsThreeEighthRule {
    public static double integrate(Function<Double, Double> f, double a, double b, int n) {
        double h = (b - a) / n;
        double sum = f.apply(a) + f.apply(b);

        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            if (i % 3 == 0) {
                sum += 2 * f.apply(x);
            } else {
                sum += 3 * f.apply(x);
            }
        }

        return 3 * h / 8 * sum;
    }

    public static double integrateWithoutFunction(double[] values, double h) {
        int n = values.length;
        double sum = values[0] + values[n - 1];

        for (int i = 1; i < n - 1; i++) {
            sum += (i % 3 == 0) ? 2 * values[i] : 3 * values[i];
        }

        return 3 * h / 8 * sum;
    }

    public static void main(String[] args) {
        double a = 0.2; //(lower limit of integration
        double b = 1.4; //upper limit
        int n = 7;  //number of subintervals or steps, including both the lower and upper limits

        License.iConfirmNonCommercialUse("true1");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Equation: ");
        String equation = scanner.nextLine();
        Function<Double, Double> function = x -> eval(equation, x);

        double result = integrate(function, a, b, n);
        System.out.println("Simpson's Three-Eighth Rule result: " + result);


        // WITHOUT FUNCTION

//        double[] values = { /* provide your function values here */ };
//        double h = /* specify the step size */;
//
//        double result = integrateWithoutFunction(values, h);
//        System.out.println("Simpson's Three-Eighth Rule result: " + result);
    }

    private static double eval(String equation, double x) {
        Expression expression = new Expression(equation.replace("x", Double.toString(x)));
        return expression.calculate();
    }
}
