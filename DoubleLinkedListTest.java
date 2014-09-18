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
 * @author Jeroen
 * @author Phuong Le (lpd91)
 * @version 2014.09.14
 */
public class DoubleLinkedListTest {

	private DoubleLinkedList<String> dll;

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
