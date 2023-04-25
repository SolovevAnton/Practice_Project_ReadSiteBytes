package com.solovev.repository;

import com.solovev.util.StringParser;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.function.Function;
import java.util.prefs.InvalidPreferencesFormatException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Class to get all info from the given URL and return all info as a result
 */
public class UserRepository {
    private final String result;
    /**
     * Map used to define pairs of open close parenthesis, where Keys - are open parenthesis, and Values closed; default : {{=}, [=]}
     */
    private Map<Character, Character> mapOfParenthesis = new HashMap<>();
    /**
     * Delimiter used to separate values in file. By default, is ','
     */
    private char delimiter = ',';

    /**
     * Constructor of the class, that reads all info from URL and puts it in the result string
     * Map of open and closed parenthesis created by default : {{=}, [=]}
     *
     * @param urlIn string that represents absolute URL path
     * @throws MalformedURLException if URL path is incorrect
     * @throws IOException           if stream is failed or interrupted
     */
    public UserRepository(String urlIn) throws MalformedURLException, IOException {
        URL url = new URL(urlIn);
        try (BufferedInputStream inStream = new BufferedInputStream(url.openStream())) {
            byte[] bytes = inStream.readAllBytes();
            result = new String(bytes);
            mapOfParenthesis.put('{', '}');
            mapOfParenthesis.put('[', ']');
        }
    }

    /**
     * Method finds all string occurrences in the result,
     * and returns them as List of content strings inside these strings.
     * Example in readme paragraph 5
     *
     * @param string string to be searched, Case insensitive, format WITHOUT "
     * @return List off all found stings, that has this string as a key, or empty
     * @throws InvalidPreferencesFormatException if parenthesis are Incorrect Example: "[[}]" will throw exception
     */
    public List<String> find(String string) throws InvalidPreferencesFormatException {
        String stringTransform = "\"" + string + "\": ";
        Matcher matcher = Pattern
                .compile(stringTransform, Pattern.CASE_INSENSITIVE)
                .matcher(result);
        List<String> list = new ArrayList<>();
        char[] resultAsArray = result.toCharArray();
        StringParser parser = new StringParser(mapOfParenthesis, delimiter);
        while (matcher.find()) {
            int startOfParsingIndex = matcher.end();
            list.add(parser.parse(resultAsArray, startOfParsingIndex));
        }
        return list;
    }

    /**
     * Method that calculates number of symbols in result String that are Keys or values in mapOfParenthesis
     *
     * @return Map with chars in mapOfParenthesis as keys and values the number of occurrences.
     */
    public Map<Character, Integer> buildMap() {
        Map<Character, Long> parserMap = new StringParser(mapOfParenthesis, delimiter).buildMap(result);
        Function<Map.Entry<Character, Long>, Integer> convertLongToInt = entry -> entry.getValue().intValue();

        return parserMap
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        convertLongToInt));
    }

    public String getResult() {
        return result;
    }

    public void setMapOfParenthesis(Map<Character, Character> mapOfParenthesis) {
        this.mapOfParenthesis = mapOfParenthesis;
    }

    public void setDelimiter(char delimiter) {
        this.delimiter = delimiter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRepository that = (UserRepository) o;

        if (delimiter != that.delimiter) return false;
        if (!Objects.equals(result, that.result)) return false;
        return Objects.equals(mapOfParenthesis, that.mapOfParenthesis);
    }

    @Override
    public int hashCode() {
        int result1 = result != null ? result.hashCode() : 0;
        result1 = 31 * result1 + (mapOfParenthesis != null ? mapOfParenthesis.hashCode() : 0);
        result1 = 31 * result1 + (int) delimiter;
        return result1;
    }

    @Override
    public String toString() {
        return "UserRepository{" +
                "result='" + result + '\'' +
                ", mapOfParenthesis=" + mapOfParenthesis +
                ", delimiter=" + delimiter +
                '}';
    }
}
