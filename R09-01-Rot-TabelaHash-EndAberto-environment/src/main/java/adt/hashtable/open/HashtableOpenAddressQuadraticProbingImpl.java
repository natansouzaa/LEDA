package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionOpenAddress;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size,
													HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
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
				while (validacao && contador < this.capacity()){
					index = (((HashFunctionOpenAddress<T>)this.hashFunction).hash(element, contador));
					if (this.table[index] == null || this.table[index] == new DELETED()){
						this.table[index] = element;
						validacao = false;
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
				while (validacao && contador < this.capacity()){
					index = (((HashFunctionOpenAddress<T>)this.hashFunction).hash(element, contador));
					if (this.table[index] == null){
						validacao = false;
					} else {
						if (this.table[index].equals(element)){
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
			int contador = 0;
			boolean validacao = true;
			int index;
			while (validacao && contador < this.capacity()){
				index = (((HashFunctionOpenAddress<T>)this.hashFunction).hash(element, contador));
				if (this.table[index] == null){
					validacao = false;
				} else {
					if (this.table[index].equals(element)){
						result = element;
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
			while (validacao && contador < this.capacity()){
				index = (((HashFunctionOpenAddress<T>)this.hashFunction).hash(element, contador));
				if (this.table[index] == null){
					validacao = false;
				} else {
					if (this.table[index].equals(element)){
						validacao = false;
						return index;
					}
					contador++;
				}
			}
		}
		return -1;
	}

}