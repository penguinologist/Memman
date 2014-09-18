import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

//On my honor:
//
//- I have not used source code obtained from another student,
//or any other unauthorized source, either modified or
//unmodified.
//
//- All source code and documentation used in my program is
//either my original work, or was derived by me from the
//source code published in the textbook for this course.
//
//- I have not discussed coding details about this project with
//anyone other than my partner (in the case of a joint
//submission), instructor, ACM/UPE tutors or the TAs assigned
//to this course. I understand that I may discuss the concepts
//of this program with other students, and that another student
//may help me debug my program so long as neither of us writes
//anything during the discussion or modifies any computer file
//during the discussion. I have violated neither the spirit nor
//letter of this restriction.

/**
 * This class is a hashmap implementation for the CS3114 project
 * 
 * @author Jeroen
 * @author Phuong Le
 * @param <K>
 *            key to the hashmap
 * @param <V>
 *            value of the hashmap
 * @version 2014.09.14
 */
public class Hash<K, V> implements Map<K, V>, Cloneable, Serializable {
	/**
	 * local variables
	 */
	private static final long serialVersionUID = 1L;
	private int capacity;
	private int items;
	// private int[] table;
	private Handle[] table;

	// ----------------------------------------------------------
	/**
	 * Creates a new Hash object.
	 * 
	 * @param initialCapacity
	 *            of the hashmap
	 */
	public Hash(int initialCapacity) {
		if (initialCapacity % 2 == 0) {
			capacity = initialCapacity;
		} else {
			capacity = initialCapacity + 1;
		}

		table = new Handle[capacity];
		for (int i = 0; i < table.length; i++) {
			table[i] = new Handle(-1);
		}
		items = 0;
	}

	/**
	 * this method clears the hashmap
	 */
	@Override
	public void clear() {
		for (int i = 0; i < table.length; i++) {
			table[i] = null;
		}
		items = 0;
	}

	/**
	 * This map puts a value with a certain key in the hashmap.
	 * 
	 * @param key
	 *            to the hashmap
	 * @param value
	 *            of the item to be stored
	 * @return V the item that was stored
	 */
	@Override
	public V put(K key, V value) {
		// not implemented
		return null;
	}

	/**
	 * This map puts a value with a certain key in the hashmap.
	 * 
	 * @param key
	 *            to the hashmap
	 * @param value
	 *            of the item to be stored
	 * @param compareData
	 * @return V the item that was stored
	 */
	public V put(K key, V value, MemoryManager compareData) {

		if (!containsKey(key, compareData)) {
			int index = (int) sfold((String) key, capacity);
			int newIndex = index;
			int j = 0;
			while (table[newIndex].getStartPosition() != -1) {
				j++;
				newIndex = (index + j * j) % capacity;
			}
			table[newIndex] = (Handle) value;
			items++;
		}
		return null;
	}

	// Use folding on a string, summed 4 bytes at a time
	/**
	 * Formula to get the position of a String in hash Table
	 * 
	 * @param s
	 *            string to be stored
	 * @param M
	 *            size of the list
	 * @return long value
	 */
	private long sfold(String s, int M) {
		int intLength = s.length() / 4;
		long sum = 0;
		for (int j = 0; j < intLength; j++) {
			char[] c = s.substring(j * 4, (j * 4) + 4).toCharArray();
			long mult = 1;
			for (int k = 0; k < c.length; k++) {
				sum += c[k] * mult;
				mult *= 256;
			}
		}

		char[] c = s.substring(intLength * 4).toCharArray();
		long mult = 1;
		for (int k = 0; k < c.length; k++) {
			sum += c[k] * mult;
			mult *= 256;
		}

		return (Math.abs(sum) % M);
	}

	/**
	 * This method checks if the hashmap contains a certain key
	 * 
	 * @param key
	 *            to the hash to be checked
	 * @return boolean value indicating validity
	 */
	@Override
	public boolean containsKey(Object key) {
		// not implemented

		return false;
	}

	// ----------------------------------------------------------
	/**
	 * This method checks if a memorymanager contains a certain key
	 * 
	 * @param key
	 *            to be checked
	 * @param compareData
	 *            the memorymanager to check
	 * @return boolean value indicating validity
	 */
	public boolean containsKey(Object key, MemoryManager compareData) {

		int index = (int) sfold((String) key, capacity);

		if (table[index].getStartPosition() == -1) {
			return false;
		} else {
			if (table[index].getStartPosition() == -2) {
				// tomb stone
				boolean notATombStone = false;
				int j = 0;
				int originalIndex = index;
				index = index + (j * j) % capacity;
				while (!notATombStone) {
					index = (originalIndex + j * j) % capacity;
					if (table[index].getStartPosition() == -1) {
						return false;
					}
					if (table[index].getStartPosition() >= 0) {
						notATombStone = true;
					}
					if (j > capacity * 2) {
						notATombStone = true;
					}
					j++;
				}
				return (compareData.getData(table[index].getStartPosition())
						.compareToIgnoreCase((String) key) == 0);

			}
			return (compareData.getData(table[index].getStartPosition())
					.compareToIgnoreCase((String) key) == 0);
		}

	}

