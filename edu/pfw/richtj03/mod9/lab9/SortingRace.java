package mod9.lab9;

import java.util.ArrayList;

public class SortingRace {
    public static void main(String[] args) {
	    /*
		 * NEARLY SORTED (n=15):
		 * Insertion Sort (0.4) performed the best with only 3 comparisons and 3 swaps because the data is already
		 * nearly sorted. Insertion sort works very well when elements are close to their final positions. 
		 * The other sorters perform more work because they still divide and reorganize the data, even though it's almost sorted.
		 * MergeSort (2.13), QuickSort (6.8), and HeapSort (6.13) all have significantly higher performance scores.
		 */
 
		/*
		 * REVERSE ORDER (n=15):
		 * Insertion Sort performed the worst with 105 comparisons and 105 swaps. This is the worst case for 
         * insertion sort because each element must be compared against all previous elements and shifted to the front.
		 * MergeSort remains consistent with only 28 comparisons (1.87 performance), HeapSort (5.4), and QuickSort (11.0) vary.
		 * MergeSort and HeapSort's performance remains relatively stable regardless of input order due to their fixed
		 * divide-and-conquer and heapify structures. QuickSort's median-of-three pivot helps avoid its worst case.
		 */
 
		/*
		 * RANDOMIZED SMALL(n=1000):
		 * Insertion Sort performed the worst again with 221,188 comparisons and 442.38 performance. This is likely because 
		 * the 1000 random elements were not close to their sorted order, causing each element to shift down nearly "i" positions 
		 * if "i" is the current element's index. MergeSort (8,504 comparisons) and HeapSort (15,790 comparisons) are O(n log n) 
		 * and scale much better with random data because of the nature of divide and conquer. QuickSort (56,654 comparisons) 
		 * also is O(n log n) but has more overhead than MergeSort in this case, so it performed worse.
		 */
 
		/*
		 * RANDOMIZED LARGE (n=20000):
		 * Insertion Sort reaches 100,881,324 comparisons with a performance of 10088.13. This makes sense because insertion sort 
		 * must check every single member in the array at least once, and often has to check more than once. MergeSort requires 
		 * only 260,853 comparisons (13.04 performance) because the divide and conquer strategy allows us to skip over large portions 
		 * of the array that would necessarily be sorted. HeapSort (510,702 comparisons, 26.54 performance) and QuickSort (408,118 
		 * comparisons, 35.87 performance) also demonstrate O(n log n) behavior at scale.
		 */
        String[] characteristics = {"Nearly Sorted", "Reverse Order", "Randomized Small", "Randomized Large"};
        Integer[][] numLists = new Integer[4][];
        numLists[0] = new Integer[]{1, 2, 3, 5, 4, 6, 7, 8, 10, 9, 11, 12, 13, 15, 14};
        numLists[1] = new Integer[]{15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        numLists[2] = new Integer[1000];
        for (int i = 0; i < numLists[2].length; i++) {
            numLists[2][i] = (int) (Math.random() * 10);
        }
        numLists[3] = new Integer[20000];
        for (int i = 0; i < numLists[3].length; i++) {
            numLists[3][i] = (int) (Math.random() * 10000);
        }

        //Use polymorphism with the Sorter interface
        ArrayList<Sorter<Integer>> sorters = new ArrayList<>();
	    		
        //TODO Creating sorting class instances, run sorts, and report metrics
		for(int i = 0; i < 4; i++) {
			sorters.add(new InsertionSort<Integer>(characteristics[i], numLists[i].clone()));
			sorters.add(new MergeSort<Integer>(characteristics[i], numLists[i].clone()));
			sorters.add(new QuickSort<Integer>(characteristics[i], numLists[i].clone()));
			sorters.add(new HeapSort<Integer>(characteristics[i], numLists[i].clone()));
		}

		for(Sorter<Integer> sorter : sorters) {
			sorter.sort();
		}
	
		for(Sorter<Integer> sorter : sorters) {
			System.out.println(sorter);
			System.out.println("Comparisons: " + sorter.getComparisons());
			System.out.println("Swaps: " + sorter.getSwaps());
			System.out.print(sorter.getClass().getSimpleName() + " Performance: " + sorter.getPerformance());
			System.out.println("\n\n---");
		}
/*
Insertion Sort - Nearly Sorted
Comparisons: 3
Swaps: 3
InsertionSort Performance: 0.4

---
Merge Sort - Nearly Sorted
Comparisons: 32
Swaps: 0
MergeSort Performance: 2.1333333333333333

---
Quick Sort - Nearly Sorted
Comparisons: 63
Swaps: 39
QuickSort Performance: 6.8

---
Heap Sort - Nearly Sorted
Comparisons: 77
Swaps: 15
HeapSort Performance: 6.133333333333334

---
Insertion Sort - Reverse Order
Comparisons: 105
Swaps: 105
InsertionSort Performance: 14.0

---
Merge Sort - Reverse Order
Comparisons: 28
Swaps: 0
MergeSort Performance: 1.8666666666666667

---
Quick Sort - Reverse Order
Comparisons: 86
Swaps: 79
QuickSort Performance: 11.0

---
Heap Sort - Reverse Order
Comparisons: 66
Swaps: 15
HeapSort Performance: 5.4

---
Insertion Sort - Randomized Small
Comparisons: 221188
Swaps: 221188
InsertionSort Performance: 442.376

---
Merge Sort - Randomized Small
Comparisons: 8504
Swaps: 0
MergeSort Performance: 8.504

---
Quick Sort - Randomized Small
Comparisons: 56654
Swaps: 54293
QuickSort Performance: 110.947

---
Heap Sort - Randomized Small
Comparisons: 15790
Swaps: 1000
HeapSort Performance: 16.79

---
Insertion Sort - Randomized Large
Comparisons: 100881324
Swaps: 100881324
InsertionSort Performance: 10088.1324

---
Merge Sort - Randomized Large
Comparisons: 260853
Swaps: 0
MergeSort Performance: 13.04265

---
Quick Sort - Randomized Large
Comparisons: 408118
Swaps: 309298
QuickSort Performance: 35.8708

---
Heap Sort - Randomized Large
Comparisons: 510702
Swaps: 20000
HeapSort Performance: 26.5351

---

 * 
 * 
 */   
        

    }
}
