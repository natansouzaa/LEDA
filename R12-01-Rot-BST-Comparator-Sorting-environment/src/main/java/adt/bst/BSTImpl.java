package adt.bst;

import java.util.ArrayList;
import java.util.List;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		this.root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return heightAux(this.root, -1);
	}
	private int heightAux(BSTNode<T> node, int alturaAtual) {
		if (!node.isEmpty()) {
			alturaAtual = Math.max(heightAux((BSTNode<T>) node.getLeft(), alturaAtual + 1), heightAux((BSTNode<T>) node.getRight(), alturaAtual + 1));
		}
		return alturaAtual;
	}

	@Override
	public BSTNode<T> search(T element) {
		if (!isEmpty()) {
			return searchAux(this.root, element);
		} else {
			return new BSTNode.Builder().build();
		}
	}

	protected BSTNode<T> searchAux(BSTNode<T> node, T element) {
		BSTNode<T> result;
		if (node.isEmpty()) {
			result = new BSTNode.Builder().build();
		} else if (element.compareTo(node.getData()) == 0) {
			result = node;
		} else if (element.compareTo(node.getData()) < 0) {
			result = searchAux((BSTNode<T>) node.getLeft(), element);
		} else {
			result = searchAux((BSTNode<T>) node.getRight(), element);
		}
		return result;
	}

	@Override
	public void insert(T element) {
		insertAux(this.root, element);
	}

	protected void insertAux(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode.Builder().parent(node).build());
			node.setRight(new BSTNode.Builder().parent(node).build());
		} else {
			if (element.compareTo(node.getData()) < 0) {
				insertAux((BSTNode<T>) node.getLeft(), element);
			} else if (element.compareTo(node.getData()) > 0) {
				insertAux((BSTNode<T>) node.getRight(), element);
			}
		}
	}

	@Override
	public BSTNode<T> maximum() {
		if (!this.root.isEmpty()) {
			return maximumAux(this.root);
		} else {
			return null;
		}
	}

	protected BSTNode<T> maximumAux(BSTNode<T> node) {
		if (!node.getRight().isEmpty()) {
			return maximumAux((BSTNode<T>) node.getRight());
		} else {
			return node;
		}
	}

	@Override
	public BSTNode<T> minimum() {
		if (!this.root.isEmpty()) {
			return minimumAux(this.root);
		} else {
			return null;
		}
	}

	protected BSTNode<T> minimumAux(BSTNode<T> node) {
		if (!node.getLeft().isEmpty()) {
			return minimumAux((BSTNode<T>) node.getLeft());
		} else {
			return node;
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
				while (result != null && result.getData().compareTo(node.getData()) < 0) {
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
				while (result != null && result.getData().compareTo(node.getData()) > 0) {
					node = result;
					result = (BSTNode<T>) node.getParent();
				}
				return result;
			}
		}
		return null;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				node.setData(null);
			} else if (hasOneChild(node)) {
				if (node.getParent() != null) {
					if (!node.getParent().getLeft().equals(node)) {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setRight(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} else {
							node.getParent().setRight(node.getRight());
							node.getRight().setParent(node.getParent());
						}
					} else {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setLeft(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} else {
							node.getParent().setLeft(node.getRight());
							node.getRight().setParent(node.getParent());
						}
					}
				} else {
					if (node.getLeft().isEmpty()) {
						this.root = (BSTNode<T>) node.getRight();
					} else {
						this.root = (BSTNode<T>) node.getLeft();
					}
					this.root.setParent(null);
				}
			} else {
				T nodeSucessor = sucessor(node.getData()).getData();
				remove(nodeSucessor);
				node.setData(nodeSucessor);
			}
		}
	}

	private boolean hasOneChild(BSTNode<T> node) {
		return (node.getRight().isEmpty() && !node.getLeft().isEmpty() || !node.getRight().isEmpty() && node.getLeft().isEmpty());
	}

	@Override
	public T[] preOrder() {
		T[] resultArray = (T[]) new Comparable[this.size()];
		List<T> auxList = new ArrayList<T>();
		if (!this.isEmpty()) {
			preOrderAux(this.root, auxList);
			auxList.toArray(resultArray);
		}
		return resultArray;
	}

	private void preOrderAux(BSTNode<T> node, List<T> array) {
		if (!node.isEmpty()) {
			array.add(node.getData());
			preOrderAux((BSTNode<T>) node.getLeft(), array);
			preOrderAux((BSTNode<T>) node.getRight(), array);
		}
	}

	@Override
	public T[] order() {
		T[] resultArray = (T[]) new Comparable[this.size()];
		List<T> auxList = new ArrayList<T>();
		if (!this.isEmpty()) {
			OrderAux(this.root, auxList);
			auxList.toArray(resultArray);
		}
		return resultArray;
	}

	private void OrderAux(BSTNode<T> node, List<T> array) {
		if (!node.isEmpty()) {
			OrderAux((BSTNode<T>) node.getLeft(), array);
			array.add(node.getData());
			OrderAux((BSTNode<T>) node.getRight(), array);
		}
	}

	@Override
	public T[] postOrder() {
		T[] resultArray = (T[]) new Comparable[this.size()];
		List<T> auxList = new ArrayList<T>();
		if (!this.isEmpty()) {
			postOrderAux(this.root, auxList);
			auxList.toArray(resultArray);
		}
		return resultArray;
	}

	private void postOrderAux(BSTNode<T> node, List<T> array) {
		if (!node.isEmpty()) {
			postOrderAux((BSTNode<T>) node.getLeft(), array);
			postOrderAux((BSTNode<T>) node.getRight(), array);
			array.add(node.getData());
		}
	}

	/**
	 * This method is already implemented using recursion. You must understand how
	 * it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(this.root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
		}
		return result;
	}
}