import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the memorymanager
 * 
 * @author Jeroen
 * @author Phuong Le
 */
public class MemoryManagerTest {

	/**
	 * local vars
	 */
	MemoryManager temp;

	/**
	 * This sets up the memory manager
	 */
	@Before
	public void setUp() {
		temp = new MemoryManager(10);

	}

	/**
	 * Test method for {@link MemoryManager#insert(byte[])}.
	 */
	@Test
	public void testInsert() {
		assertEquals("(0,3)", temp.insert(new byte[] { 1 }).toString());

	}

	/**
	 * Test method for {@link MemoryManager#getData(int)}.
	 */
	@Test
	public void testGetData() {
		temp.insert(new byte[] { (byte) 'a' });
		assertEquals("a", temp.getData(0));
	}

	/**
	 * Test method for {@link MemoryManager#removeAt(int)}.
	 */
	@Test
	public void testRemoveAt() {
		temp.insert(new byte[] { (byte) 'a' });
		temp.insert(new byte[] { (byte) 'b' });
		temp.removeAt(0);
		assertEquals("a", temp.getData(0));
	}

	/**
	 * Test method for {@link MemoryManager#getFreeMemory()}.
	 */
	@Test
	public void testGetFreeMemory() {
		temp.insert(new byte[] { (byte) 'a' });
		assertEquals("(3,7)", temp.getFreeMemory());
	}

}
