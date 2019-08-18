package adt.linkedList.set;

import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

public class SetLinkedListImpl<T> extends SingleLinkedListImpl<T> implements SetLinkedList<T> {

	@Override
	public void removeDuplicates() {
		if (!isEmpty()){
			SingleLinkedListNode<T> auxHead = this.getHead();
			SingleLinkedListImpl<T> newNode = new SingleLinkedListImpl();
			newNode.insert(auxHead.getData());
			while (!auxHead.isNIL()){
				if (newNode.search(auxHead.getData()) == null ){
					newNode.insert(auxHead.getData());
				}
				auxHead = auxHead.getNext();
			}
			this.head = newNode.getHead();
		}
	}

	@Override
	public SetLinkedList<T> union(SetLinkedList<T> otherSet) {
		if (otherSet.isEmpty()){
			return this;
		}
		if (!isEmpty()){
			SingleLinkedListNode<T> auxHead = this.head;
			while (!auxHead.isNIL()){
				if (otherSet.search((T) auxHead.getData()) == null ){
					otherSet.insert((T) auxHead.getData());
				}
				auxHead = auxHead.getNext();
			}
		}
		return otherSet;
	}

	@Override
	public SetLinkedList<T> intersection(SetLinkedList<T> otherSet) {
		SetLinkedList<T> newSet = new SetLinkedListImpl<>();
		if (!otherSet.isEmpty()){
			if (!isEmpty()){
				SingleLinkedListNode<T> auxHead = this.head;
				while (!auxHead.isNIL()){
					if (otherSet.search((T) auxHead.getData()) != null ){
						newSet.insert((T) auxHead.getData());
					}
					auxHead = auxHead.getNext();
				}
			}
		}
		return newSet;
	}

	@Override
	public void concatenate(SetLinkedList<T> otherSet) {
		if (!otherSet.isEmpty()){
			if (!isEmpty()){
				SingleLinkedListNode<T> auxHead = this.head;
				while (!auxHead.getNext().isNIL()){
					auxHead = auxHead.getNext();
				}
				SingleLinkedListImpl<T> aux = (SingleLinkedListImpl<T>) otherSet;
				auxHead.setNext(aux.getHead());
			}
		}
	}

}
