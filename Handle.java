// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author User
 * @version Sep 14, 2014
 */
public class Handle
{
    private int startPosition;
    private int length;


    // Constructor
    // ----------------------------------------------------------
    /**
     * Create a new Handle object.
     * @param startPosition
     * @param length
     */
    public Handle(int startPosition, int length)
    {
        this.startPosition = startPosition;
        this.length = length;
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * @return
     */
    public int getStartPosition()
    {
        return startPosition;
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * @return
     */
    public int getLength()
    {
        return length;
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * @param startPosition
     */
    public void setStartPosition(int startPosition)
    {
        this.startPosition = startPosition;
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * @param length
     */
    public void setLength(int length)
    {
        this.length = length;
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * @param other
     * @return
     */
    public boolean isConsecutiveTo(Handle other)
    {
        if (other == null)
        {
            return false;
        }
        return (startPosition + length == other.startPosition);
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * @param newBlock
     */
    public void expand(Handle newBlock)
    {
        if (this.isConsecutiveTo(newBlock))
        {
            this.length = this.length + newBlock.length;
        }
    }


    public String toString()
    {
        return "(" + startPosition + "," + length + ")";
    }
}
