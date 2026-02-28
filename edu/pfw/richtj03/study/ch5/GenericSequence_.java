package edu.pfw.richtj03.study.ch5;

public class GenericSequence_ <T extends Comparable<T>> {

private T[] data;
private int itemCount = 0;
private final int DEFAULT_CAPACITY = 10;

    public GenericSequence_() {
        this.data = (T[]) new Comparable<?>[DEFAULT_CAPACITY];
    }


    public GenericSequence_(int capacity) {
        this.data = (T[]) new Comparable<?>[capacity];
    }

    public GenericSequence_ (T[] data) {
        this.data = data.clone();
        for(int i = 0; i < this.data.length && this.data[i] != null; i++, this.itemCount++);
     /* int i = 0;
        while(i < this.data.length && this.data[i] != null) {
            this.itemCount++;
            i++;
        }*/
    }    
}