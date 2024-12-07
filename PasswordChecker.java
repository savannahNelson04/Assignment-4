public class PasswordChecker {
    private final HashSeparateChaining separateChaining;
    private final HashLinearProbing linearProbing;

    public PasswordChecker(HashSeparateChaining separateChaining, HashLinearProbing linearProbing) {
        this.separateChaining = separateChaining;
        this.linearProbing = linearProbing;
    }

    public boolean isStrongPassword(String password) {
        // Check if password is at least 8 characters long
        if (password.length() < 8) {
            return false;
        }

        // Check if password is a word in the dictionary or a word followed by a digit
        if (separateChaining.contains(password)) {
            return false;
        }
        if (linearProbing.contains(password)) {
            return false;
        }

        // Check if password contains a dictionary word followed by a digit
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                String word = password.substring(0, i);
                if (separateChaining.contains(word) || linearProbing.contains(word)) {
                    return false;
                }
            }
        }

        return true;
    }

    // Get the number of comparisons for separate chaining
    public int getSearchComparisonsForSeparateChaining(String password) {
        return separateChaining.getComparisons(password);
    }

    // Get the number of comparisons for linear probing
    public int getSearchComparisonsForLinearProbing(String password) {
        return linearProbing.getComparisons(password);
    }
}