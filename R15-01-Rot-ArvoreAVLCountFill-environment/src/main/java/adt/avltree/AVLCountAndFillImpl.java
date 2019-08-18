package adt.avltree;

import adt.bst.BSTNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends
		AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {
		
	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	@Override
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);
		boolean validation = false;

		if (balance < -1) {
			if (this.calculateBalance((BSTNode<T>) node.getRight()) > 0){
				this.rightRotation((BSTNode<T>) node.getRight());
				validation = true;
			}
			this.leftRotation(node);
			if (validation){
				this.RLcounter++;
			} else {
				this.RRcounter++;
			}

		} else if (balance > 1) {
			if (this.calculateBalance((BSTNode<T>) node.getLeft()) < 0){
				this.leftRotation((BSTNode<T>) node.getLeft());
				validation = true;
			}
			this.rightRotation(node);
			if (validation){
				this.LRcounter++;
			} else {
				this.LLcounter++;
			}
		}
	}

	@Override
	public void fillWithoutRebalance(T[] array) {
		ArrayList<T[]> completeList = new ArrayList<>();
		Arrays.sort(array);
		completeList.add(array);
		int counter = 0;
		while (counter < completeList.size()){
			T[] arrayAux = completeList.get(counter);
			int middle = arrayAux.length/2;
			T[] arrayHalf1 = Arrays.copyOfRange(arrayAux, 0, middle);
			T[] arrayHalf2 = Arrays.copyOfRange(arrayAux, middle + 1, arrayAux.length);
			if (arrayAux.length > 1){
				completeList.add(arrayHalf1);
				completeList.add(arrayHalf2);
			}
			this.insert(arrayAux[middle]);
			counter++;
		}
	}

}
