package mod9.lab9;

import java.util.ArrayList;

public class SortingRace {
    public static void main(String[] args) {
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
        ArrayList<Sorter> sorters = new ArrayList<>();
	    		
        //TODO Creating sorting class instances, run sorts, and report metrics
		for(int i = 0; i < 4; i++) {
			sorters.add(new InsertionSort<Integer>(characteristics[i], numLists[i]));
			sorters.add(new MergeSort<Integer>(characteristics[i], numLists[i]));
			sorters.add(new QuickSort<Integer>(characteristics[i], numLists[i]));
			sorters.add(new HeapSort<Integer>(characteristics[i], numLists[i]));
		}

		for(Sorter sorter : sorters) {
			sorter.sort();
		}
	
		for(Sorter sorter : sorters) {
			System.out.println(sorter);
			System.out.println("Comparisons: " + sorter.getComparisons());
			System.out.println("Swaps: " + sorter.getSwaps());
			System.out.print(sorter.getClass().getSimpleName() + 
							" Performance: "+ sorter.getPerformance());
			System.out.println("\n\n---");
		}
		
    }
}
