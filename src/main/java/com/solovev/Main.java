package com.solovev;

import com.solovev.repository.UserRepository;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String correctURL = "https://jsonplaceholder.typicode.com/users";
        String inCorrectUrl = "typicode.co";
        try {
            UserRepository userRepository = new UserRepository(correctURL);
//            UserRepository errorRepository = new UserRepository(inCorrectUrl);
            System.out.println(userRepository);

        } catch (IOException e) { // why this is better than to throw out of the method?
            e.printStackTrace();
        }

    }
}