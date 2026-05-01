package mod9.lab9;

public class MergeSort<T extends Comparable<T>> implements Sorter<T>{
    private final T[] arr;
    private int comparisons = 0;
    private String characteristic;

    public MergeSort(String ch, T[] arr) {
        this.characteristic = ch;
        this.arr = arr;
        comparisons = 0;
    }

    public void sort() {
        comparisons = 0;
        mergeSort(arr, 0, arr.length - 1);
    }

    private void mergeSort(T[] arr, int left, int right) {
        if (left < right) {
	    comparisons++;
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private void merge(T[] arr, int left, int mid, int right) {
        T[] leftArr = java.util.Arrays.copyOfRange(arr, left, mid + 1);
        T[] rightArr = java.util.Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i = 0, j = 0, k = left;

        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i].compareTo(rightArr[j]) <= 0) {
                arr[k++] = leftArr[i++];
		comparisons++;
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        while (i < leftArr.length) {
            arr[k++] = leftArr[i++];
        }
        while (j < rightArr.length) {
            arr[k++] = rightArr[j++];
        }
    }

    @Override
    public String toString() {
        return "Merge Sort - " + characteristic;
    }
    public int getComparisons() { return comparisons; }
    @Override
    public double getPerformance() {
        return (double)  comparisons/arr.length;

    }
}
