/*
 * Solver.java v0.1.0.0 alpha
 * Contains the logic needed to solve a mathematical (mathex™) expression.
 * mailto:umstek@gmail.com
 */
package mathexsolver;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 *
 * @author Wickramaranga
 * @version 0.1.0.0 alpha
 */
public class Solver {

//<editor-fold defaultstate="collapsed" desc="Construction; initialization">
    //Expression to solve
    private String exp;
    //List of function calls
    private ArrayList<String> func;
    //Variables
    private TreeMap<String, Double> var;

    //Misc signs used.
    private final String MINUS_SIGN = "-",
            PLUS_SIGN = "+",
            DIVIDE_SIGN = "/",
            MULTIPLY_SIGN = "*",
            POWER_SIGN = "^";

    /**
     *
     * @param expression Expression to solve
     * @param var Variables
     */
    public Solver(String expression, TreeMap<String, Double> var) {
        this.exp = expression;
        this.func = new ArrayList<>();
        this.var = var;
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Precomputation">
    private void unSpace() {
        //Remove all whitespaces inside expression.
        exp = exp.replaceAll("\\s", "");
    }

    private void funcExtract() throws Exception {

        //Add each and every function contents to an ArrayList.
        //Replace #func[...] with @ + ArrayList index.
        //loopForAllHashes:
        while (exp.contains("#")) {
            //Function starts with a hash sign.
            int hashIndex = exp.indexOf("#");

            if (exp.indexOf('[') == -1 || exp.indexOf('[') < hashIndex) {
                throw new Exception("Cannot find arguments to the function");
            }

            //Using opened square braces count in case of nested functions.
            int openBracks = 0;

            //hashIndex + 2 because at least 1 letter for a function.
            //loopForAHash:
            for (int i = hashIndex + 2; i < exp.length(); i++) {

                //Opening [
                if (exp.charAt(i) == '[') {
                    openBracks++;

                    //Closing ]
                } else if (exp.charAt(i) == ']') {
                    openBracks--;

                    //All square braces closed.
                    if (openBracks == 0) {
                        //Add function to ArrayList.
                        func.add(exp.substring(hashIndex, i + 1));
                        //Replace function with ArrayList index coding. "@" + ix
                        //Regex again.(mona magulakda) Quote with \Q and \E,
                        //so square brackets are not misinterpreted.
                        exp = exp.replaceFirst(
                                "\\Q" + func.get(func.size() - 1) + "\\E",
                                "@" + (func.size() - 1)
                        );

                        break;
                    }
                }
            }

            //Every opened square bracket must be closed
            if (openBracks != 0) {
                throw new Exception("Cannot find matching "
                        + "closing square bracket or invalid function name: "
                        + exp.substring(hashIndex));
            }
        }

    }

    private void generalize() throws Exception {
        //Remove all whitespace charactors
        unSpace();
        //Code functions with @ + funcIndex
        funcExtract();

        //(...)(...) --> (...)*(...)
        //123@0 --> 123*@0
        //Correct all repeated operators.
        //Regex is everywhere.
        exp = exp.replace(")(", ")*(").
                replaceAll("(?<=\\d+)(@)", "\\*@").
                replaceAll("(?<=\\d+)(\\Q$\\E)", "\\*\\$").
                replaceAll("\\++", "\\+").
                replaceAll("\\-+", "\\-").
                replaceAll("\\*+", "\\*").
                replaceAll("\\\\+", "\\\\").
                replaceAll("\\^+", "\\^");

        exp = exp.trim(); //Useless
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Solve - entry point">
//TODO: Improve error handling.
    /**
     * Solve expression
     *
     * @return Solved expression.
     */
    public String solve() {

        try {
            //Generalize
            generalize();
            //Solve expression
            exp = String.valueOf(Double.parseDouble(selectAndSolve(exp)));

        } catch (Exception e) {
            //FIXME
            //Error - handle and show output to user.
            exp = "ERROR. " + e.getMessage();
        }

        return exp;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Main logic aka hard part">
    //Finds whether character is used as an operator.
    private boolean isOperator(char Char) {
        return Char == PLUS_SIGN.charAt(0)
                || Char == MINUS_SIGN.charAt(0)
                || Char == DIVIDE_SIGN.charAt(0)
                || Char == MULTIPLY_SIGN.charAt(0)
                || Char == POWER_SIGN.charAt(0);
    }

    //Finds matching bracket within the string.
    private int findMatchingBracketIndex(String expWBrac, int start) throws Exception {

        //One bracket is opened.
        int openedCount = 1;
        for (int i = start + 1; i < expWBrac.length(); i++) {

            //Bracket Closed.
            if (expWBrac.charAt(i) == ')') {
                openedCount--;

                //All brackets closesd.
                if (openedCount == 0) {
                    return i;
                }

                //New bracket opened.
            } else if (expWBrac.charAt(i) == '(') {
                openedCount++;
            }
        }

        throw new Exception("Cannot find matching bracket");
    }

    //XXX
    //Typed, corrected and deleted more than 3 times,
    //each time more than 60 lines (excluding empty or comment lines) of code :(
    //But finally, it's done with less than 30 lines.
    private String solveExpWithBrackets(String expWBrac) throws Exception {
        //For String concatenation.
        StringBuilder strToSolve = new StringBuilder();

        //Expression contains brackets.
        while (expWBrac.contains("(")) {
            //Find first bracket.
            int firstBrac = expWBrac.indexOf("(");
            //Append everything before openning paranthesis.
            strToSolve.append(expWBrac.substring(0, firstBrac));

            //If neither the string starts with a paranthesis,
            //nor operator exist before parantheses,
            //append a multiplication sign.
            if (!expWBrac.startsWith("(")
                    && !isOperator(expWBrac.charAt(firstBrac - 1))) {
                strToSolve.append(MULTIPLY_SIGN);
            }

            //Find matching bracket.
            int matchBrac = findMatchingBracketIndex(expWBrac, firstBrac);
            //Solve what's inside brackets.
            String temp = selectAndSolve(
                    expWBrac.substring(firstBrac + 1, matchBrac)
            );
            //Append it.
            strToSolve.append(temp);

            //If not the character after selected paranthesis is an operator,
            //append a multiplication sign.
            if (!(expWBrac.length() - 1 == matchBrac)
                    && !isOperator(expWBrac.charAt(matchBrac + 1))) {
                strToSolve.append(MULTIPLY_SIGN);
            }

            //Remove everything added to strToSolve from expWBrac,
            //if there is any remainning characters.
            expWBrac = expWBrac.substring(matchBrac + 1);
        }

        //Append remains if any.
        strToSolve.append(expWBrac);

        //Solve and return the final strToSolve.
        return selectAndSolve(strToSolve.toString());
    }

    private String selectAndSolve(String expression) throws Exception {

        if (expression.contains("(")) {
            //Redirect to bracket solver
            expression = solveExpWithBrackets(expression);

        } else if ((expression.contains(MINUS_SIGN)
                || expression.contains(PLUS_SIGN))
                && !isSciNumOrOperator(expression)) {
            //Redirect to plus minus solver
            expression = solvePlusMinus(expression);

        } else if ((expression.contains(MULTIPLY_SIGN))
                || (expression.contains(DIVIDE_SIGN))) {
            //Redirect to divide multiply solver
            expression = solveMultiplyDivide(expression);

        } else if (expression.contains(POWER_SIGN)) {
            //Redirect to power solver
            expression = solvePower(expression);

        } else {
            //Redirect to atom solver
            expression = solveAtom(expression);

        }

        return expression;
    }

    //Finds whether contains +,- only used for scientific notation
    //or to represent a negative number.
    private boolean isSciNumOrOperator(String expression) {

        //First set to true.
        boolean onlySciNot = true;

        //Check for any +, - used not for Scientific notation
        //or not to represent a negative value.
        for (int i = 1; i < expression.length(); i++) {
            //+ or - not following an E.
            if ((expression.charAt(i) == '+' || expression.charAt(i) == '-')
                    && expression.charAt(i - 1) != 'E'
                    && !isOperator(expression.charAt(i - 1))) {

                return false;
            }
        }

        return onlySciNot;
    }

    private String solvePlusMinus(String expression) throws Exception {

        //Oops... It was very difficult to find this regex out. :(
        //Split string by + and - where they are not immediately after an E
        //or an operator.
        String[] subExp = expression.split(
                "(?<!([E]|"
                + "\\Q" + PLUS_SIGN + "\\E|"
                + "\\Q" + MINUS_SIGN + "\\E|"
                + "\\Q" + MULTIPLY_SIGN + "\\E|"
                + "\\Q" + DIVIDE_SIGN + "\\E|"
                + "\\Q" + POWER_SIGN + "\\E"
                + "))(\\+|\\-)"
        );

        //Operator (+,-) sequence.
        ArrayList<String> operators = new ArrayList<>();

        //Fill operator sequence out.
        for (int i = 1; i < expression.length(); i++) {
            if ((expression.charAt(i) == '+' || expression.charAt(i) == '-')
                    && expression.charAt(i - 1) != 'E'
                    && !isOperator(expression.charAt(i - 1))) {

                operators.add(String.valueOf(expression.charAt(i)));
            }
        }

        //Solve left-most expression of the string.
        double value = Double.parseDouble(selectAndSolve(subExp[0]));

        //Solve other expressions and do operations with first value.
        for (int i = 1; i < subExp.length; i++) {
            if (operators.get(i - 1).equals("+")) {
                //Add
                value += Double.parseDouble(selectAndSolve(subExp[i]));
            } else {
                //Subtract
                value -= Double.parseDouble(selectAndSolve(subExp[i]));
            }
        }

        //Return solved sub expression.
        return String.valueOf(value);
    }

    private String solveMultiplyDivide(String expression) throws Exception {

        //This regex was easy though.
        //Split string by * and /.
        String[] subExp = expression.split("(\\*|\\/)");

        //Operator (*,/) sequence.
        ArrayList<String> operators = new ArrayList<>();

        //Fill operator sequence out.
        for (int i = 1; i < expression.length(); i++) {
            if ((expression.charAt(i) == '*' || expression.charAt(i) == '/')) {

                operators.add(String.valueOf(expression.charAt(i)));
            }
        }

        //Solve left-most expression of the string.
        double value = Double.parseDouble(selectAndSolve(subExp[0]));

        //Solve other expressions and do operations with first value.
        for (int i = 1; i < subExp.length; i++) {
            if (operators.get(i - 1).equals("*")) {
                //Multiply
                value *= Double.parseDouble(selectAndSolve(subExp[i]));
            } else {
                //Divide
                value /= Double.parseDouble(selectAndSolve(subExp[i]));
            }
        }

        //Return solved sub expression.
        return String.valueOf(value);
    }

    private String solvePower(String expression) throws Exception {

        //Splits expression by ^.
        String[] subExp = expression.split("(\\^)");

        //Solve left-most expression of the string.
        double value = Double.parseDouble(selectAndSolve(subExp[0]));

        //Solve other expressions and do operations with first value.
        for (int i = 1; i < subExp.length; i++) {
            //Raise left to power of rignt
            value = Math.pow(
                    value,
                    Double.parseDouble(selectAndSolve(subExp[i]))
            );
        }

        //Return solved sub expression.
        return String.valueOf(value);
    }

    //TODO: Modifications to support @, &, $
    private String solveAtom(String expression) throws Exception {
        //Empty string not allowed here. :(
        if (expression.isEmpty()) {
            throw new Exception("(Contains) Empty expression.");
        }

        //Code derived from first character.
        switch (expression.charAt(0)) {
            //Functions
            case '@':
                String funcString
                        = func.get(Integer.parseInt(expression.substring(1)));
                //ContainingClassFile.FunctionName
                String funcPath
                        = funcString.substring(1, funcString.indexOf('['));
                if (!funcPath.contains(".")) {
                    funcPath = "~." + funcPath;
                }
                //Variable to execute function. (via MathExFunctions interface)
                MathExFunctions funcLoader;

                //Set required class of functions.
                switch (funcPath.split("\\.")[0]) {
                    //TODO: Add other classes of functions.
                    //Default is MathDefaultFunc
                    default:
                        funcLoader = new MathDefaultFunc();
                }

                //Execute function and return result.
                return funcLoader.execute(funcPath.split("\\.")[1],
                        funcString.substring(funcString.indexOf('[') + 1,
                                funcString.lastIndexOf(']')), var);
            //Radix conversions &16_, &
            case '&':
                try {
                    return String.valueOf(
                            Integer.valueOf(
                                    expression.substring(expression.indexOf('_')
                                            + 1),
                                    Integer.valueOf(expression.substring(
                                                    1, expression.indexOf('_')
                                            ))
                            )
                    );

                } catch (NumberFormatException e) {
                    throw new Exception("Radix conversion error.");
                }
            //Variables
            case '$':
                if (var.containsKey(expression.substring(1))) {
                    return var.get(expression.substring(1)).toString();
                } else {
                    throw new Exception("Unknown variable name.");
                }

            //Expression is a number or what?
            default:
                return expression;
        }

    }
//</editor-fold>

}
