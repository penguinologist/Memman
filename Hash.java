import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class Hash<K, V> implements Map<K, V>, Cloneable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int size;
	private int capacity;
	private int[] table;

	/*
	 * 
	 * data is stored in memory manager entry table is the list of all entries
	 * (hash values)
	 */

	public Hash(int initialCapacity) {
		capacity = 1;
		while (capacity < initialCapacity)
			capacity *= 2;

		table = new int[capacity];
	}

	private void extendArray() {
		int newCapacity = 2 * capacity;
		int[] newTable = new int[newCapacity];
		for (int i = 0; i < capacity; i++) {
			newTable[i] = table[i];
		}
		capacity = newCapacity;
		size = newCapacity;
		table = newTable;

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		int[] tab = table;
		for (int i = 0; i < tab.length; i++)
			tab[i] = -1;

		table = tab;
		size = 0;
	}

	@Override
	public V put(K key, V value) {
		// original java source code
		//
		// if (key == null)
		// return putForNullKey(value);
		// int hash = hash(key.hashCode());
		// int i = indexFor(hash, table.length);
		// for (Entry<k, V> e = table[i]; e != null; e = e.next) {
		// Object k;
		// if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
		// V oldValue = e.value;
		// e.value = value;
		// e.recordAccess(this);
		// return oldValue;
		// }
		// }
		// modCount++;
		// addEntry(hash, key, value, i);

		if (key == null) {
			// do something
		}

		int hash = (int) sfold((String) value, size);

		return null;

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

	@Override
	public boolean containsKey(Object key) {
		int k = (int) key;
		for (int i = 0; i < table.length; i++) {
			if (table[i] == k) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean containsValue(Object value) {

		// not implemented
		return false;
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	// key = hash value
	@Override
	public V get(Object key) {
		if (key == null) {
			// do something
		}

		return null;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Set<K> keySet() {
		// not implemented
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// not implemented

	}

	@Override
	public V remove(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {

		return size;
	}

	@Override
	public Collection<V> values() {
		// not implemented
		return null;
	}

}
