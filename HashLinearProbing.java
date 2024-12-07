public class HashLinearProbing {
    private final String[] keys;
    private final int[] values;
    private final int M;
    private int comparisons;

    public HashLinearProbing(int M) {
        this.M = M;
        this.keys = new String[M];
        this.values = new int[M];
        this.comparisons = 0;
    }

    public void put(String key, int value) {
        int hash = (hashCode1(key) & 0x7FFFFFFF) % M;
        while (keys[hash] != null) {
            hash = (hash + 1) % M;
        }
        keys[hash] = key;
        values[hash] = value;
    }

    public boolean contains(String key) {
        int hash = (hashCode1(key) & 0x7FFFFFFF) % M;
        comparisons = 0; // Reset comparison count
        while (keys[hash] != null) {
            comparisons++;
            if (keys[hash].equals(key)) {
                return true;
            }
            hash = (hash + 1) % M;
        }
        return false;
    }

    // Get the number of comparisons during the search
    public int getComparisons(String key) {
        contains(key); // This will calculate comparisons
        return comparisons;
    }

    private int hashCode1(String key) {
        int hash = 0;
        int skip = Math.max(1, key.length() / 8);
        for (int i = 0; i < key.length(); i += skip) {
            hash = (hash * 37) + key.charAt(i);
        }
        return hash;
    }
}
