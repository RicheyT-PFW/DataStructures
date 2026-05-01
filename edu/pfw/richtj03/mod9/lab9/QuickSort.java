package mod9.lab9;

public class QuickSort<T extends Comparable<T>> implements Sorter<T> {
    private final T[] arr;
    private int comparisons = 0;
    private int swaps = 0;
    private String characteristic;

    public QuickSort(String ch, T[] arr) {
        this.characteristic = ch;
        this.arr = arr;
        comparisons = 0;
        swaps = 0;
    }

    public void sort() {
        comparisons = 0;
        swaps = 0;
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(T[] arr, int low, int high) {
	comparisons++;    
        if (low < high) {
            int pivotIndex = medianOfThreePartition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private int medianOfThreePartition(T[] arr, int low, int high) {
        int mid = low + (high - low) / 2;

        // Find the median of arr[low], arr[mid], arr[high]
        if (arr[low].compareTo(arr[mid]) > 0) swap(arr, low, mid);
        if (arr[low].compareTo(arr[high]) > 0) swap(arr, low, high);
        if (arr[mid].compareTo(arr[high]) > 0) swap(arr, mid, high);

        // Use the median (arr[mid]) as the pivot
        swap(arr, mid, high);
	swaps++;

        T pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
	    comparisons++;
            if (arr[j].compareTo(pivot) <= 0) {
                i++;
		swaps++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
	swaps++;
        return i + 1;
    }

    private void swap(T[] arr, int i, int j) {
        if (i == j) return;
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    @Override
    public String toString() {
        return "Quick Sort - " + characteristic;
    }

    public int getComparisons() {
        return comparisons;
    }

    public int getSwaps() {
        return swaps;
    }

    @Override
    public double getPerformance() {
        return (double) (comparisons + swaps) / arr.length;
    }
}
