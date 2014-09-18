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
 * The test class for the node class
 * 
 * @author Jeroen
 * @author Phuong Le (lpd91)
 * @version 2014.09.14
 */
public class NodeTest {

	Node<String> temp;

	/**
	 * Sets up the node
	 */
	@Before
	public void setUp() {
		temp = new Node<String>("test");
	}

	/**
	 * tests the data method
	 */
	@Test
	public void testData() {
		assertEquals("test", temp.data());
	}

	/**
	 * tests the setData method
	 */
	@Test
	public void testSetData() {
		temp.setData("newTest");
		assertEquals("newTest", temp.data());
	}

	/**
	 * tests the next method
	 */
	@Test
	public void testNext() {
		Node<String> two = new Node<String>("t");
		temp.join(two);
		assertEquals("t", temp.next().data());
	}

	/**
	 * tests the previous method
	 */
	@Test
	public void testPrevious() {
		Node<String> two = new Node<String>("t");
		temp.join(two);
		assertEquals("test", two.previous().data());
	}

	/**
	 * tests the join method
	 */
	@Test
	public void testJoin() {
		Node<String> two = new Node<String>("t");
		temp.join(two);
		assertEquals("test", two.previous().data());
	}

	/**
	 * tests the split method
	 */
	@Test
	public void testSplit() {
		Node<String> two = new Node<String>("t");
		temp.join(two);
		two = temp.split();
		assertEquals("t", two.data());
	}

}
