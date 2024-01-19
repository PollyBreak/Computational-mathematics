package org.example;

import org.example.methods.BisectionMethod;
import org.example.methods.FalsePositionMethod;
import org.example.methods.NewtonRaphsonMethod;
import org.example.methods.SecantMethod;
import org.mariuszgromada.math.mxparser.Expression;

import java.util.Scanner;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите уравнение в виде функции (например, x^2 - 2): ");
        String equation = scanner.nextLine();
        Function<Double, Double> function = x -> eval(equation, x);

        System.out.print("Введите производную в виде функции (например, x^2 - 2): ");
        String equation2 = scanner.nextLine();
        Function<Double, Double> derFunc = x -> eval(equation2, x);

        System.out.print("Введите начальную точку a: ");
        double a = scanner.nextDouble();
//        System.out.print("Введите конечную точку b: ");
//        double b = scanner.nextDouble();

        System.out.print("Введите точность: ");
        double precision = scanner.nextDouble();

        BisectionMethod bisectionMethod= new BisectionMethod();
        FalsePositionMethod falsePositionMethod = new FalsePositionMethod();
        SecantMethod secantMethod = new SecantMethod();
        NewtonRaphsonMethod newtonRaphsonMethod = new NewtonRaphsonMethod();

//        System.out.println("Bisection method \n");
//        double solution1 = bisectionMethod.computeRoot(function, a, b, precision);
//        System.out.printf("Приближенное значение корня: %.3f%n", solution1);
//        System.out.println();
//
//        System.out.println("False Position method \n");
//        double solution2 = falsePositionMethod.computeRoot(function, a, b, precision);
//        System.out.printf("Приближенное значение корня: %.3f%n", solution2);
//        System.out.println();

//        System.out.println("Secant method \n");
//        double solution3 = secantMethod.computeRoot(function, a, b, precision, 300);
//        System.out.printf("Приближенное значение корня: %.3f%n", solution3);
//        System.out.println();

        System.out.println("Newton Raphson method \n");
        double solution3 = newtonRaphsonMethod.computeRoot(function, derFunc, a, precision);
        System.out.printf("Приближенное значение корня: %.3f%n", solution3);
    }


    private static double eval(String equation, double x) {
        Expression expression = new Expression(equation.replace("x", Double.toString(x)));
        return expression.calculate();
    }
}