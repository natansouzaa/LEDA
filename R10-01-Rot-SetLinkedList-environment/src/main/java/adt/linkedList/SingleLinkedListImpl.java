package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return (this.head.isNIL());
	}

	@Override
	public int size() {
		int result = 0;
		if (!isEmpty()){
			SingleLinkedListNode<T> auxHead = this.head;
			while (!auxHead.isNIL()){
				result++;
				auxHead = auxHead.next;
			}
		}
		return result;
	}

	@Override
	public T search(T element) {
		T result = null;
		if (!isEmpty()){
			if (element != null){
				SingleLinkedListNode<T> auxHead = this.head;
				while (!auxHead.isNIL()){
					if (auxHead.data.equals(element)){
						result = auxHead.data;
					}
					auxHead = auxHead.next;
				}
			}
		}
		return result;
	}

	@Override
	public void insert(T element) {
		if (element != null){
			if (isEmpty()){
				SingleLinkedListNode newHead = new SingleLinkedListNode(element, this.head);
				this.head = newHead;
			} else {
				SingleLinkedListNode auxHead = this.head;
				while (!auxHead.next.isNIL()){
					auxHead = auxHead.next;
				}
				SingleLinkedListNode newNode = new SingleLinkedListNode(element, auxHead.next);
				auxHead.next = newNode;
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			if (!isEmpty()) {
				if (this.head.data.equals(element)) {
					this.head = this.head.next;
				} else {
					SingleLinkedListNode auxHead = this.head;
					SingleLinkedListNode previous = this.head;
					while (!auxHead.isNIL()) {
						if (auxHead.data.equals(element)) {
							previous.next = auxHead.next;
							break;
						}
						previous = auxHead;
						auxHead = auxHead.next;
					}
				}
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] result = (T[]) new Object[size()];
		int cont = 0;
		SingleLinkedListNode auxHead = this.head;
		while (!auxHead.isNIL()){
			result[cont] = (T) auxHead.data;
			cont++;
			auxHead = auxHead.next;
		}
		return result;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
