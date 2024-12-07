import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // Specify the dictionary file path
        String filePath = "Dictionary.csv";

        // Load the dictionary from the file
        List<String> dictionary = loadDictionary(filePath);

        // Initialize hash tables for separate chaining and linear probing
        HashSeparateChaining separateChaining = new HashSeparateChaining(1000);
        HashLinearProbing linearProbing = new HashLinearProbing(20000);

        // Insert words into hash tables
        int lineNumber = 1;
        for (String word : dictionary) {
            separateChaining.put(word, lineNumber);
            linearProbing.put(word, lineNumber);
            lineNumber++;
        }

        // Test passwords
        String[] testPasswords = {
                "account8",
                "accountability",
                "9a$D#qW7!uX&Lv3zT",
                "B@k45*W!c$Y7#zR9P",
                "X$8vQ!mW#3Dz&Yr4K5"
        };

        PasswordChecker checker = new PasswordChecker(separateChaining, linearProbing);

        for (String password : testPasswords) {
            System.out.println("Password: " + password);

            // Check if the password is strong
            boolean isStrong = checker.isStrongPassword(password);
            System.out.println("Is strong: " + isStrong);

            // Display the cost of the search for both hashCode() functions
            System.out.println("Separate Chaining Cost (comparisons): " + checker.getSearchComparisonsForSeparateChaining(password));
            System.out.println("Linear Probing Cost (comparisons): " + checker.getSearchComparisonsForLinearProbing(password));

            System.out.println(); // Blank line for separation
        }
    }

    private static List<String> loadDictionary(String filePath) throws IOException {
        List<String> words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line.trim());
            }
        }
        return words;
    }
}
