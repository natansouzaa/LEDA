package adt.linkedList;

public class SingleLinkedListNode<T> {
	protected T data;
	protected SingleLinkedListNode<T> next;

	/**
	 * Constructor of an empty (NIL) node
	 */
	public SingleLinkedListNode() {
	}

	public SingleLinkedListNode(T data, SingleLinkedListNode<T> next) {
		this.data = data;
		this.next = next;
	}

	public boolean isNIL() {
		return (this.data == null);
	}

	public T getData() {
		return data;
	}

	public SingleLinkedListNode<T> getNext() {
		return next;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setNext(SingleLinkedListNode<T> next) {
		this.next = next;
	}

}
