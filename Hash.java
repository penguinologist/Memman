import java.awt.List;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * This class is a hashmap implementation for the CS3114 project
 * 
 * @author Jeroen
 * @author Phuong Le
 * 
 * @param <K>
 *            key to the hashmap
 * @param <V>
 *            value of the hashmap
 */
public class Hash<K, V> implements Map<K, V>, Cloneable, Serializable {
	/**
	 * local variables
	 */
	private static final long serialVersionUID = 1L;
	private int capacity;
	private int items;
	private int[] table;

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

		table = new int[capacity];
		for (int i = 0; i < table.length; i++)
			table[i] = -1;
		items = 0;
	}

	// ----------------------------------------------------------
	/**
	 * This method extends the hashmap
	 */
	public void extendHash() {
		int newCapacity = 2 * capacity;
		int[] newTable = new int[newCapacity];
		for (int i = 0; i < capacity; i++) {
			newTable[i] = table[i];
		}
		for (int i = capacity; i < newCapacity; i++) {
			newTable[i] = -1;
		}
		capacity = newCapacity;
		table = newTable;
	}

	/**
	 * this method clears the hashmap
	 */
	@Override
	public void clear() {
		for (int i = 0; i < table.length; i++)
			table[i] = -1;
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
		int index = (int) sfold((String) key, capacity);
		table[index] = (Integer) value;
		items++;
		Integer result = index;
		return (V) result;
	}

	// Use folding on a string, summed 4 bytes at a time
	private long sfold(String s, int M) {
		int intLength = s.length() / 4;
		long sum = 0;
		for (int j = 0; j < intLength; j++) {
			char c[] = s.substring(j * 4, (j * 4) + 4).toCharArray();
			long mult = 1;
			for (int k = 0; k < c.length; k++) {
				sum += c[k] * mult;
				mult *= 256;
			}
		}

		char c[] = s.substring(intLength * 4).toCharArray();
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
		int k = (int) sfold((String) key, capacity);
		return table[k] != -1;
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
		for (int i = 0; i < capacity; i++) {
			if (table[i] == (int) value) {
				return true;
			}
		}
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
		int a = table[(int) sfold((String) key, capacity)];
		Integer b = new Integer(a);
		return (V) b;
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
	 * @param Map
	 *            <?,?> to be imported
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
		int index = (int) sfold((String) key, capacity);
		Integer result = table[index];
		table[index] = -1;
		items--;
		return (V) result;
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
	public DoubleLinkedList<Integer> getValues() {
		DoubleLinkedList<Integer> result = new DoubleLinkedList<Integer>();
		for (int i = 0; i < capacity; i++) {
			if (table[i] != -1) {
				result.append(new Integer(table[i]), result.getSize());
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
		List result = new List();
		for (int i = 0; i < capacity; i++) {
			if (table[i] != -1) {
				result.add(Integer.toString(table[i]));
			}
		}
		return (Collection<V>) result;
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

}
