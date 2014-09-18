import org.junit.Before;
import org.junit.Test;
import student.TestCase;
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
 * This class tests the Memman class and every other class it references to
 * 
 * @author Jeroen
 * @author Phuong Le
 */
public class MemmanTest extends TestCase {

	/**
	 * local variables
	 */

	Memman mem;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mem = new Memman();

	}

	/**
	 * test test
	 */
	@Test
	public void test() {

		String[] args = new String[3];
		args[0] = "10";
		args[1] = "32";
		args[2] = "data.txt";

		Memman.main(args);

		assertTrue(systemOut()
				.getHistory()
				.contains(
						"|When Summer's Through| does not exist in the song database."
								+ "(0,32)"
								+ "total songs: 0"
								+ "total artists: 0"
								+ "|Blind Lemon Jefferson| is added to the artist database."
								+ "Memory pool expanded to be 64 bytes."
								+ "|Long Lonesome Blues| is added to the song database."
								+ "|Ma Rainey| is added to the artist database."
								+ "Memory pool expanded to be 96 bytes."
								+ "|Ma Rainey's Black Bottom| is added to the song database."
								+ "Memory pool expanded to be 128 bytes."
								+ "|Charley Patton| is added to the artist database."
								+ "|Mississippi Boweavil Blues| is added to the song database."
								+ "Memory pool expanded to be 160 bytes."
								+ "|Sleepy John Estes| is added to the artist database."
								+ "Memory pool expanded to be 192 bytes."
								+ "|Street Car Blues| is added to the song database."
								+ "|Bukka White| is added to the artist database."
								+ "Memory pool expanded to be 224 bytes."
								+ "|Fixin' To Die Blues| is added to the song database."
								+ "|Blind Lemon Jefferson| 0"
								+ "|Sleepy John Estes| 1"
								+ "|Charley Patton| 4"
								+ "|Bukka White| 5"
								+ "|Ma Rainey| 7"
								+ "total artists: 5"
								+ "|Fixin' To Die Blues| 1"
								+ "|Mississippi Boweavil Blues| 2"
								+ "|Long Lonesome Blues| 5"
								+ "|Ma Rainey's Black Bottom| 6"
								+ "|Street Car Blues| 9"
								+ "total songs: 5"
								+ "Artist hash table size doubled."
								+ "|Guitar Slim| is added to the artist database."
								+ "Song hash table size doubled."
								+ "Memory pool expanded to be 256 bytes."
								+ "|The Things That I Used To Do| is added to the song database."
								+ "|Style Council| does not exist in the artist database."
								+ "|Ma Rainey| is removed from the artist database."
								+ "|Mississippi Boweavil Blues| is removed from the song database."
								+ "|(The Best Part Of) Breakin' Up| does not exist in the song database."
								+ "(44,11) -> (97,28) -> (239,17)"
								+ "|Blind Lemon Jefferson| duplicates a record already in the artist database."
								+ "|Got The Blues| is added to the song database."
								+ "|Little Eva| is added to the artist database."
								+ "Memory pool expanded to be 288 bytes."
								+ "|The Loco-Motion| is added to the song database."
								+ "|Blind Lemon Jefferson| 0"
								+ "|Bukka White| 4"
								+ "|Sleepy John Estes| 10"
								+ "|Guitar Slim| 12"
								+ "|Charley Patton| 14"
								+ "|Little Eva| 18"
								+ "total artists: 6"
								+ "|Fixin' To Die Blues| 1"
								+ "|Street Car Blues| 5"
								+ "|Got The Blues| 8"
								+ "|Long Lonesome Blues| 15"
								+ "|Ma Rainey's Black Bottom| 16"
								+ "|The Things That I Used To Do| 17"
								+ "|The Loco-Motion| 18"
								+ "total songs: 7"
								+ "|Jim Reeves| is added to the artist database."
								+ "|Jingle Bells| is added to the song database."
								+ "Memory pool expanded to be 320 bytes."
								+ "|Mongo Santamaria| is added to the artist database."
								+ "|Watermelon Man| is added to the song database."
								+ "(44,11) -> (121,4) -> (319,1)"));
	}
}
