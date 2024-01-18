package org.example;

import org.example.methods.BisectionMethod;
import org.example.methods.FalsePositionMethod;
import org.mariuszgromada.math.mxparser.Expression;

import java.util.Scanner;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите уравнение в виде функции (например, x^2 - 2): ");
        String equation = scanner.nextLine();
        Function<Double, Double> function = x -> eval(equation, x);
        System.out.print("Введите начальную точку a: ");
        double a = scanner.nextDouble();
        System.out.print("Введите конечную точку b: ");
        double b = scanner.nextDouble();
        System.out.print("Введите точность: ");
        double precision = scanner.nextDouble();

        BisectionMethod bisectionMethod= new BisectionMethod();
        FalsePositionMethod falsePositionMethod = new FalsePositionMethod();

        System.out.println("Bisection method \n");
        double solution1 = bisectionMethod.computeRoot(function, a, b, precision);
        System.out.printf("Приближенное значение корня: %.3f%n", solution1);
        System.out.println();

        System.out.println("False Position method \n");
        double solution2 = falsePositionMethod.computeRoot(function, a, b, precision);
        System.out.printf("Приближенное значение корня: %.3f%n", solution2);
    }


    private static double eval(String equation, double x) {
        Expression expression = new Expression(equation.replace("x", Double.toString(x)));
        return expression.calculate();
    }
}