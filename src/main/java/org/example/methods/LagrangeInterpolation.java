package org.example.methods;

public class LagrangeInterpolation {

    public static double interpolate(double x, double[] xValues, double[] yValues) {
        if (xValues.length != yValues.length || xValues.length < 2) {
            throw new IllegalArgumentException("Invalid input: xValues and yValues must have the same length, and should have at least 2 elements.");
        }

        double result = 0;
        for (int i = 0; i < xValues.length; i++) {
            double term = yValues[i];
            for (int j = 0; j < xValues.length; j++) {
                if (j != i) {
                    term *= (x - xValues[j]) / (xValues[i] - xValues[j]);
                }
            }
            System.out.println("Term #"+(i+1)+": "+term);
            result += term;
        }
        return result;
    }

    public static void main(String[] args) {
        double[] xValues = {5, 6,9, 11};
        double[] yValues = {12,13,14,16};
        double x = 10;

        double interpolatedValue = interpolate(x, xValues, yValues);
        System.out.println("Interpolated value at x = " + x + " is " + interpolatedValue);
    }
}
