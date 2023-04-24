package com.solovev.util;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;

/**
 * A Class to pass through Json strings and create Strings of separate objects or arrays
 */
public class StringParser {
    private final Set<Character> SetOfOpening = Set.of('[', '{');
    private final char delimiter = ',';
    private final Set<Character> SetOfClosing = Set.of(']', '}');
    //TODO add check for parenthesis correctness
    /**
     * Method that parse the arrayOfChars and builds a new arrayOfChars, that will include all objects of the old one;
     * example in readme paragraph 5.
     * Method doesnot check for Parenthesis correctness. Example: "[[}]" will be parsed, and strings without closing brackets or delimiters will be parsed to the end
     *
     * @param arrayOfChars original arrayOfChars to parse
     * @param indexToStart indexToStart to start with, should be > 0, and less than String.length()
     * @return string that includes a field or an object in the old one
     */
    public String parse(char[] arrayOfChars, int indexToStart) {
        StringBuilder resultString = new StringBuilder();
        Deque<Character> openingBrackets = new ArrayDeque<>();
        for (; indexToStart < arrayOfChars.length; indexToStart++) {
            char charToCheck = arrayOfChars[indexToStart];
            if (openingBrackets.isEmpty() &&
                    (SetOfClosing.contains(charToCheck) || charToCheck == delimiter)) {
                return resultString.toString();
            }
            resultString.append(charToCheck);
            if (SetOfClosing.contains(charToCheck)) {
                openingBrackets.pollLast();
                if (openingBrackets.isEmpty()) {
                    return resultString.toString();
                }
            }
            if (SetOfOpening.contains(charToCheck)) {
                openingBrackets.add(charToCheck);
            }
        }
        return resultString.toString();
    }
}
