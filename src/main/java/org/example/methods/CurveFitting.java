package org.example.methods;

import org.apache.commons.math3.fitting.leastsquares.*;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Pair;

public class CurveFitting {
    public enum CurveType {
        LINEAR,
        PARABOLA,
        EXPONENTIAL,
        QUADRATIC,
        LINEAR_QUADRATIC,
        LOG_POWER
    }

    public static double[] fitCurve(double[] xData, double[] yData, CurveType curveType) {
        MultivariateJacobianFunction function = new MultivariateJacobianFunction() {
            @Override
            public Pair<RealVector, RealMatrix> value(RealVector parameters) {
                int n = xData.length;
                int m = parameters.getDimension();
                double[] yModel = new double[n];
                double[][] jacobian = new double[n][m];

                for (int i = 0; i < n; ++i) {
                    double x = xData[i];
                    double y = yData[i];
                    switch (curveType) {
                        case LINEAR:
                            yModel[i] = parameters.getEntry(0) + parameters.getEntry(1) * x;
                            jacobian[i][0] = 1.0;
                            jacobian[i][1] = x;
                            break;
                        case PARABOLA:
                            yModel[i] = parameters.getEntry(0) + parameters.getEntry(1) * x + parameters.getEntry(2) * x * x;
                            jacobian[i][0] = 1.0;
                            jacobian[i][1] = x;
                            jacobian[i][2] = x * x;
                            break;
                        case EXPONENTIAL:
                            double expTerm = FastMath.exp(parameters.getEntry(1) * x);
                            yModel[i] = parameters.getEntry(0) * expTerm;
                            jacobian[i][0] = expTerm;
                            jacobian[i][1] = parameters.getEntry(0) * x * expTerm;
                            break;
                        case QUADRATIC:
                            yModel[i] = parameters.getEntry(0) + parameters.getEntry(1) * x * x;
                            jacobian[i][0] = 1.0;
                            jacobian[i][1] = x * x;
                            break;
                        case LINEAR_QUADRATIC:
                            yModel[i] = parameters.getEntry(0) * x + parameters.getEntry(1) * x * x;
                            jacobian[i][0] = x;
                            jacobian[i][1] = x * x;
                            break;
                        case LOG_POWER:
                            double aFit = parameters.getEntry(0);
                            double bFit = parameters.getEntry(1);
                            yModel[i] = aFit * FastMath.pow(10, bFit * FastMath.log10(x));
                            jacobian[i][0] = FastMath.pow(10, bFit * FastMath.log10(x));
                            jacobian[i][1] = aFit * FastMath.pow(10, bFit * FastMath.log10(x)) * FastMath.log(10) * FastMath.log10(x);
                            break;
                    }
                }
                return new Pair<>(new ArrayRealVector(yModel), new Array2DRowRealMatrix(jacobian));
            }
        };

        double[] initialGuess;
        switch (curveType) {
            case LINEAR:
                initialGuess = new double[]{0, 0};
                break;
            case PARABOLA:
                initialGuess = new double[]{0, 0, 0};
                break;
            case EXPONENTIAL:
                initialGuess = new double[]{0, 0};
                break;
            case QUADRATIC:
                initialGuess = new double[]{0, 0};
                break;
            case LINEAR_QUADRATIC:
                initialGuess = new double[]{0, 0};
                break;
            case LOG_POWER:
                initialGuess = new double[]{1, 1};
                break;
            default:
                initialGuess = new double[]{0, 0};
                break;
        }
        LeastSquaresOptimizer optimizer = new LevenbergMarquardtOptimizer();
        ParameterValidator parameterValidator = new ParameterValidator() {
            @Override
            public RealVector validate(RealVector point) {
                for (int i = 0; i < point.getDimension(); i++) {
                    double value = point.getEntry(i);
                    if (Math.abs(value) < 1e-12) {
                        point.setEntry(i, 0.0);
                    }
                }
                return point;
            }
        };
        LeastSquaresBuilder builder = new LeastSquaresBuilder()
                .model(function)
                .target(yData)
                .start(initialGuess)
                .lazyEvaluation(false)
                .maxEvaluations(1000)
                .maxIterations(1000)
                .parameterValidator(parameterValidator);
        RealVector optimum = optimizer.optimize(builder.build()).getPoint();
        return optimum.toArray();
    }

    public static void main(String[] args) {
        double[] xData = {2,4,6,8};
        double[] yData = {25, 38, 56, 84};

        double[] coefficients = fitCurve(xData, yData, CurveFitting.CurveType.EXPONENTIAL);

        System.out.println("Fitted Coefficients:");
        for (double coefficient : coefficients) {
            System.out.println(coefficient);
        }
    }
}
