/*
 * ExFunctionInterfaces.java v0.1.0.0 alpha
 * Contains the code used handle various kinds of functions.
 * mailto:umstek@gmail.com
 */
package mathexsolver;

import java.util.*;

/**
 *
 * @author Wickramaranga
 * @version 0.1.0.0 alpha
 */
interface MathExFunctions {

    //Executes the called function and returns result.
    String execute(String functionName, String argumentsCSV,
            TreeMap<String, Double> variables) throws Exception;

    //Lists signatures of all functions
    //TreeMap<String, Integer> getSignatures();
    
    //Returns information about function.
    
}
