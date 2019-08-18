package adt.bst.extended;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em suas funcionalidades
 * e possui um metodo de ordenar um array dado como parametro, retornando o resultado do percurso
 * desejado que produz o array ordenado. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;
	
	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	@Override
	protected BSTNode<T> searchAux(BSTNode<T> node, T element) {
		BSTNode<T> result;
		if (node.isEmpty()) {
			result = new BSTNode.Builder().build();
		} else if (this.comparator.compare(element, node.getData()) == 0) {
			result = node;
		} else if (this.comparator.compare(element, node.getData()) < 0) {
			result = searchAux((BSTNode<T>) node.getLeft(), element);
		} else {
			result = searchAux((BSTNode<T>) node.getRight(), element);
		}
		return result;
	}

	@Override
	protected void insertAux(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode.Builder().parent(node).build());
			node.setRight(new BSTNode.Builder().parent(node).build());
		} else {
			if (this.comparator.compare(element, node.getData()) < 0) {
				insertAux((BSTNode<T>) node.getLeft(), element);
			} else if (this.comparator.compare(element, node.getData()) > 0) {
				insertAux((BSTNode<T>) node.getRight(), element);
			}
		}
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = this.search(element);
		if (!node.isEmpty()) {
			if (!node.getRight().isEmpty())
				return this.minimumAux((BSTNode<T>) node.getRight());
			else {
				BSTNode<T> result = (BSTNode<T>) node.getParent();
				while (result != null && this.comparator.compare(result.getData(), node.getData()) < 0) {
					node = result;
					result = (BSTNode<T>) node.getParent();
				}
				return result;
			}
		}
		return null;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = this.search(element);
		if (!node.isEmpty()) {
			if (!node.getLeft().isEmpty())
				return this.maximumAux((BSTNode<T>) node.getLeft());
			else {
				BSTNode<T> result = (BSTNode<T>) node.getParent();
				while (result != null && this.comparator.compare(result.getData(), node.getData()) > 0) {
					node = result;
					result = (BSTNode<T>) node.getParent();
				}
				return result;
			}
		}
		return null;
	}

	@Override
	public T[] sort(T[] array) {
		while (!isEmpty()){
			remove(this.root.getData());
		}
		for (int i = 0; i < array.length ; i++){
			insert(array[i]);
		}
		return order();
	}

	@Override
	public T[] reverseOrder() {
		T[] resultArray = (T[]) new Comparable[this.size()];
		ArrayList<T> auxList = new ArrayList<T>();
		reverseOrderAux(this.root, auxList);
		auxList.toArray(resultArray);
		return resultArray;
	}

	private void reverseOrderAux(BSTNode<T> node, ArrayList<T> array) {
		if (!node.isEmpty()) {
			reverseOrderAux((BSTNode<T>) node.getRight(), array);
			array.add(node.getData());
			reverseOrderAux((BSTNode<T>) node.getLeft(), array);
		}
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
}
