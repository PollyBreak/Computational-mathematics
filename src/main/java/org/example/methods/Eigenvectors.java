package org.example.methods;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

public class Eigenvectors {
    public static void main(String[] args) {
        // Create a sample matrix
        double[][] matrixData = {
                {2, 0, 0},
                {0, 3, 4},
                {0, 4, 9}
        };

        RealMatrix matrix = new Array2DRowRealMatrix(matrixData);

        // Perform eigen decomposition
        EigenDecomposition decomposition = new EigenDecomposition(matrix);

        // Get real eigenvalues
        double[] realEigenvalues = decomposition.getRealEigenvalues();
        System.out.println("Eigenvalues: ");
        for (double eigenvalue : realEigenvalues) {
            System.out.println(eigenvalue);
        }

        // Get eigenvectors
        RealMatrix eigenvectors = decomposition.getV();
        System.out.println("Eigenvectors:");
        for (int i = 0; i < eigenvectors.getRowDimension(); i++) {
            RealVector eigenvector = eigenvectors.getRowVector(i);
            System.out.println("Eigenvalue " + realEigenvalues[i] + ": " + eigenvector);
        }
    }
}
