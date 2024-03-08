package org.example.methods.lecture10;

public class RungeKuttaMethod {
    public static void main(String[] args) {
        double h = 0.1; // Step size
        double xTarget = 0.2; // Target value of x
        double x0 = 0.0; // Initial value of x
        double y0 = 1.0; // Initial value of y

        double result = rungeKuttaMethod(x0, y0, xTarget, h);
        System.out.println("Approximate value of y at x = " + xTarget + ": " + result);
    }

    private static double function(double x, double y) {
//        return (Math.pow(y,2)- Math.pow(x,2))/(Math.pow(y,2)+ Math.pow(x,2));
//        return x+y*y;
//        return (2*x*y+Math.pow(Math.E,x))/(Math.pow(x,2)+x*Math.pow(Math.E,x));
        return x+y;
    }

    private static double rungeKuttaMethod(double x0, double y0, double xTarget, double h) {
        double x = x0;
        double y = y0;

        while (x < xTarget) {
            double k1 = h * function(x, y);
            double k2 = h * function(x + h/2.0, y + k1/2.0);
            double k3 = h * function(x + h/2.0, y + k2/2.0);
            double k4 = h * function(x + h, y + k3);

            y = y + (k1 + 2*k2 + 2*k3 + k4)/6.0;
            x = x + h;
        }

        return y;
    }
}
