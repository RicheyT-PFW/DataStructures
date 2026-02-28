package edu.pfw.richtj03.mod3;
import java.lang.reflect.Array;

public class Table<K, V> {
    private int manyItems;
    private K[] keys;
    private V[] data;
    private boolean[] hasBeenUsed;
    private float loadFactor;
    private int collisionCount;


    public Table(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("Capacity is negative");
	    //Ensure capacity is a power of 2 above the given capacity
            if (Integer.bitCount(capacity) != 1) {
            int newCapacity = Integer.highestOneBit(capacity) << 1;
            capacity = newCapacity;
        }
        keys = (K[]) Array.newInstance(Object.class, capacity);
        data = (V[]) Array.newInstance(Object.class, capacity);
        hasBeenUsed = new boolean[capacity];
    }

   private void setCollisionCount() {
	
   }

   private void setLoadFactor() {
	return 
   }

   public int collisionCount() {
	return collisionCount;
   }

    public float getLoadFactor() {
	return loadFactor;
    }
    public int findIndex(K key) {
        int count = 0;
        int i = hash(key);
        while (count < data.length && hasBeenUsed[i]) {
            if (key.equals(keys[i]))
                return i;
            count++;
            i = nextIndex(i);
        }
        return -1;
    }

    /**
     * A hash function used in Java's HashMap
     * Expects the table size to be a power of 2
     */
    private int hash(K key) {
        int h = 0;
        //Performs a bitwise exclusive OR of the two halves of the 32-bit int from hashCode().
        if (key != null) {
            h = key.hashCode();
            h = (h >>> 16) % data.length; // Shift 32-bit to the right by 16 bits and compress to the size of the table
        }
        return h;
    }
    private int nextIndex(int i) {
        if (i + 1 == data.length)
            return 0;
        else
            return i + 1;
    }

    /**
     * @return Previous value for this key, or null if the key is new
     */
    public V put(K key, V element) {
        int index = findIndex(key);
        V answer;
        if (index != -1) { // The key is already in the table.
            answer = data[index];
            data[index] = element;
            return answer;
        } else if (manyItems < data.length) { // The key is not yet in this Table.
            index = hash(key);
            while (keys[index] != null)
                index = nextIndex(index);
            keys[index] = key;
            data[index] = element;
            hasBeenUsed[index] = true;
            manyItems++;
            return null;
        } else // The table is full.
            throw new IllegalStateException("Table is full.");
    }
    public V get(K key) {
        int index = findIndex(key);
        if (index == -1)
            return null;
        else
            return data[index];
    }
    public int size() {
        return manyItems;
    }

    // Determines if a key is in the table.
    public boolean containsKey(K key) {
        if(key == null) {
            return false;
        }

        int i = findIndex(key);

        if (i == -1) {
            return false;
        }

        return keys[i] != null;
    }

    // Removes an element from the table and returns
     public V remove(K key) {


        if (!containsKey(key)) {
            return null;
        }

        int i = findIndex(key);

        V t = data[i];

        keys[i] = null;
        data[i] = null;
	manyItems--;
        return t;
     }


    public static void main(String[] args) {
	final int capacity = 1024;
        Table<String, String> tbl = new Table<>(capacity);
    }
}
