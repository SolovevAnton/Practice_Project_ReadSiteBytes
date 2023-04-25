package com.solovev.util;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.prefs.InvalidPreferencesFormatException;
import java.util.stream.Collectors;

/**
 * A Class to pass through Json strings and create Strings of separate objects or arrays
 */
public class StringParser {
    private final Map<Character, Character> mapOfParenthesis;
    private final char delimiter;

    /**
     * Constructor that creates object with set of possible open and close parenthesis
     *
     * @param map       - map where key is OPEN value, for ex: '[','{' etc, and value is corresponding close parenthesis for ex: ']',}'
     * @param delimiter - delimiter used to separate values, for ex; ','
     */
    public StringParser(Map<Character, Character> map, char delimiter) {
        this.delimiter = delimiter;
        this.mapOfParenthesis = map;
    }

    /**
     * Method that parse the arrayOfChars and builds a new arrayOfChars, that will include all objects of the old one;
     * example in readme paragraph 5.
     * Method doesn't check for Parenthesis correctness. Example: "[[}]" will be parsed, and strings without closing brackets or delimiters will be parsed to the end
     *
     * @param arrayOfChars original arrayOfChars to parse
     * @param indexToStart indexToStart to start with, should be > 0, and less than String.length()
     * @return string that includes a field or an object in the old one
     * @throws IndexOutOfBoundsException if parenthesis are Incorrect Example: "[[}]" will throw exception
     */
    public String parse(char[] arrayOfChars, int indexToStart) throws InvalidPreferencesFormatException {
        StringBuilder resultString = new StringBuilder();
        Deque<Character> openingBrackets = new ArrayDeque<>();
        for (; indexToStart < arrayOfChars.length; indexToStart++) {
            char charToCheck = arrayOfChars[indexToStart];
            if (openingBrackets.isEmpty() &&
                    (mapOfParenthesis.containsValue(charToCheck) || charToCheck == delimiter)) {
                return resultString.toString();
            }
            resultString.append(charToCheck);
            if (mapOfParenthesis.containsValue(charToCheck)) {
                char openBracket = openingBrackets.pollLast();
                if (charToCheck != mapOfParenthesis.get(openBracket)) {
                    throw new InvalidPreferencesFormatException(
                            String.format("Expected: %s , but was: %s", mapOfParenthesis.get(openBracket), charToCheck));
                }
                if (openingBrackets.isEmpty()) {
                    return resultString.toString();
                }
            }
            if (mapOfParenthesis.containsKey(charToCheck)) {
                openingBrackets.add(charToCheck);
            }
        }
        return resultString.toString();
    }

    /**
     * Method that calculates number of symbols that are Keys or values in mapOfParenthesis.
     *
     * @param string - string to be analyzed
     * @return Map with chars in mapOfParenthesis as keys and values the number of occurrences.
     */
    public Map<Character, Long> buildMap(String string) {
        Predicate<Character> isInMap = c -> mapOfParenthesis.containsKey(c) || mapOfParenthesis.containsValue(c);
        Function<Map.Entry<Character, List<Character>>, Integer> getCount = entry -> entry
                .getValue()
                .size();

        return string
                .chars()
                .mapToObj(c -> (char) c)
                .filter(isInMap)
                .collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StringParser that = (StringParser) o;

        if (delimiter != that.delimiter) return false;
        return Objects.equals(mapOfParenthesis, that.mapOfParenthesis);
    }

    @Override
    public int hashCode() {
        int result = mapOfParenthesis != null ? mapOfParenthesis.hashCode() : 0;
        result = 31 * result + (int) delimiter;
        return result;
    }

    @Override
    public String toString() {
        return "StringParser{" +
                "mapOfParenthesis=" + mapOfParenthesis +
                ", delimiter=" + delimiter +
                '}';
    }
}
