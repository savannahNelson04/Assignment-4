import java.util.LinkedList;

public class HashSeparateChaining {
    private final LinkedList<Entry>[] table;
    private final int M;
    private int comparisons;

    public HashSeparateChaining(int M) {
        this.M = M;
        this.table = new LinkedList[M];
        for (int i = 0; i < M; i++) {
            table[i] = new LinkedList<>();
        }
        this.comparisons = 0;
    }

    public void put(String key, int value) {
        int hash = (hashCode1(key) & 0x7FFFFFFF) % M;
        table[hash].add(new Entry(key, value));
    }

    public boolean contains(String key) {
        int hash = (hashCode1(key) & 0x7FFFFFFF) % M;
        comparisons = 0; // Reset comparison count
        for (Entry entry : table[hash]) {
            comparisons++;
            if (entry.key.equals(key)) {
                return true;
            }
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

    private static class Entry {
        String key;
        int value;

        Entry(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}