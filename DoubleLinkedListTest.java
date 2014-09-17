import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Jeroen
 * @author Phuong Le (lpd91)
 * @version 2014.09.14
 */
public class DoubleLinkedListTest {

	DoubleLinkedList<String> dll;

	/**
	 * Sets up the tests
	 */
	@Before
	public void setUp() {
		dll = new DoubleLinkedList<String>();
	}

	/**
	 * Test method for {@link DoubleLinkedList#append(java.lang.Object, int)}.
	 */
	@Test
	public void testAppend() {
		dll.append("test", 0);
		dll.append("test2", 1);
		assertEquals("test", dll.getElement(0));
	}

	/**
	 * Test method for {@link DoubleLinkedList#remove(int)}.
	 */
	@Test
	public void testRemove() {
		dll.append("test", 0);
		dll.remove(0);
		assertEquals(0, dll.getSize());
	}

	/**
	 * Test method for {@link DoubleLinkedList#getElement(int)}.
	 */
	@Test
	public void testGetElement() {
		dll.append("lalala", 0);
		assertEquals("lalala", dll.getElement(0));
	}

	/**
	 * Test method for {@link DoubleLinkedList#getSize()}.
	 */
	@Test
	public void testGetSize() {
		dll.append("test", 0);
		dll.remove(0);
		assertEquals(0, dll.getSize());
	}

}
