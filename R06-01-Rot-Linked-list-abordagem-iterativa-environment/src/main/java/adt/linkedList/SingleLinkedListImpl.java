package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T>{

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl(){
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		int size = 0;
		SingleLinkedListNode<T> auxHead = this.head;
		while (!auxHead.isNIL()){
			size++;
			auxHead = auxHead.next;
		}
		return size;
	}

	@Override
	public T search(Object element) {
		SingleLinkedListNode<T> auxHead = this.head;
		while (!auxHead.isNIL() && !auxHead.data.equals(element)){
			auxHead = auxHead.next;
		}
		if (auxHead.isNIL()){
			return null;
		}
		return auxHead.data;
	}

	@Override
	public void insert(Object element) {
		SingleLinkedListNode auxHead = this.head;
		if (isEmpty()){
			SingleLinkedListNode<T> newHead = new SingleLinkedListNode(element, this.head);
			this.head = newHead;
		} else {
			while (!auxHead.next.isNIL()){
				auxHead = auxHead.next;
			}
			SingleLinkedListNode newNode = new SingleLinkedListNode(element, auxHead.next);
			auxHead.next = newNode;
		}
	}

	@Override
	public void remove(Object element) {
		if (this.head.data == element){
			this.head = this.head.next;
		} else {
			SingleLinkedListNode auxHead = this.head;
			SingleLinkedListNode previous = this.head;
			while (!auxHead.isNIL() && auxHead.data != element){
				previous = auxHead;
				auxHead = auxHead.next;
			}
			if (!auxHead.isNIL()){
				previous.next = auxHead.next;
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] result = (T[]) new Object[size()];
		SingleLinkedListNode auxHead = this.head;
		int count = 0;
		while (!auxHead.isNIL()){
			result[count] = (T) auxHead.data;
			auxHead = auxHead.next;
			count++;
		}
		return result;
	}

	@Override
	public int indexOf(T element){
		int result = -1;
		if (!isEmpty()){
			int cont = 0;
			SingleLinkedListNode auxHead = this.head;
			while (!auxHead.isNIL()){
				if (auxHead.data.equals(element)){
					result = cont;
					break;
				}
				cont++;
				auxHead = auxHead.next;
			}
		}
		return result;
	}

	@Override
	public int lastIndexOf(T element) {
		int result = -1;
		if (!isEmpty()){
			int cont = 0;
			SingleLinkedListNode auxHead = this.head;
			while (!auxHead.isNIL()){
				if (auxHead.data.equals(element)){
					result = cont;
				}
				cont++;
				auxHead = auxHead.next;
			}
		}
		return result;
	}

	@Override
	public void reverseS() {
		if (!isEmpty()){
			SingleLinkedListNode node;
			SingleLinkedListNode auxHead = this.head;
			SingleLinkedListNode newHead = new SingleLinkedListNode();
			while (!auxHead.isNIL()){
				node = auxHead.next;
				auxHead.next = newHead;
				newHead = auxHead;
				auxHead = node;
			}
			this.head = newHead;
		}
	}

	@Override
	public void removeWithIndex(int pos) {
		if (!isEmpty()){
			if (pos == 0){
				this.head = this.head.next;
			}
			int cont = 0;
			SingleLinkedListNode auxHead = this.head;
			SingleLinkedListNode previous = this.head;
			while (!auxHead.isNIL()){
				if (cont == pos){
					previous.next = auxHead.next;
					break;
				}
				cont++;
				previous = auxHead;
				auxHead = auxHead.next;
			}
		}
	}

}