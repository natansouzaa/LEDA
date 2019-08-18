package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * As the insertion sort algorithm iterates over the array, it makes the
 * assumption that the visited positions are already sorted in ascending order,
 * which means it only needs to find the right position for the current element
 * and insert it there.
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	Util util = new Util();

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		T chave;
		int j;
		for (int i = (leftIndex + 1); i <= rightIndex; i++) {
			chave = array[i];
			j = i - 1;
			while (j >= 0 && array[j].compareTo(chave) > 0){
				util.swap(array, j+1, j);
				j = j -1;
			}
		}
	}
}
