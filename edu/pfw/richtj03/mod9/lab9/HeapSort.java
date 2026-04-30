package mod9.lab9;

public class HeapSort<T extends Comparable<T>> implements Sorter<T>{
    private final T[] arr;
    private int comparisons = 0;
    private int swaps = 0;
    private String characteristic;

    public HeapSort(String ch, T[] arr) {
        this.characteristic = ch;
        this.arr = arr;
        comparisons = 0;
        swaps = 0;
    }

    public void sort() {
        comparisons = 0;
        swaps = 0;

        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            swap(arr, 0, i);
            swaps++;
            heapify(arr, i, 0);
        }
    }

    private void heapify(T[] arr, int heapSize, int root) {
        int largest = root;
        int left = 2 * root + 1;
        int right = 2 * root + 2;

        if (left < heapSize && arr[left].compareTo(arr[largest]) > 0) {
            largest = left;
        }

        if (right < heapSize && arr[right].compareTo(arr[largest]) > 0) {
            largest = right;
        }

        if (largest != root) {
            swap(arr, root, largest);
            heapify(arr, heapSize, largest);
        }
    }

    private void swap(T[] arr, int i, int j) {
        if (i == j) return;
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    @Override
    public String toString() {
        return "Heap Sort - " + characteristic;
    }
    public int getComparisons() { return comparisons; }
    public int getSwaps() { return swaps; }
    @Override
    public double getPerformance() {
        return -1;
    }
}
