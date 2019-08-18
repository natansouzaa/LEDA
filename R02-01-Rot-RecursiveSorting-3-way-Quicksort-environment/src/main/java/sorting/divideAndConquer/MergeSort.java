package sorting.divideAndConquer;

import sorting.AbstractSorting;

import java.util.Arrays;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex){
			int metade = (leftIndex + rightIndex)/2;
			sort(array, leftIndex, metade);
			sort(array, (metade + 1), rightIndex);
			merge(array, leftIndex, metade,  rightIndex);
		}
	}

	private void merge(T[] array, int leftIndex, int metade, int rightIndex) {
		T[] arrayAuxiliar = Arrays.copyOf(array, array.length);
        for (int x = leftIndex; x <= rightIndex; x++){
            arrayAuxiliar[x] = array[x];
        }
		int i = leftIndex;
		int j = metade + 1;
		for (int k = leftIndex; k <= rightIndex; k++){
			if (i > metade){
				array[k] = arrayAuxiliar[j++];
			} else if (j > rightIndex){
				array[k] = arrayAuxiliar[i++];
			} else if (arrayAuxiliar[i].compareTo(arrayAuxiliar[j]) < 0){
				array[k] = arrayAuxiliar[i++];
			} else {
				array[k] = arrayAuxiliar[j++];
			}
		}

	}
}
