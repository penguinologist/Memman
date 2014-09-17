import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

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
