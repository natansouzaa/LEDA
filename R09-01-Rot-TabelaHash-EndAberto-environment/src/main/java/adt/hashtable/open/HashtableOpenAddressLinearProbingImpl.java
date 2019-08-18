package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;
import adt.hashtable.hashfunction.HashFunctionOpenAddress;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends
		AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size,
												 HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (element != null){
			if (isFull()){
				throw new HashtableOverflowException();
			} else {
				int contador = 0;
				boolean validacao = true;
				int index;
				T elementTable;
				while (validacao && contador < this.capacity()){
					index = (((HashFunctionOpenAddress<T>)this.hashFunction).hash(element, contador));
					elementTable = (T) this.table[index];
					if (elementTable == null || elementTable == new DELETED()){
						validacao = false;
						this.table[index] = element;
						this.elements++;
					} else {
						this.COLLISIONS++;
						contador++;
					}
				}
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null){
			if (!isEmpty()){
				int contador = 0;
				boolean validacao = true;
				int index;
				T elementTable;
				while (validacao && contador < this.capacity()){
					index = (((HashFunctionOpenAddress<T>)this.hashFunction).hash(element, contador));
					elementTable = (T) this.table[index];
					if (elementTable == null){
						validacao = false;
					} else {
						if (elementTable.equals(element)){
							this.table[index] = new DELETED();
							validacao = false;
							this.elements--;
						}
						contador++;
					}
				}
			}
		}
	}

	@Override
	public T search(T element) {
		T result = null;
		if (element != null){
			boolean validacao = true;
			int contador = 0;
			int index;
			T elementTable;
			while (validacao && contador < this.capacity()){
				index = (((HashFunctionOpenAddress<T>)this.hashFunction).hash(element, contador));
				elementTable = (T) this.table[index];
				if (elementTable == null){
					validacao = false;
				} else {
					if (elementTable.equals(element)){
						result = element;
						validacao = false;
					}
					contador++;
				}
			}
		}
		return result;
	}

	@Override
	public int indexOf(T element) {
		if (element != null){
			int contador = 0;
			boolean validacao = true;
			int index;
			T elementTable;
			while (validacao && contador < this.capacity()){
				index = (((HashFunctionOpenAddress<T>)this.hashFunction).hash(element, contador));
				elementTable = (T) this.table[index];
				if (elementTable == null){
					validacao = false;
				} else {
					if (elementTable.equals(element)){
						return index;
					}
					contador++;
				}
			}
		}
		return -1;
	}

}