	/**
	 * this method checks if the hashmap contains a certain value
	 * 
	 * @param value
	 *            to be checked
	 * @return boolean value indicating validity
	 */
	@Override
	public boolean containsValue(Object value) {
		// not implemented
		return false;
	}

	/**
	 * This method is not implemented
	 * 
	 * @return Set to be returned
	 */
	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// not implemented
		return null;
	}

	// key = hash index
	/**
	 * This method returns an object according to the provided key
	 * 
	 * @param key
	 *            to the hash
	 * @return V value, possibly null if not found.
	 */
	@Override
	public V get(Object key) {
		// Handle a = table[new Handle((int) sfold((String) key, capacity))];
		//
		// return (V) a;
		return null; // not implemented
	}

	@SuppressWarnings("unchecked")
	public V get(Object key, MemoryManager compareData) {
		int count = 0;
		for (int i = 0; i < table.length; i++) {
			if (table[i].getStartPosition() >= 0) {
				if (compareData.getData(table[i].getStartPosition())
						.compareToIgnoreCase((String) key) == 0) {
					return (V) table[i];
				}
				count++;
			}
			if (count == items) {
				return null;
			}
		}
		return null;
	}

	/**
	 * This method checks if the hashmap is empty
	 * 
	 * @return boolean value indicating validity
	 */
	@Override
	public boolean isEmpty() {
		return items == 0;
	}

	/**
	 * This method sets the key to a set IT IS NOT IMPLEMENTED.
	 * 
	 * @return Set<K> to be returned
	 */
	@Override
	public Set<K> keySet() {
		// Not Implemented
		return null;
	}

	/**
	 * This method is not implemented
	 * 
	 * @param m
	 *            to be imported
	 */
	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// not implemented
	}

	/**
	 * This method removes the element with a certain key and returns it.
	 * 
	 * @param key
	 *            to delete
	 * @return V value to be returned after having been deleted from the hashmap
	 */
	@Override
	public V remove(Object key) {
		return null;// not implemented
	}

	@SuppressWarnings("unchecked")
	public V remove(Object key, MemoryManager compareData) {

		int index = (int) sfold((String) key, capacity);

		if (table[index].getStartPosition() == -1) {
			return null;
		} else if (table[index].getStartPosition() == -2) {
			// tomb stone
			boolean notATombStone = false;
			int j = 0;
			int originalIndex = index;
			index = index + (j * j) % capacity;

			while (!notATombStone) {

				index = originalIndex + (j * j);
				index = index % capacity;
				if (table[index].getStartPosition() == -1) {
					notATombStone = true;
				} else {
					if (compareData.getData(table[index].getStartPosition())

					.compareToIgnoreCase((String) key) == 0) {
						table[index] = new Handle(-2);
						notATombStone = true;
						items--;
					}
				}

				if (j > capacity * 2) {
					notATombStone = true;
				}

				j++;
			}

			return (V) (compareData.getData(table[index].getStartPosition()));
		}

		else if (compareData.getData(table[index].getStartPosition())
				.compareToIgnoreCase((String) key) == 0) {
			Handle result = table[index];
			table[index] = new Handle(-2);
			items--;
			return (V) result;
		}
		return null;

	}

	/**
	 * This method returns the size of the hashmap, which is dynamically
	 * changing
	 * 
	 * @return int size of the hash map
	 */
	@Override
	public int size() {
		return capacity;
	}

	/**
	 * This method returns all the values of the hash map as a doublelinkelist
	 * of Integers
	 * 
	 * @return DoubleLinkedList<Integer> list of all values in the hash map
	 */
	public DoubleLinkedList<Handle> getValues() {
		DoubleLinkedList<Handle> result = new DoubleLinkedList<Handle>();
		for (int i = 0; i < capacity; i++) {
			if (table[i].getStartPosition() >= 0) {
				result.append(new Handle(table[i].getStartPosition()),
						result.getSize());
			}
		}
		return result;
	}

	/**
	 * This method returns the values of the hash map as a collection
	 * 
	 * @return Collection<V> collection of the values
	 */
	@Override
	public Collection<V> values() {
		// not implemented

		return null;
	}

	/**
	 * Thie method gets the capacity
	 * 
	 * @return int value of the capacity of the hashmap
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * this method returns the number of items in the hashmap
	 * 
	 * @return int value of the number of items in the hashmap
	 */
	public int getItems() {
		return items;
	}

	/**
	 * Retrieves the index of a certain key
	 * 
	 * @param val
	 *            of which to find the index
	 * @return int value of the index return -1 if there is no value in the hash
	 *         table
	 */
	public int indexOfValue(V val) {
		for (int i = 0; i < table.length; i++) {
			if (table[i].equals((Handle) val)) {
				return i;
			}
		}
		return -1;
	}

}
