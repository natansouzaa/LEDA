package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;

	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}

	
	@Override
	public void insert(int key, T newValue, int height) {
		SkipListNode<T>[] update = new SkipListNode[this.maxHeight];
		SkipListNode<T> aux = this.root;
		for (int i = this.maxHeight - 1; i >= 0; i--){
			while (aux.forward[i] != null && aux.forward[i].key < key){
				aux = aux.forward[i];
			}
			update[i] = aux;
		}
		aux = aux.forward[0];
		if (aux.key == key){
			aux.value = newValue;
		} else {
			aux = new SkipListNode<T>(key, height, newValue);
			for (int i = 0; i < height; i++){
				aux.forward[i] = update[i].forward[i];
				update[i].forward[i] = aux;
			}
		}
	}

	@Override
	public void remove(int key) {
		SkipListNode<T>[] update = new SkipListNode[this.maxHeight];
		SkipListNode<T> aux = this.root;
		for (int i = this.maxHeight - 1; i >= 0; i--){
			while (aux.forward[i] != null && aux.forward[i].key < key){
				aux = aux.forward[i];
			}
			update[i] = aux;
		}
		aux = aux.forward[0];
		if (aux.key == key){
			int i = 0;
			while (i < this.maxHeight && update[i].forward[i] == aux){
				update[i].forward[i] = aux.forward[i];
				i++;
			}
		}
	}

	@Override
	public int height() {
		int result = 0;
		SkipListNode<T> aux = this.root.forward[0];
		while (!aux.equals(NIL)){
			if (aux.height() > result){
				result = aux.height();
			}
			aux = aux.forward[0];
		}
		return result;
	}

	@Override
	public SkipListNode<T> search(int key) {
		SkipListNode aux = this.root;
		for (int i = this.maxHeight - 1; i >= 0; i--){
			while (aux.forward[i] != null && aux.forward[i].key < key){
				aux = aux.forward[i];
			}
		}
		aux = aux.forward[0];
		if (aux.key == key){
			return aux;
		} else {
			return null;
		}
	}

	@Override
	public int size() {
		int result = 0;
		SkipListNode<T> aux = this.root.forward[0];
		while (!aux.equals(NIL)){
			result++;
			aux = aux.forward[0];
		}
		return result;
	}

	@Override
	public SkipListNode<T>[] toArray() {
		SkipListNode<T>[] array = new SkipListNode[this.size() + 2];
		SkipListNode<T> aux = this.root;
		int i = 0;
		while (i != this.size() + 2){
			array[i] = aux;
			aux = aux.forward[0];
			i++;
		}
		return array;
	}

}
