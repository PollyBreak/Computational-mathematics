package org.example.methods.integrals;

import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.License;

import java.util.Scanner;
import java.util.function.Function;

public class TrapezoidalRule {
    public static double integrate(Function<Double, Double> f, double a, double b, int n) {
        double h = (b - a) / n;
        double sum = 0.5 * (f.apply(a) + f.apply(b));

        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            sum += f.apply(x);
        }

        return h * sum;
    }

    public static double integrateWithoutFunction(double[] values, double h) {
        int n = values.length;
        double sum = values[0] + values[n - 1];

        for (int i = 1; i < n - 1; i++) {
            sum += 2 * values[i];
        }

        return h / 2 * sum;
    }


    public static void main(String[] args) {
        double a = 0; //(lower limit of integration
        double b = 4; //upper limit
        int n = 5;  //number of subintervals or steps, including both the lower and upper limits

        License.iConfirmNonCommercialUse("true1");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Equation: ");
        String equation = scanner.nextLine();
        Function<Double, Double> function = x -> eval(equation, x);

        double result = integrate(function, a, b, n);
        System.out.println("Trapezoidal Rule result: " + result);


        ///// WITHOUT FUNCTION

//        double[] values = { 3,5,6,7};
//        double h = 2;
//
//        double result = integrateWithoutFunction(values, h);
//        System.out.println("Trapezoidal Rule result: " + result);
    }

    private static double eval(String equation, double x) {
        Expression expression = new Expression(equation.replace("x", Double.toString(x)));
        return expression.calculate();
    }
}
