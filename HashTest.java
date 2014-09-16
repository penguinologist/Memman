import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 *
 */

/**
 * @author Jeroen Goossens (jeroen)
 * @author Phuong Le (ldp91)
 */
public class HashTest {
	Hash<String, Integer> test;

	@Before
	public void setUp() {
		test = new Hash<String, Integer>(32);

	}

	@Test
	public void testClear() {
		test.put("blablabla", test.size());
		assertEquals(1, test.size());
		test.clear();
		assertEquals(0, test.size());
	}

	@Test
	public void testContainsKey() {
		test.put("test value", test.size());
		assertEquals(32, test.size());
		assertEquals(1, test.size());
		assertTrue(test.containsKey("test value"));
	}

	@Test
	public void testGet() {
		test.put("test", test.size());
		assertEquals(32, (int)test.get("test"));

	}

	@Test
	public void testisEmtpy() {
		assertTrue(test.isEmpty());
		test.put("test value", test.size());
		assertEquals(32, test.size());

	}

	@Test
	public void testRemove() {
		test.put("test value", test.size());
		assertEquals(1, test.size());
		test.remove("test value");
		assertEquals(0, test.size());


	}

	@Test
	public void testSize() {
		assertEquals(32, test.size());
	}
}
