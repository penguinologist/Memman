package Backend;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class Hash<K, V> implements Map<K, V>, Cloneable, Serializable {
	private int size;

	private int capacity;
	private Entry[] table;

	public Hash(int initialCapacity) {
		capacity = 1;
		while (capacity < initialCapacity)
			capacity *= 2;

		table = new Entry[capacity];

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		size = 0;
	}

	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V get(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub
		return null;
	}

}
