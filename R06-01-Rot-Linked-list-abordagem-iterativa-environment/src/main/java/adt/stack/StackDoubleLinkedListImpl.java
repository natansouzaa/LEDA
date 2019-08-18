package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (isFull()){
			throw new StackOverflowException();
		} else {
			this.top.insert(element);
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		T element;
		if (isEmpty()){
			throw new StackUnderflowException();
		} else {
			element = this.top.toArray()[this.top.size() - 1];
			this.top.removeLast();
		}
		return element;
	}

	@Override
	public T top() {
		T valor;
		if (this.top.isEmpty()){
			valor = null;
		} else {
			valor = this.top.toArray()[this.top.size() - 1];
		}
		return valor;
	}

	@Override
	public boolean isEmpty() {
		return this.top.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.top.size() == this.size;
	}

}