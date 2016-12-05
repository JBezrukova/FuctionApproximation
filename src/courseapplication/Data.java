/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseapplication;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Function;
import org.mariuszgromada.math.mxparser.mXparser;

/**
 *
 * @author Julia
 */
public class Data {
    private double[] xValues;
    private double[] yValues;
    private double x;
    private mXparser mXparser = new mXparser();

    public Data loadFromFile(String fileName, String function) {
        Scanner scanner = new Scanner(System.in);
        int numberOfElements = 0;

        Scanner scanner2 = null;
        try {
            scanner2 = new Scanner(new File(fileName));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        String str = scanner2.nextLine();
        Scanner scanner1 = new Scanner(str);
        while (scanner1.hasNextDouble()) {
            numberOfElements++;
            scanner1.nextDouble();
        }
        scanner1.close();
        xValues = new double[numberOfElements];
        yValues = new double[numberOfElements];
        scanner = new Scanner(str);
        int i = 0;
        while (i < numberOfElements) {
            xValues[i] = scanner.nextDouble();
            i++;
        }

        for (int j= 0; j < xValues.length; j++){
            yValues[j] = function(function, xValues[j]);
        }

        return this;
    }

    public double function(String text, Double x) {
        Function expression;
        expression = new Function(text);
        double result;
        result = expression.calculate(new Argument("x", x));
        return result;
    }


    public void printData(double[] x, double[] y) {
        String result = "Data: " + "\n";

        result += "X's values: " + "\n";
        result += Arrays.toString(x) + "\n";
        result += "F(x) values: " + "\n";
        result += Arrays.toString(y) + "\n";
        CourseApplication1.getInstance().toPanel(result);
    }

    public double[] getxValues() {
        return xValues;
    }

    public double[] getyValues() {
        return yValues;
    }

}
