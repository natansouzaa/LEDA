package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	@Override
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
			this.rebalance(node);
		}
	}

	@Override
	public void remove(T element){
		BSTNode<T> node = search(element);
		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				node.setData(null);
				this.rebalanceUp(node);
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
				this.rebalanceUp(node);
			} else {
				T nodeSucessor = sucessor(node.getData()).getData();
				remove(nodeSucessor);
				node.setData(nodeSucessor);
			}
		}
	}

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		int result = 0;
		if (!node.isEmpty()){
			result = this.heightAux((BSTNode<T>) node.getLeft(), -1) - this.heightAux((BSTNode<T>) node.getRight(), -1);
		}
		return result;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		if (calculateBalance(node) > 1){
			if (calculateBalance((BSTNode<T>) node.getLeft()) < 0){
				leftRotation((BSTNode<T>) node.getLeft());
			}
			rightRotation(node);

		} else if (calculateBalance(node) < -1){
			if (calculateBalance((BSTNode<T>) node.getRight()) > 0){
				rightRotation((BSTNode<T>) node.getRight());
			}
			leftRotation(node);
		}
	}

	private void leftRotation(BSTNode<T> node){
		if (node.equals(this.root)){
			this.root = Util.leftRotation(node);
		} else {
			BSTNode<T> auxNode = Util.leftRotation(node);
			if (auxNode.getData().compareTo(auxNode.getParent().getData()) < 0){
				auxNode.getParent().setLeft(auxNode);
			} else {
				auxNode.getParent().setRight(auxNode);
			}
		}
	}

	private void rightRotation(BSTNode<T> node){
		if (node.equals(this.root)){
			this.root = Util.rightRotation(node);
		} else {
			BSTNode<T> auxNode = Util.rightRotation(node);
			if (auxNode.getData().compareTo(auxNode.getParent().getData()) > 0){
				auxNode.getParent().setRight(auxNode);
			} else {
				auxNode.getParent().setLeft(auxNode);
			}
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		while (parent != null){
			this.rebalance(parent);
			parent = (BSTNode<T>) parent.getParent();
		}
	}
}
