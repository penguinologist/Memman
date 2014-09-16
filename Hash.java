import java.awt.List;
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

	// ----------------------------------------------------------
	/**
	 * Create a new Hash object.
	 * @param initialCapacity
	 */
	public Hash(int initialCapacity) {
		capacity = 1;
		while (capacity < initialCapacity)
			capacity *= 2;

		table = new int[capacity];
		for (int i = 0; i < table.length; i++)
            table[i] = -1;
		items = 0;
		this.initialCapacity = initialCapacity;
	}

	// ----------------------------------------------------------
	/**
	 * Place a description of your method here.
	 */
	public void extendHash() {
		int newCapacity = 2 * capacity;
		int[] newTable = new int[newCapacity];
		for (int i = 0; i < capacity; i++)
		{
			newTable[i] = table[i];
		}
		for (int i = capacity; i<newCapacity; i++)
		{
		    newTable[i] = -1;
		}
		capacity = newCapacity;
		table = newTable;
	}

	@Override
	public void clear() {
		for (int i = 0; i < table.length; i++)
			table[i] = -1;
		items = 0;
		capacity = initialCapacity;
	}

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

	@Override
	public boolean containsKey(Object key) {
		int k = (int) sfold((String) key, capacity);
		return table[k] != -1;
	}

	@Override
	public boolean containsValue(Object value) {
		for (int i=0; i<capacity; i++)
		{
		    if (table[i] == (int)value)
		    {
		        return true;
		    }
		}
		return false;
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// not implemented
		return null;
	}

	// key = hash index
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
		//Not Implement
	    return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// not implemented

	}

	@Override
	public V remove(Object key) {
	    int index = (int) sfold((String) key, capacity);
		Integer result = table[index];
		table[index] = -1;
		items--;
		return (V) result;
	}

	@Override
	public int size() {

		return capacity;
	}

	public DoubleLinkedList<Integer> getValues()
	{
	    DoubleLinkedList<Integer> result = new DoubleLinkedList<Integer>();
	    for (int i= 0; i<capacity; i++)
        {
            if (table[i] != -1)
            {
                result.append(new Integer(table[i]), result.getSize());
            }
        }

	    return result;
	}

	@Override
	public Collection<V> values() {
	    List result = new List();
        for (int i= 0; i<capacity; i++)
        {
            if (table[i] != -1)
            {
                result.add(Integer.toString(table[i]));
            }
        }
        return (Collection<V>)result;
	}

    public int getCapacity()
    {
        return capacity;
    }

    public int getItems()
    {
        return items;
    }

}
