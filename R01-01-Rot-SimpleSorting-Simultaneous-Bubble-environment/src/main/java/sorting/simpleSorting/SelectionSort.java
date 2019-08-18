package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * As the insertion sort algorithm iterates over the array, it makes the
 * assumption that the visited positions are already sorted in ascending order,
 * which means it only needs to find the right position for the current element
 * and insert it there.
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	Util util = new Util();

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		for (int i = leftIndex; i < rightIndex; i++){
			int min = i;
			for (int j = i+1; j <= rightIndex; j++){
				if (array[min].compareTo(array[j]) > 0){
					min = j;
				}
			}
			this.util.swap(array, min, i);
		}
	}
}
