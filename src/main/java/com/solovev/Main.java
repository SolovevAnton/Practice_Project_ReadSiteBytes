package com.solovev;

import com.solovev.repository.UserRepository;

import java.io.IOException;
import java.util.prefs.InvalidPreferencesFormatException;

public class Main {
    public static void main(String[] args) {
        String correctURL = "https://jsonplaceholder.typicode.com/users";
        String inCorrectUrl = "typicode.co";
        String[] testDataCollect = {
                "",
                "Nothing can be found",
                "id",
                "name",
                "geo",
                "address",
                "lng"
        };
        //TODO #6 from the task + tests for it
        try {
            UserRepository userRepository = new UserRepository(correctURL);
//            UserRepository errorRepository = new UserRepository(inCorrectUrl); //this line will throw exception
            System.out.println(userRepository);
            for(String s : testDataCollect){
            System.out.println("\n Result for: " + s);
            System.out.println(userRepository.find(s));
            }

        } catch (InvalidPreferencesFormatException |
                 IOException e) { // why this is better than to throw out of the method?
            e.printStackTrace();
        }
        //tests for StringParser:
//        Map<Character,Character> map = new HashMap<>();
//        map.put('{','}');
//        map.put('[',']');
//        StringParser sp = new StringParser(map,',');
//        List<String> forTest = List.of(
//                "",
//                "10,",
//                "\"024-648-3804\", \nUNUSED" ,
//                "[1,2,3]",
//                "{ \"field1\" : \"this\" , \"field2\" : \"that\" } , \nUNUSED",
//                "{\"street\": \"Kattie Turnpike\",\"suite\": \"Suite 198\",\"city\": \"Lebsackbury\",\"zipcode\": \"31428-2261\",\"geo\": {\"lat\": \"-38.2386\",\"lng\": \"57.2232\"}},\nUNUSED",
//                "{ \"name\": \"Hoeger LLC\", \"catchPhrase\": \"Centralized empowering task-force\", \"bs\": \"target end-to-end models\" }, \nUNUSED",
//                "{\"lat\": \"-38.2386\",\"lng\": \"57.2232\" }, \nUNUSED",
//                "\"57.2232\" } \nUNUSED"
////                ,"{\"lat\": \"-38.2386\"] \nUNUSED" //this line is done for exception checking
//        );
//        for(String s : forTest){
//           try {
//               System.out.println(sp.parse(s.toCharArray(), 0));
//           } catch (InvalidPreferencesFormatException e) {
//               throw new RuntimeException(e);
//           }
//        }

    }
}