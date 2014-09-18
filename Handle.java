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
