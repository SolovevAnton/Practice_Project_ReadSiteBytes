package com.solovev.repository;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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
     * @throws IOException if stream is failed or interrupted
     */
    public UserRepository(String urlIn) throws MalformedURLException, IOException {
        URL url = new URL(urlIn);
        try (BufferedInputStream inStream = new BufferedInputStream(url.openStream())) {
            byte[] bytes = inStream.readAllBytes();
            result = new String(bytes);
        }
    }
}
