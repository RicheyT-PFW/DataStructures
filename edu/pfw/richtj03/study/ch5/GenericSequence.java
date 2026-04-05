package ch5;

public class GenericSequence <T extends Comparable<T>> {

private T[] data;
private int itemCount = 0;
private final int DEFAULT_CAPACITY = 10;

    public GenericSequence() {
        this.data = (T[]) new Comparable<?>[DEFAULT_CAPACITY];
    }


    public GenericSequence(int capacity) {
        this.data = (T[]) new Comparable<?>[capacity];
    }

    public GenericSequence(T[] data) {
        this.data = data.clone();
        for(int i = 0; i < this.data.length && this.data[i] != null; i++, this.itemCount++);
    }    
}
