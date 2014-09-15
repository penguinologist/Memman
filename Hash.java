import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class Hash<K, V> implements Map<K, V>, Cloneable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int capacity;
	private int items;
	private int[] table;
	private int initialCapacity;

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
		items = 0;
		this.initialCapacity = initialCapacity;
	}

	private void extendArray() {
		int newCapacity = 2 * capacity;
		int[] newTable = new int[newCapacity];
		for (int i = 0; i < capacity; i++) {
			newTable[i] = table[i];
		}
		capacity = newCapacity;
		table = newTable;
		items = 0;

	}

	@Override
	public void clear() {
		int[] tab = table;
		for (int i = 0; i < tab.length; i++)
			tab[i] = -1;

		table = tab;
		capacity = initialCapacity;
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

		int hash = (int) sfold((String) key, items);

		table[hash] = (Integer) value;
		items++;

		if (items > capacity / 2) {
			extendArray();
		}
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
		// not implemented
		return null;
	}

	// key = hash value
	@Override
	public V get(Object key) {
		
		
		int a = table[(int)sfold((String)key,capacity)];
		Integer b = new Integer (a);
		return  (V)b;
	}

	@Override
	public boolean isEmpty() {
		return items == 0;
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

		for (int i = 0; i < capacity; i++) {
			if (table[0] == (int) key) {
				items--;
				table[0] = -1;
				break;
			}
		}
		items--;
		return null;
	}

	@Override
	public int size() {

		return capacity;
	}

	@Override
	public Collection<V> values() {
		// not implemented
		return null;
	}

}
