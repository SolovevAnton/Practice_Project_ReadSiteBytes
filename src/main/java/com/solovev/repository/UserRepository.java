package com.solovev.repository;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class to get all info from the given URL and return all info as a result
 */
public class UserRepository {
    private final String result;

    /**
     * Constructor of the class, that reads all info from URL and puts it in the result string
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
        }
    }

    /**
     * Method finds all string occurrences in the result,
     * and returns them as List of content strings inside these strings.
     * Example in readme paragraph 5
     *
     * @param string string to be searched, Case insensitive, format WITHOUT "
     * @return List off all found stings, that has this string as a key, or empty
     */
    public List<String> find(String string) {
        String stringTransform = "\"" + string + "\": ";
        Matcher matcher = Pattern
                .compile(stringTransform, Pattern.CASE_INSENSITIVE)
                .matcher(result);
        List<String> list = new ArrayList<>();

        return null;
    }

    public String getResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRepository that = (UserRepository) o;

        return Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return result != null ? result.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "UserRepository{" +
                "result='" + result + '\'' +
                '}';
    }
}
