package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(isFull()){
			throw new QueueOverflowException();
		}
		if(head == -1){
			head = 0;
		}
		tail = (tail+1) % array.length;
		array[tail] = element;
		elements++;
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(isEmpty()){
			throw new QueueUnderflowException();
		}
		T result = array[head];
		head = (head+1) % array.length;
		elements--;
		if(elements==0){
			head =-1;
		}
		return result;
	}

	@Override
	public T head() {
		return array[head];
	}

	@Override
	public boolean isEmpty() {
		 if (elements == 0){
		 	return true;
		 }
		 return false;
	}

	@Override
	public boolean isFull() {
		if (elements == array.length){
			return true;
		}
		return false;
	}

}
