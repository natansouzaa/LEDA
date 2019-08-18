package sorting.divideAndConquer.threeWayQuicksort;

import sorting.AbstractSorting;
import util.Util;

public class ThreeWayQuickSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	Util util = new Util();

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(leftIndex>= rightIndex){
			return;
		}
		int lt = leftIndex;
		int gt = rightIndex;
		int m = particao(array, leftIndex,rightIndex);
		sort(array, leftIndex, m-1);
		sort(array, m+1, rightIndex);
	}

	public int particao(T[] array, int leftIndex, int rightIndex){
		T pivot = array[leftIndex];
		int i = leftIndex;
		for (int j = leftIndex+1; j <= rightIndex; j++){
			if (array[j].compareTo(pivot) < 0){
				i = i + 1;
				util.swap(array, i, j);
			}
		}
		util.swap(array, leftIndex, i);
		return i;
	}

}
