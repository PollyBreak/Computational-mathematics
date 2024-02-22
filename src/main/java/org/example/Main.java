package org.example;

import org.example.methods.*;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.License;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        License.iConfirmNonCommercialUse("true1");

        Scanner scanner = new Scanner(System.in);

//        System.out.print("Equation: ");
//        String equation = scanner.nextLine();
//        Function<Double, Double> function = x -> eval(equation, x);
//
//        System.out.print("Derivative: ");
//        String equation2 = scanner.nextLine();
//        Function<Double, Double> derFunc = x -> eval(equation2, x);
//
//        System.out.print("a: ");
//        double a = scanner.nextDouble();
//        System.out.print("b: ");
//        double b = scanner.nextDouble();
//
//        System.out.print("Precision: ");
//        double precision = scanner.nextDouble();
//        System.out.println();

//        BisectionMethod bisectionMethod= new BisectionMethod();
//        FalsePositionMethod falsePositionMethod = new FalsePositionMethod();
//        SecantMethod secantMethod = new SecantMethod();
//        NewtonRaphsonMethod newtonRaphsonMethod = new NewtonRaphsonMethod();
//        GaussSeidelMethod gaussSeidelMethod = new GaussSeidelMethod();
//        JacobIterationMethod jacobIterationMethod = new JacobIterationMethod();
          PowerMethod powerMethod = new PowerMethod();

//        System.out.println("Bisection method \n");
//        double solution1 = bisectionMethod.computeRoot(function, a, b, precision);
//        System.out.printf("Approximate value of the root: %.3f%n", solution1);
//        System.out.println();
//
//        System.out.println("False Position method \n");
//        double solution2 = falsePositionMethod.computeRoot(function, a, b, precision);
//        System.out.printf("Approximate value of the root: %.3f%n", solution2);
//        System.out.println();
//
//        System.out.println("Secant method \n");
//        double solution3 = secantMethod.computeRoot(function, a, b, precision, 300);
//        System.out.printf("Approximate value of the root: %.3f%n", solution3);
//        System.out.println();
//
//        System.out.println("Newton Raphson method \n");
//        double solution4 = newtonRaphsonMethod.computeRoot(function, derFunc, a, precision);
//        System.out.printf("Approximate value of the root: %.3f%n", solution4);


//        double[][] coefficients = {
//                {10, -2, -1, -1},
//                {-2, 10, -1, -1},
//                {-1, -1, 10, -2},
//                {-1, -1, -2, 10}
//        };
//        double[] constants = {3, 15, 27, -9};
//
//        double[] solution = gaussSeidelMethod.computeRoot(coefficients, constants,
//                new double[]{0,0,0}, 1000, 0.0001);

//        double[] solution = jacobIterationMethod.computeRoots(coefficients, constants,
//                new double[]{0,0,0,0}, 1000, 0.0001);

//        double[][] matrix = {
//                {25, 1, 2},
//                {1, 3, 0},
//                {2, 0, -4}
//        };
//
//        powerMethod.compute(matrix, 0.005);

        CurveFitting curveFitting = new CurveFitting();
        double[] xData = {2,4,6,8};
        double[] yData = {25, 38, 56, 84};

        double[] coefficients = curveFitting.fitCurve(xData, yData, CurveFitting.CurveType.EXPONENTIAL);

        System.out.println("Fitted Coefficients:");
        for (double coefficient : coefficients) {
            System.out.println(coefficient);
        }


    }


    private static double eval(String equation, double x) {
        Expression expression = new Expression(equation.replace("x", Double.toString(x)));
        return expression.calculate();
    }
}