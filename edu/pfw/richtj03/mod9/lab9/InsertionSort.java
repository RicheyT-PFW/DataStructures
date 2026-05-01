package mod9.lab9;

public class InsertionSort<T extends Comparable<T>> implements Sorter<T>{
    private final T[] arr;
    private int comparisons = 0;
    private int swaps = 0;
    private String characteristic;

    public InsertionSort(String ch, T[] arr) {
        this.characteristic = ch;
        this.arr = arr;
        comparisons = 0;
        swaps = 0;
    }

    public void sort() {
        comparisons = 0;
        swaps = 0;

        for (int i = 1; i < arr.length; i++) {
            T key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j].compareTo(key) > 0) {
		comparisons++;
		swaps++;
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    @Override
    public String toString() {
        return "Insertion Sort - " + characteristic;
    }
    public int getComparisons() { return comparisons; }
    public int getSwaps() { return swaps; }
    @Override
    public double getPerformance() {
        return (double) (swaps + comparisons) / arr.length;
    }
}
