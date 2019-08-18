package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {
		head = new DoubleLinkedListNode<T>();
		last = (DoubleLinkedListNode<T>) head;
	}

	@Override
	public void insertFirst(T element) {
		DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<T>(element, (DoubleLinkedListNode<T>) this.head, new DoubleLinkedListNode<T>());
		((DoubleLinkedListNode<T>) this.head).setPrevious(newHead);
		if (this.head.isNIL()){
			this.last = newHead;
		}
		this.head = newHead;
	}

	@Override
	public void insert(Object element) {
		if (element != null) {
			if (isEmpty()) {
				DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode(element, (DoubleLinkedListNode<T>) this.head, (DoubleLinkedListNode<T>) this.head);
				this.head = newHead;
			} else {
				DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) this.head;
				while (!aux.getNext().isNIL()) {
					aux = (DoubleLinkedListNode<T>) aux.getNext();
				}
				DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode(element, new DoubleLinkedListNode<T>(), aux);
				aux.setNext(newNode);
				setLast(newNode);
			}
		}
	}

	@Override
	public void removeFirst() {
		if (!this.head.isNIL()){
			this.head = this.head.getNext();
			if (head.isNIL()){
				setLast((DoubleLinkedListNode<T>) this.head);
			}
			((DoubleLinkedListNode<T>) this.head).setPrevious(new DoubleLinkedListNode<T>());
		}
	}

	@Override
	public void removeLast() {
		if (!this.last.isNIL()){
			this.last = this.last.getPrevious();
			if (this.last.isNIL()){
				this.head = this.last;
			}
			this.last.next = new DoubleLinkedListNode<T>();
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}