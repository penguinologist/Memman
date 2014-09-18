// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

/**
 * @author Phuong Le
 * @author Jeroen
 * @version 9/17/2014
 */
public class FreeBlock {

	/**
	 * local vars
	 */
	private int startPosition;
	private int length;

	// Constructor
	// ----------------------------------------------------------
	/**
	 * Create a new Handle for the memory pool
	 * 
	 * @param startPosition
	 *            where the handle starts
	 * @param length
	 *            of the item stored
	 */
	public FreeBlock(int startPosition, int length) {
		this.startPosition = startPosition;
		this.length = length;
	}

	// ----------------------------------------------------------
	/**
	 * Returns the start position of the handle
	 * 
	 * @return int value of the start position
	 */
	public int getStartPosition() {
		return startPosition;
	}

	// ----------------------------------------------------------
	/**
	 * returns the length of the string
	 * 
	 * @return int value of the length
	 */
	public int getLength() {
		return length;
	}

	// ----------------------------------------------------------
	/**
	 * This method sets the start position
	 * 
	 * @param startPosition
	 *            where the handle is supposed to start
	 */
	public void setStartPosition(int startPosition) {
		this.startPosition = startPosition;
	}

	// ----------------------------------------------------------
	/**
	 * This method sets the length of the handle
	 * 
	 * @param length
	 *            of the handle
	 */
	public void setLength(int length) {
		this.length = length;
	}

	// ----------------------------------------------------------
	/**
	 * This method checks to see if two handles are consecutive to eachother
	 * 
	 * @param other
	 *            handel to be compared to
	 * @return boolean value indicating validity
	 */
	public boolean isConsecutiveTo(FreeBlock other) {
		if (other == null) {
			return false;
		}
		return (startPosition + length == other.startPosition);
	}

	// ----------------------------------------------------------
	/**
	 * This method expands the handle to cover two handles.
	 * 
	 * @param newBlock
	 *            the new block to lengthen to
	 */
	public void expand(FreeBlock newBlock) {
		if (this.isConsecutiveTo(newBlock)) {
			this.length = this.length + newBlock.length;
		}
	}

	/**
	 * this method returns the string value of the handle
	 * 
	 * @return String value of the handle
	 */
	public String toString() {
		return "(" + startPosition + "," + length + ")";
	}
}