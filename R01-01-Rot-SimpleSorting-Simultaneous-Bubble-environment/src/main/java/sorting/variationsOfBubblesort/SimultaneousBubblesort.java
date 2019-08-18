package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * This algorithm applies two bubblesorts simultaneously. In a same iteration, a
 * bubblesort pushes the greatest elements to the right and another bubblesort
 * pushes the smallest elements to the left. At the end of the first iteration,
 * the smallest element is in the first position (index 0) and the greatest
 * element is the last position (index N-1). The next iteration does the same
 * from index 1 to index N-2. And so on. The execution continues until the array
 * is completely ordered.
 */
public class SimultaneousBubblesort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	Util util = new Util();

	public void sort(T[] array, int leftIndex, int rightIndex) {
		for (int i = leftIndex; i < rightIndex; i++){
			for (int j = leftIndex; j < rightIndex; j++){
				if (array[j].compareTo(array[j+1]) > 0){
					this.util.swap(array, j, j+1);
				}
			}
			for (int x = rightIndex; x > leftIndex; x--) {
				if (array[x].compareTo(array[x-1]) < 0){
					this.util.swap(array, x, x-1);
				}
			}
		}
	}
}
