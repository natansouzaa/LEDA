package adt.hashtable.closed;

import java.util.LinkedList;

import java.util.List;

import adt.hashtable.closed.AbstractHashtableClosedAddress;
import adt.hashtable.hashfunction.HashFunction;
import adt.hashtable.hashfunction.HashFunctionClosedAddress;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionFactory;
import util.Util;

public class HashtableClosedAddressImpl<T> extends AbstractHashtableClosedAddress<T> {

	protected Util util;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashtableClosedAddressImpl(int desiredSize, HashFunctionClosedAddressMethod method) {
		int realSize = desiredSize;
		if (method == HashFunctionClosedAddressMethod.DIVISION) {
			realSize = this.getPrimeAbove(desiredSize); // real size must the
			// the immediate prime
			// above
		}
		initiateInternalTable(realSize);
		HashFunction function = HashFunctionFactory.createHashFunction(method, realSize);
		this.hashFunction = function;
	}

	int getPrimeAbove(int number) {
		while (!util.isPrime(number)){
			number++;
		}
		return number;
	}

	private int getIndex(T element) {
		int index = 0;
		index = ((HashFunctionClosedAddress) this.hashFunction).hash(element);
		return index;
	}

	@Override
	public void insert(T element) {
		if (element != null){
			int index = getIndex(element);
			LinkedList<T> elementTable = (LinkedList<T>) this.table[index];
			if (elementTable == null){
				elementTable = new LinkedList<T>();
				elementTable.add(element);
				this.table[index] = elementTable;
			} else {
				if (!elementTable.contains(element)){
					elementTable.add(element);
					this.COLLISIONS++;
				}
			}
			this.elements++;
		}

	}

	@Override
	public void remove(T element) {
		if (element != null){
			int index = getIndex(element);
			LinkedList<T> elementTable = (LinkedList<T>) this.table[index];
			if (elementTable != null){
				if (elementTable.size() > 1){
					this.COLLISIONS--;
				}
				elementTable.remove(element);
				this.elements--;
				if (elementTable.isEmpty()){
					this.table[index] = null;
				}
			}
		}

	}

	@Override
	public T search(T element) {
		T elementResult = null;
		if (element != null){
			int index = getIndex(element);
			LinkedList<T> elementTable = (LinkedList<T>) this.table[index];
			if (elementTable != null){
				if (elementTable.contains(element)){
					elementResult = element;
				}
			}
		}
		return elementResult;
	}

	@Override
	public int indexOf(T element) {
		boolean result = false;
		int index = getIndex(element);
		if (element != null){
			LinkedList<T> elementTable = (LinkedList<T>) this.table[index];
			if (elementTable != null){
				if (elementTable.contains(element)){
					result = true;
				}
			}
		}
		if (result){
			return index;
		} else {
			return -1;
		}
	}
}