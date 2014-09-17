// -------------------------------------------------------------------------
/**
 * Double linked list class
 * 
 * @author Phuong Le(Ldp91)
 * @author Jeroen
 * @version 2014.09.14
 * @param <T>
 *            to make it generic
 */
public class DoubleLinkedList<T> {
	/**
	 * local variables
	 */
	private Node<T> head;

	private int size;

	// ----------------------------------------------------------
	/**
	 * Constructor for this class
	 */
	public DoubleLinkedList() {
		head = new Node<T>(null);
		head.join(head);
		size = 0;
	}

	// ----------------------------------------------------------
	/**
	 * Adds data to the list at a certain position
	 * 
	 * @param newData
	 *            to be placed at the given position
	 * @param position
	 *            to place the item
	 */
	public void append(T newData, int position) {
		if (position > size) {
			return;
		}
		Node<T> current = head;
		for (int i = 0; i < position; i++) {
			current = current.next();
		}
		Node<T> temp = current.split();
		Node<T> newNode = new Node<T>(newData);
		newNode.join(temp);
		current.join(newNode);
		size++;
	}

	// ----------------------------------------------------------
	/**
	 * removes the node at a certain position
	 * 
	 * @param position
	 *            where the node is supposed to be removed
	 */
	public void remove(int position) {
		if (position >= size) {
			return;
		}
		Node<T> current = head;
		for (int i = 0; i <= position; i++) {
			current = current.next();
		}
		Node<T> temp = current.previous();
		temp.split();
		temp.join(current.split());
		size--;
	}

	// ----------------------------------------------------------
	/**
	 * Get element at a position. Index starts with 0
	 * 
	 * @param index
	 *            position need to get data
	 * @return Data at index
	 */
	public T getElement(int index) {
		Node<T> current = head;
		for (int i = 0; i <= index; i++) {
			current = current.next();
		}
		return current.data();
	}

	// ----------------------------------------------------------
	/**
	 * Returns the size of the linkedlist
	 * 
	 * @return int size of the linkedlist
	 */
	public int getSize() {
		return this.size;
	}

}
