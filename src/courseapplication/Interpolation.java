/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseapplication;

/**
 *
 * @author Julia
 */
public class Interpolation {

    public double lagranjeInterpolation(double[] xValues, double[] yValues, double x) {
        double lagrange = 0;
        double lagranjeMultiply = 1;
        for (int i = 0; i < xValues.length; i++) {
            lagranjeMultiply = yValues[i];
            for (int j = 0; j < xValues.length; j++) {
                if (i != j) {
                    lagranjeMultiply *= (x - xValues[j]) / (xValues[i] - xValues[j]);
                }
            }
            lagrange += lagranjeMultiply;
        }
        return lagrange;
    }

    public Double[] singleDivisionMethod(double[] x, double[] y) {
        
        double[][] matrix = createMatrix(x, y);        
        int height = x.length;
        int width = x.length + 1;
        int j = 0;
        int k = 0;
        double l;
        for (int n = 1; n < height; n++) {
            for (int i = k + 1; i < height; i++) {
                if (matrix[k][j] == 0) {
                    double[] temp = matrix[k];
                    //меняет строки метстами, если ведущий элемент = 0
                    for (int s = i; s < height; s++) {
                        if (matrix[s][j] != 0) {
                            matrix[k] = matrix[s];
                            matrix[s] = temp;
                        }
                    }
                }
                l = matrix[i][j] / matrix[k][j];
                for (int s = 0; s < width; s++) {
                    matrix[i][s] = matrix[i][s] - matrix[j][s] * l;
                }
            }
            k++;
            j++;
        }

        Double[] answers = new Double[height];

        answers = findingAnswers(width, height, matrix, answers);

        return answers;
    }

    private Double[] findingAnswers(int width, int height, double[][] matrix, Double[] answers) {
        int j;
        int k;
        j = 1;
        for (int i = height - 1; i >= 0; i--) {
            answers[i] = matrix[i][width - 1] / matrix[i][width - 1 - j];
            if (i > 0) {
                for (k = i - 1; k >= 0; k--) {
                    matrix[k][width - 1] = matrix[k][width - 1] - (matrix[k][width - 1 - j] * answers[i]);
                }
                j++;
            }
        }

        return answers;
    }
    
    public double errorLagranje(double x, double f, double[] xV, double[] y) {
        return Math.abs(f - lagranjeInterpolation(xV, y, x));
    }
    
    private double[][] createMatrix(double[] x, double[] y){
        double[][] matrix = new double[y.length][y.length + 1];
        for (int i = 0; i < y.length; i++){
            for (int j = 0; j < y.length; j++){
                matrix[i][j] = Math.pow(x[i], j);
            }
            matrix[i][y.length] = y[i];
        }
        return matrix;
    }
    
    public double polinomInterpolation(Double[] coef, double x){
        double result = 0;
        for (int i = 0; i < coef.length; i++){
            result += coef[i]*Math.pow(x, i);
        }
        return result;
    }

}
