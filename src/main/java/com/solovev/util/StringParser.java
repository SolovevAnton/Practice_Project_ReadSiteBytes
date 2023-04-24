package com.solovev.util;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;

/**
 * A Class to pass through Json strings and create Strings of separate objects or arrays
 */
public class StringParser {
    private final Set<Character> SetOfOpening = Set.of('[', '{');
    private final Character delimiter = ',';
    private final Set<Character> SetOfClosing = Set.of(']', '}');

    /**
     * Method that parse the arrayOfChars and builds a new arrayOfChars, that will include all objects of the old one;
     * example in readme paragraph 5.
     * Method doesnot check for Parenthesis correctness. Example: "[[}]" will be paresed
     *
     * @param arrayOfChars original arrayOfChars to parse
     * @param indexToStart indexToStart to start with, should be > 0, and less than String.length()
     * @return arrayOfChars that includes a field or an object in the old one
     */
    public String parse(char[] arrayOfChars, int indexToStart) {
        StringBuilder resultString = new StringBuilder();
        Deque<Character> openingBrackets = new ArrayDeque<>();
        Deque<Character> closingBrackets = new ArrayDeque<>();
        for (; indexToStart < arrayOfChars.length; indexToStart++) {
            char charToCheck = arrayOfChars[indexToStart];
            if (SetOfOpening.contains(charToCheck)) {
                openingBrackets.add(charToCheck);
            }
            if (openingBrackets.size() == 0
                    && (SetOfClosing.contains(charToCheck) || charToCheck == delimiter)) {
                return resultString.toString();
            }
            if (SetOfClosing.contains(charToCheck)) {
                System.out.println("im here");
                openingBrackets.peekLast();
            }
            resultString.append(charToCheck);
        }
        return resultString.toString();
    }
}
