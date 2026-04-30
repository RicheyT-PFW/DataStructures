package mod9.lab9;

public interface Sorter<E extends Comparable<E>> {
    void sort();
    default int getComparisons(){
        return 0;
    };
    default int getSwaps(){
        return 0;
    };
    double getPerformance();
}
