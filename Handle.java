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
 * This is the handle class, capable of delivering handles to items put in the memory pool
 *
 * @author jeroen
 * @author Phuong Le
 * @version Sep 14, 2014
 */
public class Handle
{
	/**
	 * local vars
	 */
    private int startPosition;


    // Constructor
    // ----------------------------------------------------------
    /**
     * Create a new Handle for the memory pool
     * @param startPosition where the handle starts
     * @param length of the item stored
     */
    public Handle(int startPosition)
    {
        this.startPosition = startPosition;
    }


    // ----------------------------------------------------------
    /**
     * Returns the start position of the handle
     * @return int value of the start position
     */
    public int getStartPosition()
    {
        return startPosition;
    }


    // ----------------------------------------------------------
    /**
     * This method sets the start position
     * @param startPosition where the handle is supposed to start
     */
    public void setStartPosition(int startPosition)
    {
        this.startPosition = startPosition;
    }

    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * @param other
     * @return
     */
    public boolean equals(Handle other)
    {
        return (this.startPosition == other.startPosition);
    }
}
