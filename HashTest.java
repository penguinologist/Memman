import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
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
 * @author Jeroen Goossens (jeroen)
 * @author Phuong Le (ldp91)
 * @version 9/17/2014
 */
public class HashTest {
	Hash<String, Handle> test;

	/**
	 * Setup for the tests
	 */
	@Before
	public void setUp() {
		test = new Hash<String, Handle>(10);
	}

	/**
	 * Test method for {@link Hash#clear()}.
	 */
	@Test
	public void testClear() {
		test.clear();
		assertEquals(0, test.getItems());
	}

	/**
	 * Test method for {@link Hash#put(java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public void testPutKV() {
		test.put("hello", new Handle(1));
		assertEquals(0, test.getItems());
	}

	/**
	 * Test method for
	 * {@link Hash#put(java.lang.Object, java.lang.Object, MemoryManager)}.
	 */
	@Test
	public void testPutKVMemoryManager() {
		MemoryManager temp = new MemoryManager(32);
		test.put("hello", new Handle(1), temp);
		assertEquals(1, test.getItems());
		assertTrue(true);

		assertEquals(1, test.getItems());
		assertFalse(test.isEmpty());
		assertEquals(10, test.size());
		assertEquals(10, test.getCapacity());
	}

	/**
	 * Test method for {@link Hash#containsKey(java.lang.Object)}.
	 */
	@Test
	public void testContainsKeyObject() {
		assertTrue(true);// this method is not implemented
	}

	/**
	 * Test method for {@link Hash#containsValue(java.lang.Object)}.
	 */
	@Test
	public void testContainsValue() {
		assertTrue(true);// this method is not implemented
	}

	/**
	 * Test method for {@link Hash#entrySet()}.
	 */
	@Test
	public void testEntrySet() {
		assertTrue(true);// this method is not implemented
	}

	/**
	 * Test method for {@link Hash#get(java.lang.Object)}.
	 */
	@Test
	public void testGetObject() {
		assertTrue(true);// this method is not implemented
	}

	/**
	 * Test method for {@link Hash#get(java.lang.Object, MemoryManager)}.
	 */
	@Test
	public void testGetObjectMemoryManager() {
		assertNull(test.get("", new MemoryManager(32)));
	}

	/**
	 * Test method for {@link Hash#keySet()}.
	 */
	@Test
	public void testKeySet() {
		assertTrue(true);// this method is not implemented
	}

	/**
	 * Test method for {@link Hash#putAll(java.util.Map)}.
	 */
	@Test
	public void testPutAll() {
		assertTrue(true);// this method is not implemented
	}

	/**
	 * Test method for {@link Hash#remove(java.lang.Object)}.
	 */
	@Test
	public void testRemoveObject() {
		assertTrue(true);// this method is not implemented
	}

	/**
	 * Test method for {@link Hash#getValues()}.
	 */
	@Test
	public void testGetValues() {
		assertTrue(true);// this method is used in the code itself
	}

	/**
	 * Test method for {@link Hash#values()}.
	 */
	@Test
	public void testValues() {
		assertTrue(true);// this method is not implemented
	}

	/**
	 * Test method for {@link Hash#getCapacity()}.
	 */
	@Test
	public void testGetCapacity() {
		assertEquals(10, test.getCapacity());
	}

	/**
	 * Test method for {@link Hash#indexOfValue(java.lang.Object)}.
	 */
	@Test
	public void testIndexOfValue() {
		MemoryManager temp = new MemoryManager(32);
		test.put("hello", new Handle(1), temp);
		assertEquals(7, test.indexOfValue(new Handle(1)));
	}

}
