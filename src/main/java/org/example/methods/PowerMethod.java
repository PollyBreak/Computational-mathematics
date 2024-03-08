package org.example.methods;

import java.util.Arrays;

public class PowerMethod {


    public static void compute(double[][] matrix, double epsilon) {
        int n = matrix.length;
        double[] eigenVector = new double[n];
        Arrays.fill(eigenVector, 0.0);
        eigenVector[0] = 1.0;
        double prevEigenValue = 0;
        double currEigenValue = 0;

        int iteration = 0;
        System.out.println("Iteration " + iteration);
        System.out.println("λ = " + round(currEigenValue));
        System.out.println("X = " + Arrays.toString(round(eigenVector)));
        do {
            iteration++;
            prevEigenValue = currEigenValue;
            double[] tempVector = multiplyMatrixVector(matrix, eigenVector);
            currEigenValue = Arrays.stream(tempVector).max().getAsDouble();
            double[] normalizedVector = new double[n];
            for (int i = 0; i < n; i++) {
                normalizedVector[i] = tempVector[i] / currEigenValue;
            }
            eigenVector = normalizedVector;
            System.out.println("Iteration " + iteration);
            System.out.println("AX = " + Arrays.toString(round(tempVector)));
            System.out.println("λ = " + round(currEigenValue));
            System.out.println("X = " + Arrays.toString(round(eigenVector)));
        } while (Math.abs(currEigenValue - prevEigenValue) > epsilon);
    }

    public static double[] multiplyMatrixVector(double[][] matrix, double[] vector) {
        int n = matrix.length;
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
        }
        return result;
    }

    public static double round(double val){
        return Math.round(val * (1/0.001)) / (1/0.001);
    }
    public static double[] round(double[] val){
        for (int i = 0; i< val.length; i++) {
            val[i]=round(val[i]);
        }
        return val;
    }

    public static void main(String[] args) {
                double[][] matrix = {
                {25, 1, 2},
                {1, 3, 0},
                {2, 0, -4}
        };

        compute(matrix, 0.005);
    }
}