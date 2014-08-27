/*
 * MathFefaultFunc.java v0.1.0.0 alpha
 * Contains the code used handle default math functions.
 * mailto:umstek@gmail.com
 */
package mathexsolver;

import java.util.TreeMap;

/**
 *
 * @author Wickramaranga
 * @version 0.1.0.0 alpha
 */
public class MathDefaultFunc implements MathExFunctions {

    //Finds natural logarithm.
    double ln(double input) {
        return Math.log(input);
    }

    //Finds base 10 logarithm.
    double lg(double input) {
        return Math.log10(input);
    }

    //Finds logarithm given by base.
    double log(double base, double input) {
        return (Math.log(input) / Math.log(base));
    }

    //Sine.
    double sin(double input) {
        return Math.sin(input);
    }

    //Hyperbolic sine.
    double sinh(double input) {
        return Math.sinh(input);
    }

    //Arc sine. (Sine inverse.)
    double asin(double input) {
        return Math.asin(input);
    }

    //Cosine.
    double cos(double input) {
        return Math.cos(input);
    }

    //Hyperbolic cosine.
    double cosh(double input) {
        return Math.cosh(input);
    }

    //Arc cosine. (Cosine inverse.)
    double acos(double input) {
        return Math.acos(input);
    }

    //Tangent.
    double tan(double input) {
        return Math.tan(input);
    }

    //Hyperbolic tangent.
    double tanh(double input) {
        return Math.tanh(input);
    }

    //Arc tangent. (Tangent inverse.)
    double atan(double input) {
        return Math.atan(input);
    }

    //Cosecant.
    double csc(double input) {
        return 1 / Math.sin(input);
    }

    //Arc cosecant. (Cosecant inverse.)
    double acsc(double input) {
        return Math.asin(1 / input);
    }

    //Secant.
    double sec(double input) {
        return 1 / Math.cos(input);
    }

    //Arc secant. (Secant inverse.)
    double asec(double input) {
        return Math.acos(1 / input);
    }

    //Cotangent.
    double cot(double input) {
        return 1 / Math.tan(input);
    }

    //Arc cotangent. (Cotangent inverse.)
    double acot(double input) {
        return Math.atan(1 / input);
    }

    //Executes a function.
    @Override
    public String execute(String functionName, String argumentsCSV,
            TreeMap<String, Double> variables) throws Exception {

        String[] args = argumentsCSV.split(",");

        for (int i = 0; i < args.length; i++) {
            Solver solver = new Solver(args[i], variables);
            args[i] = solver.solve();
        }

        switch (functionName) {
            case "ln":
                return String.valueOf(ln(Double.parseDouble(args[0])));
            case "lg":
                return String.valueOf(lg(Double.parseDouble(args[0])));
            case "log":
                return String.valueOf(log(Double.parseDouble(args[0]),
                        Double.parseDouble(args[1])));

            case "sin":
                return String.valueOf(sin(Double.parseDouble(args[0])));
            case "sinh":
                return String.valueOf(sinh(Double.parseDouble(args[0])));
            case "asin":
                return String.valueOf(asin(Double.parseDouble(args[0])));

            case "cos":
                return String.valueOf(cos(Double.parseDouble(args[0])));
            case "cosh":
                return String.valueOf(cosh(Double.parseDouble(args[0])));
            case "acos":
                return String.valueOf(acos(Double.parseDouble(args[0])));

            case "tan":
                return String.valueOf(tan(Double.parseDouble(args[0])));
            case "tanh":
                return String.valueOf(tanh(Double.parseDouble(args[0])));
            case "atan":
                return String.valueOf(atan(Double.parseDouble(args[0])));

            case "csc":
                return String.valueOf(csc(Double.parseDouble(args[0])));
            case "acsc":
                return String.valueOf(acsc(Double.parseDouble(args[0])));

            case "sec":
                return String.valueOf(sec(Double.parseDouble(args[0])));
            case "asec":
                return String.valueOf(acsc(Double.parseDouble(args[0])));

            case "cot":
                return String.valueOf(cot(Double.parseDouble(args[0])));
            case "acot":
                return String.valueOf(acot(Double.parseDouble(args[0])));

            default:
                throw new Exception("Function not found: "
                        + functionName + " (in MathDefaultFunc)");
        }
    }

}
