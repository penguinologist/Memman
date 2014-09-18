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
 * This class tests the memorymanager
 * 
 * @author Jeroen
 * @author Phuong Le
 * @version 2014.09.14
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
