// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.
// -------------------------------------------------------------------------
/**
 * doubly linked list of generic type
 * 
 * @author1 Phuong Le(Ldp91)
 * @version 2014.09.14
 * @param <T>
 *            generic type
 */
public class DoubleLinkedList<T> {
	/**
	 * class vars
	 */
	private Node<T> head;
	private Node<T> tail;
	private int size;

	// ----------------------------------------------------------
	/**
	 * Create a new DoubleLinkedList object.
	 */
	public DoubleLinkedList() {
		head = new Node<T>(null);
		tail = new Node<T>(null);
		head.join(tail);
		size = 0;
	}

	// ----------------------------------------------------------
	/**
	 * appends data at a position
	 * 
	 * @param newData
	 *            to be added
	 * @param position
	 *            where data is to be placed
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
	 * removes at a position
	 * 
	 * @param position
	 *            to be removed
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
	 * gets the size of the list
	 * 
	 * @return int value of the size
	 */
	public int getSize() {
		return this.size;
	}

}
