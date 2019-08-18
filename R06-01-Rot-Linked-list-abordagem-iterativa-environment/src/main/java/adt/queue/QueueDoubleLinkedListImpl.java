package adt.queue;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class QueueDoubleLinkedListImpl<T> implements Queue<T> {

	protected DoubleLinkedList<T> list;
	protected int size;

	public QueueDoubleLinkedListImpl(int size) {
		this.size = size;
		this.list = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()){
			throw new QueueOverflowException();
		} else {
			this.list.insert(element);
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T element;
		if (isEmpty()){
			throw new QueueUnderflowException();
		} else {
			element = this.list.toArray()[0];
			this.list.removeFirst();
		}
		return element;
	}

	@Override
	public T head() {
		T valor;
		if (this.list.isEmpty()){
			valor = null;
		} else {
			valor = this.list.toArray()[0];
		}
		return valor;
	}

	@Override
	public boolean isEmpty() {
		return this.list.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.list.size() == this.size;
	}

}